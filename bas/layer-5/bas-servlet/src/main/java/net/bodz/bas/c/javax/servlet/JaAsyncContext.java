package net.bodz.bas.c.javax.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.bodz.bas.c.jakarta.servlet.JxAsyncListener;
import net.bodz.bas.c.jakarta.servlet.JxServletContext;
import net.bodz.bas.c.jakarta.servlet.JxServletRequest;
import net.bodz.bas.c.jakarta.servlet.JxServletResponse;
import net.bodz.bas.c.util.ClassNotSupportException;
import net.bodz.bas.c.util.MapGlobal;

public class JaAsyncContext
        implements
            AsyncContext {

    jakarta.servlet.AsyncContext ja;

    public JaAsyncContext(jakarta.servlet.AsyncContext ja) {
        this.ja = ja;
    }

    @Override
    public ServletRequest getRequest() {
        return JaServletRequest.ja2x.cachedMap(ja.getRequest());
    }

    @Override
    public ServletResponse getResponse() {
        return JaServletResponse.ja2x.cachedMap(ja.getResponse());
    }

    @Override
    public boolean hasOriginalRequestAndResponse() {
        return ja.hasOriginalRequestAndResponse();
    }

    @Override
    public void dispatch() {
        ja.dispatch();
    }

    @Override
    public void dispatch(String path) {
        ja.dispatch(path);
    }

    @Override
    public void dispatch(ServletContext context, String path) {
        ja.dispatch(//
                JxServletContext.jx2a.cachedMap(context), //
                path);
    }

    @Override
    public void complete() {
        ja.complete();
    }

    @Override
    public void start(Runnable run) {
        ja.start(run);
    }

    @Override
    public void addListener(AsyncListener listener) {
        ja.addListener(JxAsyncListener.jx2a.cachedMap(listener));
    }

    @Override
    public void addListener(AsyncListener listener, ServletRequest servletRequest, ServletResponse servletResponse) {
        ja.addListener(//
                JxAsyncListener.jx2a.cachedMap(listener), //
                JxServletRequest.jx2a.cachedMap(servletRequest), //
                JxServletResponse.jx2a.cachedMap(servletResponse));
    }

    @Override
    public <T extends AsyncListener> T createListener(Class<T> clazz)
            throws ServletException {
        throw new ClassNotSupportException();
    }

    public <T extends jakarta.servlet.AsyncListener> T createJaListener(Class<T> clazz)
            throws ServletException {
        try {
            return ja.createListener(clazz);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void setTimeout(long timeout) {
        ja.setTimeout(timeout);
    }

    @Override
    public long getTimeout() {
        return ja.getTimeout();
    }

    public static final MapGlobal<jakarta.servlet.AsyncContext, AsyncContext> ja2x //
            = MapGlobal.fn(AsyncContext.class, (s) -> new JaAsyncContext(s));

}
