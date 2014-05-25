package net.bodz.bas.html.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ServiceLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.IHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewBuilderFactory;
import net.bodz.bas.html.IHtmlViewContext;
import net.bodz.bas.html.IndexedHtmlViewBuilderFactory;
import net.bodz.bas.html.RootHtmlViewContext;
import net.bodz.bas.html.artifact.IArtifactManager;
import net.bodz.bas.html.artifact.IndexedArtifactManager;
import net.bodz.bas.html.vbo.PathFramesVbo;
import net.bodz.bas.http.HttpServlet;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.adapter.WriterCharOut;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.PathDispatchService;
import net.bodz.bas.repr.path.TokenQueue;
import net.bodz.bas.repr.req.IHttpRequestProcessor;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.std.rfc.http.HttpCacheControl;
import net.bodz.bas.std.rfc.http.ICacheControl;
import net.bodz.bas.std.rfc.mime.ContentType;
import net.bodz.bas.ui.dom1.UiValue;
import net.bodz.bas.xml.dom.XmlFormatter;

public class PathDispatchServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(PathDispatchServlet.class);

    PathDispatchService pathDispatchService;
    IHtmlViewBuilderFactory viewBuilderFactory;
    PathFramesVbo pathFramesVbo;

    public PathDispatchServlet() {
        pathDispatchService = PathDispatchService.getInstance();
        viewBuilderFactory = IndexedHtmlViewBuilderFactory.getInstance();
        pathFramesVbo = new PathFramesVbo();
    }

    @Override
    protected void serviceImpl(HttpServletRequest _req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("X-Powered-By", "Jazz BAS Repr/Html Server 2.0");

        HttpServletReqEx req = HttpServletReqEx.of(_req);

        for (IHttpRequestProcessor proc : ServiceLoader.load(IHttpRequestProcessor.class))
            try {
                proc.apply(req);
            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        String pathInfo = req.getPathInfo();
        if (pathInfo.startsWith("/"))
            pathInfo = pathInfo.substring(1);
        TokenQueue tokenQueue = new TokenQueue(pathInfo);

        Object startObject = getStartObject();
        IPathArrival arrival;
        try {
            arrival = pathDispatchService.dispatch(startObject, tokenQueue);
        } catch (PathDispatchException e) {
            throw new ServletException(e.getMessage(), e);
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

        req.setAttribute(IHtmlViewBuilderFactory.class, viewBuilderFactory);
        req.setAttribute(IArtifactManager.class, IndexedArtifactManager.getInstance());

        IHtmlViewBuilder<Object> viewBuilder = viewBuilderFactory.getViewBuilder(target.getClass());
        if (viewBuilder == null)
            throw new IllegalUsageError("No available view builder");

        ContentType contentType = viewBuilder.getContentType(target);
        resp.setContentType(contentType.getName());

        // Using UTF-8 by default.
        resp.setCharacterEncoding("utf-8");

        if (target instanceof ICacheControl) {
            HttpCacheControl.apply(resp, (ICacheControl) target);
        }

        switch (contentType.getName()) {
        case "text/html":
        case "text/xhtml":
            IHtmlViewContext ctx = new RootHtmlViewContext(req, resp);

            try {
                pathFramesVbo.buildHtmlView(ctx, UiValue.wrap(arrival));
            } catch (ViewBuilderException e) {
                throw new ServletException("Build html view: " + e.getMessage(), e);
            }

            PrintWriter writer = resp.getWriter();
            WriterCharOut charOut = new WriterCharOut(writer);
            ITreeOut treeOut = TreeOutImpl.from(charOut);
            XmlFormatter formatter = new XmlFormatter(treeOut);
            formatter.format(ctx.getHtmlDoc());
            break;

        default:
            resp.addHeader("X-Content-View", viewBuilder.getClass().getSimpleName());
            IHtmlViewContext _ctx = new RootHtmlViewContext(req, resp);
            try {
                viewBuilder.buildHtmlView(_ctx, UiValue.wrap(target));
            } catch (ViewBuilderException e) {
                throw new ServletException("Build view: " + e.getMessage(), e);
            }
        }
    }

    protected Object getStartObject() {
        return startObject;
    }

    public static Object startObject;

}
