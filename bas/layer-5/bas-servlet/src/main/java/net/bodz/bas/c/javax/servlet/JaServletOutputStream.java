package net.bodz.bas.c.javax.servlet;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

import net.bodz.bas.c.jakarta.servlet.JxWriterListener;
import net.bodz.bas.c.util.MapGlobal;

public class JaServletOutputStream
        extends ServletOutputStream {

    jakarta.servlet.ServletOutputStream ja;

    public JaServletOutputStream(jakarta.servlet.ServletOutputStream ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public void write(int b)
            throws IOException {
        ja.write(b);
    }

    @Override
    public int hashCode() {
        return ja.hashCode();
    }

    @Override
    public void write(byte[] b)
            throws IOException {
        ja.write(b);
    }

    @Override
    public void print(String s)
            throws IOException {
        ja.print(s);
    }

    @Override
    public void write(byte[] b, int off, int len)
            throws IOException {
        ja.write(b, off, len);
    }

    @Override
    public void print(boolean b)
            throws IOException {
        ja.print(b);
    }

    @Override
    public void print(char c)
            throws IOException {
        ja.print(c);
    }

    @Override
    public boolean equals(Object obj) {
        return ja.equals(obj);
    }

    @Override
    public void print(int i)
            throws IOException {
        ja.print(i);
    }

    @Override
    public void flush()
            throws IOException {
        ja.flush();
    }

    @Override
    public void print(long l)
            throws IOException {
        ja.print(l);
    }

    @Override
    public void print(float f)
            throws IOException {
        ja.print(f);
    }

    @Override
    public void print(double d)
            throws IOException {
        ja.print(d);
    }

    @Override
    public void close()
            throws IOException {
        ja.close();
    }

    @Override
    public void println()
            throws IOException {
        ja.println();
    }

    @Override
    public void println(String s)
            throws IOException {
        ja.println(s);
    }

    @Override
    public void println(boolean b)
            throws IOException {
        ja.println(b);
    }

    @Override
    public void println(char c)
            throws IOException {
        ja.println(c);
    }

    @Override
    public void println(int i)
            throws IOException {
        ja.println(i);
    }

    @Override
    public void println(long l)
            throws IOException {
        ja.println(l);
    }

    @Override
    public void println(float f)
            throws IOException {
        ja.println(f);
    }

    @Override
    public void println(double d)
            throws IOException {
        ja.println(d);
    }

    @Override
    public boolean isReady() {
        return ja.isReady();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        ja.setWriteListener(JxWriterListener.jx2a.cachedMap(writeListener));
    }

    @Override
    public String toString() {
        return ja.toString();
    }

    public static final MapGlobal<jakarta.servlet.ServletOutputStream, ServletOutputStream> ja2x //
            = MapGlobal.fn(ServletOutputStream.class, (s) -> new JaServletOutputStream(s));

}
