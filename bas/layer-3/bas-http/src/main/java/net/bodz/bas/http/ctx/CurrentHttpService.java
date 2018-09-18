package net.bodz.bas.http.ctx;

import java.io.IOException;

import javax.servlet.FilterChain;
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

public class CurrentHttpService
        extends AbstractHttpFilter
        implements IServletRequestListener {

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
        requestRef.set(request);
        responseRef.set(response);
        try {
            chain.doFilter(request, response);
        } finally {
            responseRef.set(null);
            requestRef.set(null);
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
