package net.bodz.bas.html.path;

import java.io.IOException;
import java.util.ServiceLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.html.IHtmlViewBuilder;
import net.bodz.bas.html.IHtmlViewBuilderFactory;
import net.bodz.bas.html.IHttpReprContext;
import net.bodz.bas.html.IndexedHtmlViewBuilderFactory;
import net.bodz.bas.html.RootHtmlOutputContext;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.potato.ref.ValueEntry;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.PathDispatchService;
import net.bodz.bas.repr.path.TokenQueue;
import net.bodz.bas.repr.req.IHttpRequestProcessor;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.std.rfc.mime.ContentType;

public class PathDispatchServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(PathDispatchServlet.class);

    PathDispatchService pathDispatchService;
    IHtmlViewBuilderFactory viewBuilderFactory;
    PathArrivalHtmlViewBuilder pathArrivalVbo;

    public PathDispatchServlet() {
        pathDispatchService = PathDispatchService.getInstance();
        viewBuilderFactory = IndexedHtmlViewBuilderFactory.getInstance();
        pathArrivalVbo = new PathArrivalHtmlViewBuilder();
    }

    @Override
    protected void service(HttpServletRequest _req, HttpServletResponse resp)
            throws ServletException, IOException {
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

        if (!tokenQueue.isEmpty()) {
            logger.error("Incomplete-Dispatch: " + tokenQueue);
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, //
                    "Path-Remaining: " + tokenQueue.getRemainingPath());
            return;
        }

        IHtmlViewBuilder<Object> viewBuilder = viewBuilderFactory.getViewBuilder(target.getClass());
        ContentType contentType = viewBuilder.getContentType(target);
        resp.setContentType(contentType.getName());

        // Using UTF-8 by default.
        resp.setCharacterEncoding("utf-8");

        IHttpReprContext ctx = new RootHtmlOutputContext(req, resp);
        try {
            pathArrivalVbo.buildHtmlView(ctx, ValueEntry.wrap(arrival));
        } catch (ViewBuilderException e) {
            throw new ServletException("Build html view: " + e.getMessage(), e);
        }
        ctx.flush();
    }

    protected Object getStartObject() {
        return startObject;
    }

    public static Object startObject;

}
