package net.bodz.bas.repr.path;

import java.io.IOException;
import java.util.ServiceLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.html.IHtmlOut;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.potato.ref.ValueEntry;
import net.bodz.bas.repr.html.IHtmlOutputContext;
import net.bodz.bas.repr.html.IHtmlViewBuilder;
import net.bodz.bas.repr.html.IHtmlViewBuilderFactory;
import net.bodz.bas.repr.html.IndexedHtmlViewBuilderFactory;
import net.bodz.bas.repr.html.RootHtmlOutputContext;
import net.bodz.bas.repr.req.IHttpRequestProcessor;
import net.bodz.bas.repr.viz.ViewBuilderException;

public class PathDispatchServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    static final Logger logger = LoggerFactory.getLogger(PathDispatchServlet.class);

    PathDispatchService pathDispatchService;
    IHtmlViewBuilderFactory viewBuilderFactory;

    public PathDispatchServlet() {
        pathDispatchService = PathDispatchService.getInstance();
        viewBuilderFactory = IndexedHtmlViewBuilderFactory.getInstance();
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

        // if (!tokenQueue.isEmpty())
        // throw new ServletException("Dispatch incomplete: " + arrival);

        Object target = arrival.getTarget();
        if (target == null)
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Arrival: " + arrival);

        if (!tokenQueue.isEmpty()) {
            logger.debug(tokenQueue);
        }

        Class<?> targetClass = target.getClass();
        IRefEntry<Object> entry = new ValueEntry<>(target);

        IHtmlViewBuilder<Object> htmlVbo = viewBuilderFactory.getViewBuilder(targetClass);
        if (htmlVbo != null) {
            // Using UTF-8 by default.
            resp.setCharacterEncoding("utf-8");

            IHtmlOutputContext ctx = new RootHtmlOutputContext(req, resp);
            try {
                htmlVbo.buildHtmlView(ctx, entry);
            } catch (ViewBuilderException e) {
                throw new ServletException("Build html view: " + e.getMessage(), e);
            }

            IHtmlOut htmlOut = ctx.getOut();
            htmlOut.flush();
        }
    }

    protected Object getStartObject() {
        return startObject;
    }

    public static Object startObject;

}
