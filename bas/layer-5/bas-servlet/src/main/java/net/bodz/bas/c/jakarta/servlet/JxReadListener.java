package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;

import jakarta.servlet.ReadListener;

import net.bodz.bas.c.util.MapGlobal;

public class JxReadListener
        implements
            ReadListener {

    javax.servlet.ReadListener jx;

    public JxReadListener(javax.servlet.ReadListener jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public void onDataAvailable()
            throws IOException {
        jx.onDataAvailable();
    }

    @Override
    public void onAllDataRead()
            throws IOException {
        jx.onAllDataRead();
    }

    @Override
    public void onError(Throwable t) {
        jx.onError(t);
    }

    public static final MapGlobal<javax.servlet.ReadListener, ReadListener> jx2a //
            = MapGlobal.fn(ReadListener.class, (s) -> new JxReadListener(s));

}
