package net.bodz.bas.c.javax.servlet;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

import net.bodz.bas.c.jakarta.servlet.JxAsyncEvent;
import net.bodz.bas.c.util.MapGlobal;

public class JaAsyncListener
        implements
            AsyncListener {

    jakarta.servlet.AsyncListener ja;

    public JaAsyncListener(jakarta.servlet.AsyncListener ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public void onComplete(AsyncEvent event)
            throws IOException {
        ja.onComplete(JxAsyncEvent.jx2a(event));
    }

    @Override
    public void onTimeout(AsyncEvent event)
            throws IOException {
        ja.onTimeout(JxAsyncEvent.jx2a(event));
    }

    @Override
    public void onError(AsyncEvent event)
            throws IOException {
        ja.onError(JxAsyncEvent.jx2a(event));
    }

    @Override
    public void onStartAsync(AsyncEvent event)
            throws IOException {
        ja.onStartAsync(JxAsyncEvent.jx2a(event));
    }

    public static final MapGlobal<jakarta.servlet.AsyncListener, AsyncListener> ja2x //
            = MapGlobal.fn(AsyncListener.class, (s) -> new JaAsyncListener(s));

}
