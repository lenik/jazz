package net.bodz.bas.http.ctx;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bodz.bas.c.javax.servlet.http.AbstractHttpFilter;
import net.bodz.bas.c.javax.servlet.http.IHttpFilter;
import net.bodz.bas.c.javax.servlet.http.IServletRequestListener;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class CurrentHttpService
        extends AbstractHttpFilter
        implements IServletRequestListener {

    static final Logger logger = LoggerFactory.getLogger(CurrentHttpService.class);

    ThreadLocal<HttpServletRequest> requestRef = new ThreadLocal<HttpServletRequest>();
    ThreadLocal<HttpServletResponse> responseRef = new ThreadLocal<HttpServletResponse>();

    public CurrentHttpService() {
        if (instance == null)
            instance = this;
        else
            throw new IllegalStateException("Multiple instances.");
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
            CurrentHttpService.setRequest(req);
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        CurrentHttpService.setRequest(null);
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
            CurrentHttpService.setResponse(resp);
        }
        try {
            chain.doFilter(request, response);
        } finally {
            CurrentHttpService.setResponse(null);
        }
    }

    @Override
    public void destroy() {
    }

    // ________________________________________________________________________
    // ⇱ Part: Static Accessor
    //

    private static CurrentHttpService instance;

    static void ensureInstance() {
        if (instance == null)
            throw new IllegalUsageException("The listener/filter isn't configured.");
    }

    public static HttpServletRequest getRequestOpt() {
        ensureInstance();
        return instance.requestRef.get();
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = getRequestOpt();

        if (request == null)
            throw new IllegalStateException("Request isn't set.");

        return request;
    }

    public static void setRequest(HttpServletRequest request) {
        ensureInstance();
        instance.requestRef.set(request);
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

    public static synchronized void escape(Runnable runnable) {
        HttpServletRequest request = getRequestOpt();
        try {
            runnable.run();
        } finally {
            setRequest(request);
        }
    }

    @Deprecated
    public static HttpServletResponse getResponseOpt() {
        ensureInstance();
        return instance.responseRef.get();
    }

    @Deprecated
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = getResponseOpt();

        if (response == null)
            throw new IllegalStateException("Response isn't set.");

        return instance.responseRef.get();
    }

    @Deprecated
    public static void setResponse(HttpServletResponse response) {
        ensureInstance();
        instance.responseRef.set(response);
    }

}
