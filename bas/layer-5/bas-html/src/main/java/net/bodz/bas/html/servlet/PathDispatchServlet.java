package net.bodz.bas.html.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.ServiceLoader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fn.EvalException;
import net.bodz.bas.fn.IEvaluable;
import net.bodz.bas.html.artifact.IArtifactManager;
import net.bodz.bas.html.artifact.IndexedArtifactManager;
import net.bodz.bas.html.viz.DefaultHtmlViewContext;
import net.bodz.bas.html.viz.IHtmlViewBuilder;
import net.bodz.bas.html.viz.IHtmlViewBuilderFactory;
import net.bodz.bas.html.viz.IndexedHtmlViewBuilderFactory;
import net.bodz.bas.html.viz.util.PathFrames_htm;
import net.bodz.bas.http.viz.IHttpViewBuilderFactory;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterCharOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.PathDispatchService;
import net.bodz.bas.repr.path.TokenQueue;
import net.bodz.bas.repr.req.HttpSnapManager;
import net.bodz.bas.repr.req.IHttpRequestProcessor;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryableUnion;
import net.bodz.bas.std.rfc.http.HttpCacheControl;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.ui.dom1.UiVar;
import net.bodz.bas.xml.dom.XmlFormatter;

public class PathDispatchServlet
        extends DumpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(PathDispatchServlet.class);

    public static final String ROOT_CLASS = "rootClass";
    public static final String MAX_EVAL_DEPTH = "maxEvalDepth";

    private Object rootObject;
    private int maxEvalDepth = 10;

    private PathDispatchService pathDispatchService;
    private IHtmlViewBuilderFactory viewBuilderFactory;
    private PathFrames_htm pathFramesVbo;
    private HttpSnapManager snapManager;

    public PathDispatchServlet() {
        pathDispatchService = PathDispatchService.getInstance();
        viewBuilderFactory = IndexedHtmlViewBuilderFactory.getInstance();
        pathFramesVbo = new PathFrames_htm();
        snapManager = new HttpSnapManager();
    }

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
        String rootClassName = config.getInitParameter(ROOT_CLASS);
        try {
            Class<?> rootClass = Class.forName(rootClassName);
            rootObject = rootClass.newInstance();
        } catch (Exception e) {
            throw new ServletException(e.getMessage(), e);
        }

        String maxRefDepthStr = config.getInitParameter(MAX_EVAL_DEPTH);
        if (maxRefDepthStr != null)
            maxEvalDepth = Integer.parseInt(maxRefDepthStr);
    }

    @Override
    protected void serviceImpl(HttpServletRequest _req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("X-Powered-By", "Jazz BAS Repr/Html Server 2.0");

        HttpServletReqEx req = HttpServletReqEx.of(_req);
        req.setAttribute(HttpSnapManager.ATTRIBUTE_KEY, snapManager);

        for (IHttpRequestProcessor proc : ServiceLoader.load(IHttpRequestProcessor.class))
            try {
                if (!proc.apply(req, resp)) {
                    // invalid request.
                    return;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        String pathInfo = req.getPathInfo();
        if (pathInfo.startsWith("/"))
            pathInfo = pathInfo.substring(1);
        TokenQueue tokenQueue = new TokenQueue(pathInfo);

        Object start = rootObject;
        IPathArrival arrival;
        try {
            arrival = pathDispatchService.dispatch(start, tokenQueue);
        } catch (PathDispatchException e) {
            throw new ServletException(e.getMessage(), e);
        }

        for (int i = 0; i < maxEvalDepth; i++) {
            Object target = arrival.getTarget();
            if (target instanceof IEvaluable<?>) {
                IEvaluable<?> ref = (IEvaluable<?>) target;
                try {
                    target = ref.eval();
                } catch (EvalException e) {
                    throw new ServletException(e.getMessage(), e);
                }
                arrival = new PathArrival(arrival, target, new String[0], arrival.getRemainingPath());
            } else
                break;
        }

        req.setAttribute(ITokenQueue.class, tokenQueue);
        req.setAttribute(IPathArrival.class, arrival);

        if (arrival == null)
            throw new ServletException("Dispatch failed: " + tokenQueue);

        Object target = arrival.getTarget();
        if (target == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Arrival: " + arrival);
            return;
        }

        if (!tokenQueue.isEmpty() && !tokenQueue.isStopped()) {
            logger.error("Incomplete-Dispatch: " + tokenQueue);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, //
                    "Path-Remaining: " + tokenQueue.getRemainingPath());
            return;
        }

        req.setAttribute(IHttpViewBuilderFactory.class, viewBuilderFactory);
        req.setAttribute(IArtifactManager.class, IndexedArtifactManager.getInstance());

        IHtmlViewBuilder<Object> viewBuilder = viewBuilderFactory.getViewBuilder(target.getClass());
        if (viewBuilder == null)
            throw new IllegalUsageError("No available view builder");

        ContentType contentType = viewBuilder.getContentType(req, target);
        resp.setContentType(contentType.getName());

        // Using UTF-8 by default.
        resp.setCharacterEncoding("utf-8");

        if (target instanceof ICacheControl) {
            HttpCacheControl.apply(resp, (ICacheControl) target);
        }

        QueryableUnion union = new QueryableUnion();
        for (IPathArrival a : arrival.toList(false))
            if (a.getTarget() instanceof IQueryable)
                union.add((IQueryable) a.getTarget());
            Collections.reverse(union);

        DefaultHtmlViewContext ctx = new DefaultHtmlViewContext(req, resp);
        if(!union.isEmpty())
            ctx.setQueryContext(union);

        switch (contentType.getName()) {
        case "text/html":
        case "text/xhtml":
            try {
                pathFramesVbo.buildHtmlView(ctx, ctx.getHtmlDoc().getRoot(), UiVar.wrap(arrival));
            } catch (ViewBuilderException e) {
                throw new ServletException("Build html view: " + e.getMessage(), e);
            }

            PrintWriter writer = resp.getWriter();
            WriterCharOut charOut = new WriterCharOut(writer);
            ITreeOut treeOut = TreeOutImpl.from(charOut);

            XmlFormatter formatter = new XmlFormatter(treeOut);
            formatter.setSelfClosing(false); // HTML5
            formatter.format(ctx.getHtmlDoc());
            break;

        default:
            resp.addHeader("X-Content-View", viewBuilder.getClass().getSimpleName());
            try {
                viewBuilder.buildHtmlView(ctx, ctx.getHtmlDoc().getRoot(), UiVar.wrap(target));
            } catch (ViewBuilderException e) {
                throw new ServletException("Build view: " + e.getMessage(), e);
            }
        }
    }

}
