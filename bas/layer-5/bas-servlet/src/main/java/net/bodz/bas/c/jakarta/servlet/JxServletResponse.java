package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletResponse;

import net.bodz.bas.c.util.MapGlobal;

public class JxServletResponse
        implements
            ServletResponse {

    javax.servlet.ServletResponse jx;

    public JxServletResponse(javax.servlet.ServletResponse jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public String getCharacterEncoding() {
        return jx.getCharacterEncoding();
    }

    @Override
    public String getContentType() {
        return jx.getContentType();
    }

    @Override
    public ServletOutputStream getOutputStream()
            throws IOException {
        return JxServletOutputStream.jx2a.cachedMap(jx.getOutputStream());
    }

    @Override
    public PrintWriter getWriter()
            throws IOException {
        return jx.getWriter();
    }

    @Override
    public void setCharacterEncoding(String charset) {
        jx.setCharacterEncoding(charset);
    }

    @Override
    public void setContentLength(int len) {
        jx.setContentLength(len);
    }

    @Override
    public void setContentLengthLong(long len) {
        jx.setContentLengthLong(len);
    }

    @Override
    public void setContentType(String type) {
        jx.setContentType(type);
    }

    @Override
    public void setBufferSize(int size) {
        jx.setBufferSize(size);
    }

    @Override
    public int getBufferSize() {
        return jx.getBufferSize();
    }

    @Override
    public void flushBuffer()
            throws IOException {
        jx.flushBuffer();
    }

    @Override
    public void resetBuffer() {
        jx.resetBuffer();
    }

    @Override
    public boolean isCommitted() {
        return jx.isCommitted();
    }

    @Override
    public void reset() {
        jx.reset();
    }

    @Override
    public void setLocale(Locale loc) {
        jx.setLocale(loc);
    }

    @Override
    public Locale getLocale() {
        return jx.getLocale();
    }

    public static final MapGlobal<javax.servlet.ServletResponse, ServletResponse> jx2a //
            = MapGlobal.fn(ServletResponse.class, (s) -> new JxServletResponse(s));

}
