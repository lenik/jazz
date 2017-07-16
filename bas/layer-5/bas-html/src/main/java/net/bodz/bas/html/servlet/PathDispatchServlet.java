package net.bodz.bas.html.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.html.artifact.IArtifactManager;
import net.bodz.bas.html.artifact.IndexedArtifactManager;
import net.bodz.bas.html.io.HtmlDoc;
import net.bodz.bas.html.io.HtmlOutputFormat;
import net.bodz.bas.html.io.RecHtmlOut;
import net.bodz.bas.html.viz.DefaultHtmlViewContext;
import net.bodz.bas.html.viz.IHtmlViewBuilder;
import net.bodz.bas.html.viz.PathArrivalFrames;
import net.bodz.bas.html.viz.PathArrivalFrames_htm;
import net.bodz.bas.http.viz.ContentFamily;
import net.bodz.bas.http.viz.IHttpViewBuilder;
import net.bodz.bas.http.viz.IHttpViewBuilderFactory;
import net.bodz.bas.http.viz.IndexedHttpViewBuilderFactory;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.PathDispatchIndex;
import net.bodz.bas.repr.path.TokenQueue;
import net.bodz.bas.repr.req.HttpSnapManager;
import net.bodz.bas.repr.req.IHttpRequestProcessor;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.ViewBuilderSet;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryableUnion;
import net.bodz.bas.std.rfc.http.HttpCacheControl;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.ui.dom1.UiVar;

public class PathDispatchServlet
        extends DumpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(PathDispatchServlet.class);

    public static final String ROOT_CLASS = "rootClass";

    private Object rootObject;

    private PathDispatchIndex pathDispatchIndex;
    private IHttpViewBuilderFactory viewBuilderFactory;
    private PathArrivalFrames_htm pathArrivalFrames_htm;
    private HttpSnapManager snapManager;

    public PathDispatchServlet() {
        pathDispatchIndex = PathDispatchIndex.getInstance();
        viewBuilderFactory = IndexedHttpViewBuilderFactory.getInstance();
        pathArrivalFrames_htm = new PathArrivalFrames_htm();
        snapManager = new HttpSnapManager();
    }

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        super.init(config);
        String rootClassName = config.getInitParameter(ROOT_CLASS);
        if (rootClassName != null)
            try {
                Class<?> rootClass = Class.forName(rootClassName);
                rootObject = rootClass.newInstance();
            } catch (Exception e) {
                throw new ServletException("Failed to instantiate root object: " + e.getMessage(), e);
            }
    }

    public void setRootObject(Object rootObject) {
        if (rootObject == null)
            throw new NullPointerException("rootObject");
        this.rootObject = rootObject;
    }

    @Override
    protected void serviceImpl(HttpServletRequest _req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (rootObject == null)
            throw new IllegalConfigException("rootObject isn't set.");

        resp.setHeader("X-Powered-By", "Jazz BAS Repr/Html Server 2.0");

        HttpServletReqEx req = HttpServletReqEx.of(_req);
        req.setAttribute(HttpSnapManager.ATTRIBUTE_KEY, snapManager);

        if (!IHttpRequestProcessor.fn.applyAll(req, resp))
            return;

        String pathInfo = req.getPathInfo();
        if (pathInfo.startsWith("/"))
            pathInfo = pathInfo.substring(1);
        TokenQueue tokenQueue = new TokenQueue(pathInfo);

        Object start = rootObject;
        IPathArrival arrival;
        try {
            arrival = pathDispatchIndex.dispatch(start, tokenQueue);
            // List<IPathArrival> arrivals = pathDispatchIndex.dispatchMultiple(start, tokenQueue);
            // arrival = arrivals.isEmpty() ? null : arrivals.get(0);
        } catch (PathDispatchException e) {
            throw new ServletException(e.getMessage(), e);
        }
        if (arrival == null)
            throw new ServletException("Dispatch failed: " + tokenQueue);

        req.setAttribute(ITokenQueue.class, tokenQueue);
        req.setAttribute(IPathArrival.ATTRIBUTE_KEY, arrival);

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

        req.setAttribute(IHttpViewBuilderFactory.ATTRIBUTE_KEY, viewBuilderFactory);
        req.setAttribute(IArtifactManager.ATTRIBUTE_KEY, IndexedArtifactManager.getInstance());

        ContentType contentType = ContentType.forPath(pathInfo);
        ViewBuilderSet<Object> viewBuilders = viewBuilderFactory.getViewBuilders(target.getClass());
        IHttpViewBuilder<Object> viewBuilder = ContentFamily.findFirstFor(viewBuilders, contentType);
        if (viewBuilder == null)
            throw new IllegalUsageError("No suitable view builder for " + target.getClass());

        contentType = viewBuilder.getContentType(req, target);
        resp.setContentType(contentType.getName());

        String encoding = viewBuilder.getEncoding();
        if (encoding != null)
            resp.setCharacterEncoding(encoding);

        if (target instanceof ICacheControl) {
            HttpCacheControl.apply(resp, (ICacheControl) target);
        }

        QueryableUnion union = new QueryableUnion();
        for (IPathArrival a : arrival.toList())
            if (a.getTarget() instanceof IQueryable)
                union.add((IQueryable) a.getTarget());

        DefaultHtmlViewContext ctx = new DefaultHtmlViewContext(req, resp);
        if (!union.isEmpty())
            ctx.setQueryContext(union);

        boolean isHtml = false;
        switch (contentType.getName()) {
        case "text/html":
        case "text/xhtml":
            if (viewBuilder instanceof IHtmlViewBuilder<?>)
                isHtml = true;
        }

        if (isHtml) {
            HtmlOutputFormat outputFormat = new HtmlOutputFormat();

            WriterPrintOut printOut = new WriterPrintOut(resp.getWriter());
            ITreeOut treeOut = TreeOutImpl.from(printOut);
            HtmlDoc doc = new HtmlDoc(treeOut, outputFormat);
            RecHtmlOut htmlOut = doc.newHtmlOut();

            try {
                PathArrivalFrames frames = PathArrivalFrames.convert(viewBuilderFactory, arrival);
                frames.enterAll(ctx);
                pathArrivalFrames_htm.buildHtmlView(ctx, htmlOut, UiVar.wrap(frames));
                frames.leaveAll(ctx);
            } catch (ViewBuilderException e) {
                throw new ServletException("Build html view: " + e.getMessage(), e);
            }

            htmlOut.close();
        }

        else {
            UiVar<Object> ref = UiVar.wrap(target);

            resp.addHeader("X-Content-View", viewBuilder.getClass().getSimpleName());
            viewBuilder.precompile(ctx, ref);
            try {
                viewBuilder.buildHttpViewStart(ctx, resp, ref);
            } catch (ViewBuilderException e) {
                throw new ServletException("Build view: " + e.getMessage(), e);
            }
        } // if is-html.
    }

}
