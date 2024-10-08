package net.bodz.bas.servlet.ctx;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.bodz.bas.c.jakarta.servlet.http.IHttpFilter;
import net.bodz.bas.c.jakarta.servlet.http.IServletRequestListener;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class CurrentHttpService
        implements
            IHttpFilter,
            IServletRequestListener {

    static final Logger logger = LoggerFactory.getLogger(CurrentHttpService.class);

    static ThreadLocal<HttpServletRequest> requestRef = new ThreadLocal<HttpServletRequest>();
    static ThreadLocal<HttpServletResponse> responseRef = new ThreadLocal<HttpServletResponse>();

    public CurrentHttpService() {
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
            requestRef.set(req);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        requestRef.remove();
    }

    /** ⇱ Implementation Of {@link IHttpFilter}. */
    /* _____________________________ */static section.iface __HTTP_FILTER__;

    @Override
    public String getPreferredMapping() {
        return "/*";
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Thread thread = Thread.currentThread();
        String oldName = thread.getName();

        requestRef.set(request);
        responseRef.set(response);
        try {
            String uri = request.getRequestURI();
            thread.setName(uri);

            chain.doFilter(request, response);
        } finally {
            responseRef.set(null);
            requestRef.set(null);
            thread.setName(oldName);
        }
    }

    // ________________________________________________________________________
    // ⇱ Part: Static Accessor
    //

    public static HttpServletRequest getRequestOpt() {
        return requestRef.get();
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = getRequestOpt();

        if (request == null)
            throw new IllegalStateException("Request isn't set.");

        return request;
    }

    public static HttpServletResponse getResponseOpt() {
        return responseRef.get();
    }

    public static HttpServletResponse getResponse() {
        HttpServletResponse response = getResponseOpt();

        if (response == null)
            throw new IllegalStateException("Response isn't set.");

        return response;
    }

    public static HttpSession getSessionOpt() {
        HttpServletRequest request = getRequestOpt();
        if (request == null)
            return null;
        return request.getSession();
    }

    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        return request.getSession();
    }

    public static ServletContext getServletContextOpt() {
        HttpServletRequest request = getRequestOpt();
        if (request == null)
            return null;
        return request.getServletContext();
    }

    public static ServletContext getServletContext() {
        return getRequest().getServletContext();
    }

    public static synchronized void escape(Runnable runnable) {
        HttpServletRequest request = getRequestOpt();
        try {
            runnable.run();
        } finally {
            requestRef.set(request);
        }
    }

}
