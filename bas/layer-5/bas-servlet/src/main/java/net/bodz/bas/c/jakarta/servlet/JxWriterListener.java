package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;

import jakarta.servlet.WriteListener;

import net.bodz.bas.c.util.MapGlobal;

public class JxWriterListener
        implements
            WriteListener {

    javax.servlet.WriteListener jx;

    public JxWriterListener(javax.servlet.WriteListener jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public void onWritePossible()
            throws IOException {
        jx.onWritePossible();
    }

    @Override
    public void onError(Throwable t) {
        jx.onError(t);
    }

    public static final MapGlobal<javax.servlet.WriteListener, WriteListener> jx2a //
            = MapGlobal.fn(WriteListener.class, (s) -> new JxWriterListener(s));

}
