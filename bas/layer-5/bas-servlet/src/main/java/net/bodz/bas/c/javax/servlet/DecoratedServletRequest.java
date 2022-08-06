package net.bodz.bas.c.javax.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.*;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedServletRequest
        extends AbstractDecorator<ServletRequest>
        implements ServletRequest, IAttributes {

    private static final long serialVersionUID = 1L;

    public DecoratedServletRequest(ServletRequest _orig) {
        super(_orig);
    }

    @Override
    public Object getAttribute(String name) {
        return getWrapped().getAttribute(name);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return getWrapped().getAttributeNames();
    }

    @Override
    public String getCharacterEncoding() {
        return getWrapped().getCharacterEncoding();
    }

    @Override
    public void setCharacterEncoding(String env)
            throws UnsupportedEncodingException {
        getWrapped().setCharacterEncoding(env);
    }

    @Override
    public int getContentLength() {
        return getWrapped().getContentLength();
    }

    @Override
    public long getContentLengthLong() {
        return getWrapped().getContentLengthLong();
    }

    @Override
    public String getContentType() {
        return getWrapped().getContentType();
    }

    @Override
    public ServletInputStream getInputStream()
            throws IOException {
        return getWrapped().getInputStream();
    }

    @Override
    public String getParameter(String name) {
        return getWrapped().getParameter(name);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return getWrapped().getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        return getWrapped().getParameterValues(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return getWrapped().getParameterMap();
    }

    @Override
    public String getProtocol() {
        return getWrapped().getProtocol();
    }

    @Override
    public String getScheme() {
        return getWrapped().getScheme();
    }

    @Override
    public String getServerName() {
        return getWrapped().getServerName();
    }

    @Override
    public int getServerPort() {
        return getWrapped().getServerPort();
    }

    @Override
    public BufferedReader getReader()
            throws IOException {
        return getWrapped().getReader();
    }

    @Override
    public String getRemoteAddr() {
        return getWrapped().getRemoteAddr();
    }

    @Override
    public String getRemoteHost() {
        return getWrapped().getRemoteHost();
    }

    @Override
    public void setAttribute(String name, Object o) {
        getWrapped().setAttribute(name, o);
    }

    @Override
    public void removeAttribute(String name) {
        getWrapped().removeAttribute(name);
    }

    @Override
    public Locale getLocale() {
        return getWrapped().getLocale();
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return getWrapped().getLocales();
    }

    @Override
    public boolean isSecure() {
        return getWrapped().isSecure();
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return getWrapped().getRequestDispatcher(path);
    }

    @Override
    @Deprecated
    public String getRealPath(String path) {
        return getWrapped().getRealPath(path);
    }

    @Override
    public int getRemotePort() {
        return getWrapped().getRemotePort();
    }

    @Override
    public String getLocalName() {
        return getWrapped().getLocalName();
    }

    @Override
    public String getLocalAddr() {
        return getWrapped().getLocalAddr();
    }

    @Override
    public int getLocalPort() {
        return getWrapped().getLocalPort();
    }

    @Override
    public ServletContext getServletContext() {
        return getWrapped().getServletContext();
    }

    @Override
    public AsyncContext startAsync()
            throws IllegalStateException {
        return getWrapped().startAsync();
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
            throws IllegalStateException {
        return getWrapped().startAsync(servletRequest, servletResponse);
    }

    @Override
    public boolean isAsyncStarted() {
        return getWrapped().isAsyncStarted();
    }

    @Override
    public boolean isAsyncSupported() {
        return getWrapped().isAsyncSupported();
    }

    @Override
    public AsyncContext getAsyncContext() {
        return getWrapped().getAsyncContext();
    }

    @Override
    public DispatcherType getDispatcherType() {
        return getWrapped().getDispatcherType();
    }

}
