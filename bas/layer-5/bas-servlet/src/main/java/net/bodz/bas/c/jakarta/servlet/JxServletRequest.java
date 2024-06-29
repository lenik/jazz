package net.bodz.bas.c.jakarta.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import net.bodz.bas.c.javax.servlet.JaServletRequest;
import net.bodz.bas.c.javax.servlet.JaServletResponse;
import net.bodz.bas.c.util.MapGlobal;

public class JxServletRequest
        implements
            ServletRequest {

    javax.servlet.ServletRequest jx;

    public JxServletRequest(javax.servlet.ServletRequest jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public Object getAttribute(String name) {
        return jx.getAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return jx.getAttributeNames();
    }

    @Override
    public String getCharacterEncoding() {
        return jx.getCharacterEncoding();
    }

    @Override
    public void setCharacterEncoding(String env)
            throws UnsupportedEncodingException {
        jx.setCharacterEncoding(env);
    }

    @Override
    public int getContentLength() {
        return jx.getContentLength();
    }

    @Override
    public long getContentLengthLong() {
        return jx.getContentLengthLong();
    }

    @Override
    public String getContentType() {
        return jx.getContentType();
    }

    @Override
    public ServletInputStream getInputStream()
            throws IOException {
        return JxServletInputStream.jx2a.cachedMap(jx.getInputStream());
    }

    @Override
    public String getParameter(String name) {
        return jx.getParameter(name);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return jx.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        return jx.getParameterValues(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return jx.getParameterMap();
    }

    @Override
    public String getProtocol() {
        return jx.getProtocol();
    }

    @Override
    public String getScheme() {
        return jx.getScheme();
    }

    @Override
    public String getServerName() {
        return jx.getServerName();
    }

    @Override
    public int getServerPort() {
        return jx.getServerPort();
    }

    @Override
    public BufferedReader getReader()
            throws IOException {
        return jx.getReader();
    }

    @Override
    public String getRemoteAddr() {
        return jx.getRemoteAddr();
    }

    @Override
    public String getRemoteHost() {
        return jx.getRemoteHost();
    }

    @Override
    public void setAttribute(String name, Object o) {
        jx.setAttribute(name, o);
    }

    @Override
    public void removeAttribute(String name) {
        jx.removeAttribute(name);
    }

    @Override
    public Locale getLocale() {
        return jx.getLocale();
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return jx.getLocales();
    }

    @Override
    public boolean isSecure() {
        return jx.isSecure();
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return JxRequestDispatcher.jx2a.cachedMap(jx.getRequestDispatcher(path));
    }

    @Deprecated
    @Override
    public String getRealPath(String path) {
        return jx.getRealPath(path);
    }

    @Override
    public int getRemotePort() {
        return jx.getRemotePort();
    }

    @Override
    public String getLocalName() {
        return jx.getLocalName();
    }

    @Override
    public String getLocalAddr() {
        return jx.getLocalAddr();
    }

    @Override
    public int getLocalPort() {
        return jx.getLocalPort();
    }

    @Override
    public ServletContext getServletContext() {
        return JxServletContext.jx2a.cachedMap(jx.getServletContext());
    }

    @Override
    public AsyncContext startAsync()
            throws IllegalStateException {
        return JxAsyncContext.jx2a.cachedMap(jx.startAsync());
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
            throws IllegalStateException {
        return JxAsyncContext.jx2a.cachedMap(//
                jx.startAsync(//
                        JaServletRequest.ja2x.cachedMap(servletRequest), //
                        JaServletResponse.ja2x.cachedMap(servletResponse)));
    }

    @Override
    public boolean isAsyncStarted() {
        return jx.isAsyncStarted();
    }

    @Override
    public boolean isAsyncSupported() {
        return jx.isAsyncSupported();
    }

    @Override
    public AsyncContext getAsyncContext() {
        return JxAsyncContext.jx2a.cachedMap(jx.getAsyncContext());
    }

    @Override
    public DispatcherType getDispatcherType() {
        return JxDispatcherType.jx2a(jx.getDispatcherType());
    }

    public static final MapGlobal<javax.servlet.ServletRequest, ServletRequest> jx2a //
            = MapGlobal.fn(ServletRequest.class, (s) -> new JxServletRequest(s));

}
