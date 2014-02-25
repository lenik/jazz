package net.bodz.bas.repr.path;

import java.io.IOException;
import java.util.ServiceLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.c.javax.servlet.http.HttpServletReqEx;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.req.IHttpRequestProcessor;

public class PathDispatchServlet
        extends HttpServlet {

    private static final long serialVersionUID = 1L;

    PathDispatchService pathDispatchService = PathDispatchService.getInstance();

    public PathDispatchServlet() {
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

    }

    protected Object getStartObject() {
        return startObject;
    }

    public static Object startObject;

}
