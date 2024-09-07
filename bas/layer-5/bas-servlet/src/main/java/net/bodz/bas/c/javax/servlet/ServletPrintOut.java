package net.bodz.bas.c.javax.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.bodz.bas.io.adapter.WriterPrintOut;

public class ServletPrintOut
        extends WriterPrintOut
        implements ServletResponse {

    private final HttpServletResponse response;

    /**
     * @throws NullPointerException
     *             If <code>response</code> is <code>null</code>.
     * @throws IOException
     *             If can't {@link HttpServletResponse#getWriter() get writer} from the
     *             <code>response</code>.
     */
    public ServletPrintOut(HttpServletResponse response)
            throws IOException {
        super(response.getWriter());
        this.response = response;
    }

    // Delegates

    public void addCookie(Cookie cookie) {
        response.addCookie(cookie);
    }

    public boolean containsHeader(String name) {
        return response.containsHeader(name);
    }

    public String encodeURL(String url) {
        return response.encodeURL(url);
    }

    public String getCharacterEncoding() {
        return response.getCharacterEncoding();
    }

    public String encodeRedirectURL(String url) {
        return response.encodeRedirectURL(url);
    }

    public String getContentType() {
        return response.getContentType();
    }

    @Deprecated
    public String encodeUrl(String url) {
        return response.encodeUrl(url);
    }

    @Deprecated
    public String encodeRedirectUrl(String url) {
        return response.encodeRedirectUrl(url);
    }

    public ServletOutputStream getOutputStream()
            throws IOException {
        return response.getOutputStream();
    }

    public void sendError(int sc, String msg)
            throws IOException {
        response.sendError(sc, msg);
    }

    public PrintWriter getWriter()
            throws IOException {
        return response.getWriter();
    }

    public void sendError(int sc)
            throws IOException {
        response.sendError(sc);
    }

    public void sendRedirect(String location)
            throws IOException {
        response.sendRedirect(location);
    }

    public void setCharacterEncoding(String charset) {
        response.setCharacterEncoding(charset);
    }

    public void setDateHeader(String name, long date) {
        response.setDateHeader(name, date);
    }

    public void addDateHeader(String name, long date) {
        response.addDateHeader(name, date);
    }

    public void setHeader(String name, String value) {
        response.setHeader(name, value);
    }

    public void setContentLength(int len) {
        response.setContentLength(len);
    }

    public void setContentType(String type) {
        response.setContentType(type);
    }

    public void addHeader(String name, String value) {
        response.addHeader(name, value);
    }

    public void setIntHeader(String name, int value) {
        response.setIntHeader(name, value);
    }

    public void addIntHeader(String name, int value) {
        response.addIntHeader(name, value);
    }

    public void setBufferSize(int size) {
        response.setBufferSize(size);
    }

    public void setStatus(int sc) {
        response.setStatus(sc);
    }

    @Deprecated
    public void setStatus(int sc, String sm) {
        response.setStatus(sc, sm);
    }

    public int getBufferSize() {
        return response.getBufferSize();
    }

    public void flushBuffer()
            throws IOException {
        response.flushBuffer();
    }

    public void resetBuffer() {
        response.resetBuffer();
    }

    public boolean isCommitted() {
        return response.isCommitted();
    }

    public void reset() {
        response.reset();
    }

    public void setLocale(Locale loc) {
        response.setLocale(loc);
    }

    public Locale getLocale() {
        return response.getLocale();
    }

}
