package net.bodz.bas.c.javax.servlet;

import java.io.IOException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

import net.bodz.bas.c.jakarta.servlet.JxReadListener;
import net.bodz.bas.c.util.MapGlobal;

public class JaServletInputStream
        extends ServletInputStream {

    jakarta.servlet.ServletInputStream ja;

    public JaServletInputStream(jakarta.servlet.ServletInputStream ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public int read()
            throws IOException {
        return ja.read();
    }

    @Override
    public int hashCode() {
        return ja.hashCode();
    }

    @Override
    public int readLine(byte[] b, int off, int len)
            throws IOException {
        return ja.readLine(b, off, len);
    }

    @Override
    public int read(byte[] b)
            throws IOException {
        return ja.read(b);
    }

    @Override
    public boolean isFinished() {
        return ja.isFinished();
    }

    @Override
    public boolean isReady() {
        return ja.isReady();
    }

    @Override
    public boolean equals(Object obj) {
        return ja.equals(obj);
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        ja.setReadListener(JxReadListener.jx2a.cachedMap(readListener));
    }

    @Override
    public int read(byte[] b, int off, int len)
            throws IOException {
        return ja.read(b, off, len);
    }

    @Override
    public long skip(long n)
            throws IOException {
        return ja.skip(n);
    }

    @Override
    public String toString() {
        return ja.toString();
    }

    @Override
    public int available()
            throws IOException {
        return ja.available();
    }

    @Override
    public void close()
            throws IOException {
        ja.close();
    }

    @Override
    public void mark(int readlimit) {
        ja.mark(readlimit);
    }

    @Override
    public void reset()
            throws IOException {
        ja.reset();
    }

    @Override
    public boolean markSupported() {
        return ja.markSupported();
    }

    public static final MapGlobal<jakarta.servlet.ServletInputStream, ServletInputStream> ja2x //
            = MapGlobal.fn(ServletInputStream.class, (s) -> new JaServletInputStream(s));

}
