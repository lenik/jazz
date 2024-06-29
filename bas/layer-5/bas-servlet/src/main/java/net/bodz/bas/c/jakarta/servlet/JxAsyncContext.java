package net.bodz.bas.c.jakarta.servlet;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import net.bodz.bas.c.javax.servlet.JaAsyncListener;
import net.bodz.bas.c.javax.servlet.JaServletContext;
import net.bodz.bas.c.javax.servlet.JaServletRequest;
import net.bodz.bas.c.javax.servlet.JaServletResponse;
import net.bodz.bas.c.util.ClassNotSupportException;
import net.bodz.bas.c.util.MapGlobal;

public class JxAsyncContext
        implements
            AsyncContext {

    javax.servlet.AsyncContext jx;

    public JxAsyncContext(javax.servlet.AsyncContext jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public ServletRequest getRequest() {
        return JxServletRequest.jx2a.cachedMap(jx.getRequest());
    }

    @Override
    public ServletResponse getResponse() {
        return JxServletResponse.jx2a.cachedMap(jx.getResponse());
    }

    @Override
    public boolean hasOriginalRequestAndResponse() {
        return jx.hasOriginalRequestAndResponse();
    }

    @Override
    public void dispatch() {
        jx.dispatch();
    }

    @Override
    public void dispatch(String path) {
        jx.dispatch(path);
    }

    @Override
    public void dispatch(ServletContext context, String path) {
        jx.dispatch(//
                JaServletContext.ja2x.cachedMap(context), //
                path);
    }

    @Override
    public void complete() {
        jx.complete();
    }

    @Override
    public void start(Runnable run) {
        jx.start(run);
    }

    @Override
    public void addListener(AsyncListener listener) {
        jx.addListener(JaAsyncListener.ja2x.cachedMap(listener));
    }

    @Override
    public void addListener(AsyncListener listener, ServletRequest servletRequest, ServletResponse servletResponse) {
        jx.addListener(//
                JaAsyncListener.ja2x.cachedMap(listener), //
                JaServletRequest.ja2x.cachedMap(servletRequest), //
                JaServletResponse.ja2x.cachedMap(servletResponse));
    }

    @Override
    public <T extends AsyncListener> T createListener(Class<T> clazz)
            throws ServletException {
        throw new ClassNotSupportException();
    }

    public <T extends javax.servlet.AsyncListener> T createJxListener(Class<T> clazz)
            throws ServletException {
        try {
            return jx.createListener(clazz);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void setTimeout(long timeout) {
        jx.setTimeout(timeout);
    }

    @Override
    public long getTimeout() {
        return jx.getTimeout();
    }

    public static final MapGlobal<javax.servlet.AsyncContext, AsyncContext> jx2a //
            = MapGlobal.fn(AsyncContext.class, (s) -> new JxAsyncContext(s));

}
