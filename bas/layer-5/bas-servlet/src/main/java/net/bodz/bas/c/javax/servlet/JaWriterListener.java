package net.bodz.bas.c.javax.servlet;

import java.io.IOException;

import javax.servlet.WriteListener;

import net.bodz.bas.c.util.MapGlobal;

public class JaWriterListener
        implements
            WriteListener {

    jakarta.servlet.WriteListener ja;

    public JaWriterListener(jakarta.servlet.WriteListener ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public void onWritePossible()
            throws IOException {
        ja.onWritePossible();
    }

    @Override
    public void onError(Throwable t) {
        ja.onError(t);
    }

    public static final MapGlobal<jakarta.servlet.WriteListener, WriteListener> ja2x //
            = MapGlobal.fn(WriteListener.class, (s) -> new JaWriterListener(s));

}
