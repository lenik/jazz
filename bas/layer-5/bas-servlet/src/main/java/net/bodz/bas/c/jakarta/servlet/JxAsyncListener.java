package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;

import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;

import net.bodz.bas.c.javax.servlet.JaAsyncEvent;
import net.bodz.bas.c.util.MapGlobal;

public class JxAsyncListener
        implements
            AsyncListener {

    javax.servlet.AsyncListener jx;

    public JxAsyncListener(javax.servlet.AsyncListener jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public void onComplete(AsyncEvent event)
            throws IOException {
        jx.onComplete(JaAsyncEvent.ja2x(event));
    }

    @Override
    public void onTimeout(AsyncEvent event)
            throws IOException {
        jx.onTimeout(JaAsyncEvent.ja2x(event));
    }

    @Override
    public void onError(AsyncEvent event)
            throws IOException {
        jx.onError(JaAsyncEvent.ja2x(event));
    }

    @Override
    public void onStartAsync(AsyncEvent event)
            throws IOException {
        jx.onStartAsync(JaAsyncEvent.ja2x(event));
    }

    public static final MapGlobal<javax.servlet.AsyncListener, AsyncListener> jx2a //
            = MapGlobal.fn(AsyncListener.class, (s) -> new JxAsyncListener(s));

}
