package net.bodz.bas.c.javax.servlet;

import java.io.IOException;

import javax.servlet.ReadListener;

import net.bodz.bas.c.util.MapGlobal;

public class JaReadListener
        implements
            ReadListener {

    jakarta.servlet.ReadListener ja;

    public JaReadListener(jakarta.servlet.ReadListener ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public void onDataAvailable()
            throws IOException {
        ja.onDataAvailable();
    }

    @Override
    public void onAllDataRead()
            throws IOException {
        ja.onAllDataRead();
    }

    @Override
    public void onError(Throwable t) {
        ja.onError(t);
    }

    public static final MapGlobal<jakarta.servlet.ReadListener, ReadListener> ja2x //
            = MapGlobal.fn(ReadListener.class, (s) -> new JaReadListener(s));

}
