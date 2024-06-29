package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

import net.bodz.bas.c.javax.servlet.JaWriterListener;
import net.bodz.bas.c.util.MapGlobal;

public class JxServletOutputStream
        extends ServletOutputStream {

    javax.servlet.ServletOutputStream jx;

    public JxServletOutputStream(javax.servlet.ServletOutputStream jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public void write(int b)
            throws IOException {
        jx.write(b);
    }

    @Override
    public int hashCode() {
        return jx.hashCode();
    }

    @Override
    public void write(byte[] b)
            throws IOException {
        jx.write(b);
    }

    @Override
    public void print(String s)
            throws IOException {
        jx.print(s);
    }

    @Override
    public void write(byte[] b, int off, int len)
            throws IOException {
        jx.write(b, off, len);
    }

    @Override
    public void print(boolean b)
            throws IOException {
        jx.print(b);
    }

    @Override
    public boolean equals(Object obj) {
        return jx.equals(obj);
    }

    @Override
    public void print(char c)
            throws IOException {
        jx.print(c);
    }

    @Override
    public void print(int i)
            throws IOException {
        jx.print(i);
    }

    @Override
    public void flush()
            throws IOException {
        jx.flush();
    }

    @Override
    public void print(long l)
            throws IOException {
        jx.print(l);
    }

    @Override
    public void print(float f)
            throws IOException {
        jx.print(f);
    }

    @Override
    public void close()
            throws IOException {
        jx.close();
    }

    @Override
    public void print(double d)
            throws IOException {
        jx.print(d);
    }

    @Override
    public void println()
            throws IOException {
        jx.println();
    }

    @Override
    public void println(String s)
            throws IOException {
        jx.println(s);
    }

    @Override
    public void println(boolean b)
            throws IOException {
        jx.println(b);
    }

    @Override
    public void println(char c)
            throws IOException {
        jx.println(c);
    }

    @Override
    public void println(int i)
            throws IOException {
        jx.println(i);
    }

    @Override
    public void println(long l)
            throws IOException {
        jx.println(l);
    }

    @Override
    public void println(float f)
            throws IOException {
        jx.println(f);
    }

    @Override
    public void println(double d)
            throws IOException {
        jx.println(d);
    }

    @Override
    public String toString() {
        return jx.toString();
    }

    @Override
    public boolean isReady() {
        return jx.isReady();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        jx.setWriteListener(JaWriterListener.ja2x.cachedMap(writeListener));
    }

    public static final MapGlobal<javax.servlet.ServletOutputStream, ServletOutputStream> jx2a //
            = MapGlobal.fn(ServletOutputStream.class, (s) -> new JxServletOutputStream(s));

}
