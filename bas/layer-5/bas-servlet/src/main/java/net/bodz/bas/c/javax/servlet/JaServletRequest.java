package net.bodz.bas.c.javax.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.bodz.bas.c.jakarta.servlet.JxServletRequest;
import net.bodz.bas.c.jakarta.servlet.JxServletResponse;
import net.bodz.bas.c.util.MapGlobal;

public class JaServletRequest
        implements
            ServletRequest {

    jakarta.servlet.ServletRequest ja;

    public JaServletRequest(jakarta.servlet.ServletRequest ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public Object getAttribute(String name) {
        return ja.getAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return ja.getAttributeNames();
    }

    @Override
    public String getCharacterEncoding() {
        return ja.getCharacterEncoding();
    }

    @Override
    public void setCharacterEncoding(String env)
            throws UnsupportedEncodingException {
        ja.setCharacterEncoding(env);
    }

    @Override
    public int getContentLength() {
        return ja.getContentLength();
    }

    @Override
    public long getContentLengthLong() {
        return ja.getContentLengthLong();
    }

    @Override
    public String getContentType() {
        return ja.getContentType();
    }

    @Override
    public ServletInputStream getInputStream()
            throws IOException {
        return JaServletInputStream.ja2x.cachedMap(ja.getInputStream());
    }

    @Override
    public String getParameter(String name) {
        return ja.getParameter(name);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return ja.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        return ja.getParameterValues(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return ja.getParameterMap();
    }

    @Override
    public String getProtocol() {
        return ja.getProtocol();
    }

    @Override
    public String getScheme() {
        return ja.getScheme();
    }

    @Override
    public String getServerName() {
        return ja.getServerName();
    }

    @Override
    public int getServerPort() {
        return ja.getServerPort();
    }

    @Override
    public BufferedReader getReader()
            throws IOException {
        return ja.getReader();
    }

    @Override
    public String getRemoteAddr() {
        return ja.getRemoteAddr();
    }

    @Override
    public String getRemoteHost() {
        return ja.getRemoteHost();
    }

    @Override
    public void setAttribute(String name, Object o) {
        ja.setAttribute(name, o);
    }

    @Override
    public void removeAttribute(String name) {
        ja.removeAttribute(name);
    }

    @Override
    public Locale getLocale() {
        return ja.getLocale();
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return ja.getLocales();
    }

    @Override
    public boolean isSecure() {
        return ja.isSecure();
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return JaRequestDispatcher.ja2x.cachedMap(ja.getRequestDispatcher(path));
    }

    @Deprecated
    @Override
    public String getRealPath(String path) {
        return ja.getRealPath(path);
    }

    @Override
    public int getRemotePort() {
        return ja.getRemotePort();
    }

    @Override
    public String getLocalName() {
        return ja.getLocalName();
    }

    @Override
    public String getLocalAddr() {
        return ja.getLocalAddr();
    }

    @Override
    public int getLocalPort() {
        return ja.getLocalPort();
    }

    @Override
    public ServletContext getServletContext() {
        return JaServletContext.ja2x.cachedMap(ja.getServletContext());
    }

    @Override
    public AsyncContext startAsync()
            throws IllegalStateException {
        return JaAsyncContext.ja2x.cachedMap(ja.startAsync());
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
            throws IllegalStateException {
        return JaAsyncContext.ja2x.cachedMap(//
                ja.startAsync(//
                        JxServletRequest.jx2a.cachedMap(servletRequest), //
                        JxServletResponse.jx2a.cachedMap(servletResponse)));
    }

    @Override
    public boolean isAsyncStarted() {
        return ja.isAsyncStarted();
    }

    @Override
    public boolean isAsyncSupported() {
        return ja.isAsyncSupported();
    }

    @Override
    public AsyncContext getAsyncContext() {
        return JaAsyncContext.ja2x.cachedMap(ja.getAsyncContext());
    }

    @Override
    public DispatcherType getDispatcherType() {
        return JaDispatcherType.ja2x(ja.getDispatcherType());
    }

    public static final MapGlobal<jakarta.servlet.ServletRequest, ServletRequest> ja2x //
            = MapGlobal.fn(ServletRequest.class, (s) -> new JaServletRequest(s));

}
