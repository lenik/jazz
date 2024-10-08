package net.bodz.bas.html.servlet;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.c.jakarta.servlet.http.HttpServletReqEx;
import net.bodz.bas.c.jakarta.servlet.http.IHttpRequestProcessor;
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
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterPrintOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.PathDispatchIndex;
import net.bodz.bas.repr.path.TokenQueue;
import net.bodz.bas.repr.req.HttpSnapManager;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.repr.viz.ViewBuilderSet;
import net.bodz.bas.repr.viz.web.IHttpViewBuilder;
import net.bodz.bas.repr.viz.web.IHttpViewBuilderFactory;
import net.bodz.bas.repr.viz.web.IndexedHttpViewBuilderFactory;
import net.bodz.bas.rtx.IQueryable;
import net.bodz.bas.rtx.QueryableUnion;
import net.bodz.bas.std.rfc.http.HttpCacheControl;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.VariantMaps;
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
                rootObject = rootClass.getConstructor().newInstance();
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
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        if ("OPTIONS".equals(_req.getMethod()))
            return;

        HttpServletReqEx req = HttpServletReqEx.of(_req);
        req.setAttribute(HttpServletReqEx.ATTRIBUTE_KEY, req);
        req.setAttribute(HttpSnapManager.ATTRIBUTE_KEY, snapManager);

        if (! IHttpRequestProcessor.fn.applyAll(req, resp))
            return;

        TokenQueue tokenQueue = TokenQueue.ofRequest(req);
        IVariantMap<String> q = VariantMaps.fromRequest(req);
        req.setAttribute(ITokenQueue.ATTRIBUTE_KEY, tokenQueue);
        req.setAttribute(IVariantMap.class, q);

        Object start = rootObject;
        PathArrival init = new PathArrival(start, tokenQueue.getRemainingPath());
        IPathArrival arrival;
        try {
            arrival = pathDispatchIndex.dispatch(init, start, tokenQueue, q);
            // List<IPathArrival> arrivals = pathDispatchIndex.dispatchMultiple(start, tokenQueue);
            // arrival = arrivals.isEmpty() ? null : arrivals.get(0);
        } catch (PathDispatchException e) {
            throw new ServletException(e.getMessage(), e);
        }
        if (arrival == null)
            throw new ServletException("Dispatch failed: " + tokenQueue);

        req.setAttribute(IPathArrival.ATTRIBUTE_KEY, arrival);

        Object target = arrival.getTarget();
        if (target == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Arrival: " + arrival);
            return;
        }

        if (target == NoRender.INSTANCE)
            return;

        if (! tokenQueue.isDone() && ! tokenQueue.isStopped()) {
            logger.error("Incomplete-Dispatch: " + tokenQueue);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, //
                    "Path-Remaining: " + tokenQueue.getRemainingPath());
            return;
        }

        req.setAttribute(IHttpViewBuilderFactory.ATTRIBUTE_KEY, viewBuilderFactory);
        req.setAttribute(IArtifactManager.ATTRIBUTE_KEY, IndexedArtifactManager.getInstance());

        ContentType contentType = ContentType.forPath(req.getPathInfo());
        ViewBuilderSet<Object> viewBuilders = viewBuilderFactory.getViewBuilders(target.getClass());
        IHttpViewBuilder<Object> viewBuilder = ContentFamily.findFirstFor(viewBuilders, contentType);
        if (viewBuilder == null)
            throw new IllegalUsageError("No suitable view builder for " + target.getClass());

        String encoding = viewBuilder.getEncoding(target);
        // if (contentType == ContentType.DEFAULT)
        contentType = viewBuilder.getContentType(req, target);

        String contentTypeSpec = contentType.getName();
        if (encoding != null) {
            resp.setCharacterEncoding(encoding);
            contentTypeSpec += "; encoding=" + encoding;
        }
        resp.setContentType(contentTypeSpec);

        if (target instanceof ICacheControl) {
            HttpCacheControl.apply(resp, (ICacheControl) target);
        }

        QueryableUnion union = new QueryableUnion();
        for (IPathArrival a : arrival.toList())
            if (a.getTarget() instanceof IQueryable)
                union.add((IQueryable) a.getTarget());

        DefaultHtmlViewContext ctx = new DefaultHtmlViewContext(req, resp);
        if (! union.isEmpty())
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
                throw new ServletException(String.format(//
                        "Failed to build view for %s: %s", req.getPathInfo(), e.getMessage()), e);
            }
        } // if is-html.
    }

}
