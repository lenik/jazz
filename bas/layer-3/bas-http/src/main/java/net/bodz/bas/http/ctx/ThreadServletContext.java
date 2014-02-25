package net.bodz.bas.http.ctx;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.c.javax.servlet.http.AbstractHttpFilter;
import net.bodz.bas.c.javax.servlet.http.IHttpFilter;
import net.bodz.bas.c.javax.servlet.http.IServletRequestListener;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ThreadServletContext
        extends AbstractHttpFilter
        implements IServletRequestListener {

    static final Logger logger = LoggerFactory.getLogger(ThreadServletContext.class);

    static final ThreadLocal<HttpServletRequest> threadLocalRequests = new ThreadLocal<HttpServletRequest>();
    static final ThreadLocal<HttpServletResponse> threadLocalResponses = new ThreadLocal<HttpServletResponse>();

    public static HttpServletRequest getRequestOpt() {
        return threadLocalRequests.get();
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = getRequestOpt();

        if (request == null)
            throw new IllegalStateException("Not in an http-request scope");

        return request;
    }

    public static void setRequest(HttpServletRequest request) {
        threadLocalRequests.set(request);
    }

    public static HttpServletResponse getResponseOpt() {
        return threadLocalResponses.get();
    }

    public static HttpServletResponse getResponse() {
        HttpServletResponse response = getResponseOpt();

        if (response == null)
            throw new IllegalStateException("Not in an http-request scope");

        return threadLocalResponses.get();
    }

    public static void setResponse(HttpServletResponse response) {
        threadLocalResponses.set(response);
    }

    public static HttpSession getSessionOpt() {
        HttpServletRequest request = getRequestOpt();
        if (request == null)
            return null;
        return request.getSession();
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletContext getServletContextOpt() {
        HttpSession session = getSessionOpt();
        if (session == null)
            return null;
        return session.getServletContext();
    }

    public static ServletContext getServletContext() {
        return getSession().getServletContext();
    }

    public static ServletContext getApplication() {
        return getServletContext();
    }

    public static synchronized void escape(Runnable runnable) {
        HttpServletRequest request = getRequestOpt();
        try {
            runnable.run();
        } finally {
            setRequest(request);
        }
    }

    /** ⇱ Implementation Of {@link IServletRequestListener}. */
/* _____________________________ */static section.iface __REQ_LISTENER__;

    public static final int PRIORITY = -1;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        logger.debug("Thread servlet context enter: " + event);

        ServletRequest request = event.getServletRequest();

        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            ThreadServletContext.setRequest(req);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        ThreadServletContext.setRequest(null);
    }

    /** ⇱ Implementation Of {@link IHttpFilter}. */
    /* _____________________________ */static section.iface __HTTP_FILTER__;

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
    }

    @Override
    public String getPreferredMapping() {
        return "/*";
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            HttpServletResponse resp = response;
            ThreadServletContext.setResponse(resp);
        }
        try {
            chain.doFilter(request, response);
        } finally {
            ThreadServletContext.setResponse(null);
        }
    }

    @Override
    public void destroy() {
    }

}
