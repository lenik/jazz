package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

import net.bodz.bas.c.javax.servlet.JaReadListener;
import net.bodz.bas.c.util.MapGlobal;

public class JxServletInputStream
        extends ServletInputStream {

    javax.servlet.ServletInputStream jx;

    public JxServletInputStream(javax.servlet.ServletInputStream jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public int read()
            throws IOException {
        return jx.read();
    }

    @Override
    public int hashCode() {
        return jx.hashCode();
    }

    @Override
    public int readLine(byte[] b, int off, int len)
            throws IOException {
        return jx.readLine(b, off, len);
    }

    @Override
    public int read(byte[] b)
            throws IOException {
        return jx.read(b);
    }

    @Override
    public boolean isFinished() {
        return jx.isFinished();
    }

    @Override
    public boolean isReady() {
        return jx.isReady();
    }

    @Override
    public boolean equals(Object obj) {
        return jx.equals(obj);
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        jx.setReadListener(JaReadListener.ja2x.cachedMap(readListener));
    }

    @Override
    public int read(byte[] b, int off, int len)
            throws IOException {
        return jx.read(b, off, len);
    }

    @Override
    public long skip(long n)
            throws IOException {
        return jx.skip(n);
    }

    @Override
    public String toString() {
        return jx.toString();
    }

    @Override
    public int available()
            throws IOException {
        return jx.available();
    }

    @Override
    public void close()
            throws IOException {
        jx.close();
    }

    @Override
    public void mark(int readlimit) {
        jx.mark(readlimit);
    }

    @Override
    public void reset()
            throws IOException {
        jx.reset();
    }

    @Override
    public boolean markSupported() {
        return jx.markSupported();
    }

    public static final MapGlobal<javax.servlet.ServletInputStream, ServletInputStream> jx2a //
            = MapGlobal.fn(ServletInputStream.class, (s) -> new JxServletInputStream(s));

}
