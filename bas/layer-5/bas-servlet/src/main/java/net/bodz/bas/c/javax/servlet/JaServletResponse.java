package net.bodz.bas.c.javax.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import net.bodz.bas.c.util.MapGlobal;

public class JaServletResponse
        implements
            ServletResponse {

    jakarta.servlet.ServletResponse ja;

    public JaServletResponse(jakarta.servlet.ServletResponse ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public String getCharacterEncoding() {
        return ja.getCharacterEncoding();
    }

    @Override
    public String getContentType() {
        return ja.getContentType();
    }

    @Override
    public ServletOutputStream getOutputStream()
            throws IOException {
        return JaServletOutputStream.ja2x.cachedMap(ja.getOutputStream());
    }

    @Override
    public PrintWriter getWriter()
            throws IOException {
        return ja.getWriter();
    }

    @Override
    public void setCharacterEncoding(String charset) {
        ja.setCharacterEncoding(charset);
    }

    @Override
    public void setContentLength(int len) {
        ja.setContentLength(len);
    }

    @Override
    public void setContentLengthLong(long len) {
        ja.setContentLengthLong(len);
    }

    @Override
    public void setContentType(String type) {
        ja.setContentType(type);
    }

    @Override
    public void setBufferSize(int size) {
        ja.setBufferSize(size);
    }

    @Override
    public int getBufferSize() {
        return ja.getBufferSize();
    }

    @Override
    public void flushBuffer()
            throws IOException {
        ja.flushBuffer();
    }

    @Override
    public void resetBuffer() {
        ja.resetBuffer();
    }

    @Override
    public boolean isCommitted() {
        return ja.isCommitted();
    }

    @Override
    public void reset() {
        ja.reset();
    }

    @Override
    public void setLocale(Locale loc) {
        ja.setLocale(loc);
    }

    @Override
    public Locale getLocale() {
        return ja.getLocale();
    }

    public static final MapGlobal<jakarta.servlet.ServletResponse, ServletResponse> ja2x //
            = MapGlobal.fn(ServletResponse.class, (s) -> new JaServletResponse(s));

}
