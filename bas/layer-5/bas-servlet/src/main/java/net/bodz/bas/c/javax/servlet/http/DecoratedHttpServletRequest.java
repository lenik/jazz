package net.bodz.bas.c.javax.servlet.http;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import net.bodz.bas.c.javax.servlet.DecoratedServletRequest;

public class DecoratedHttpServletRequest
        extends DecoratedServletRequest
        implements
            HttpServletRequest {

    private static final long serialVersionUID = 1L;

    public DecoratedHttpServletRequest(HttpServletRequest _orig) {
        super(_orig);
    }

    @Override
    public HttpServletRequest getWrapped() {
        return (HttpServletRequest) super.getWrapped();
    }

    @Override
    public String getAuthType() {
        return getWrapped().getAuthType();
    }

    @Override
    public Cookie[] getCookies() {
        return getWrapped().getCookies();
    }

    @Override
    public long getDateHeader(String name) {
        return getWrapped().getDateHeader(name);
    }

    @Override
    public String getHeader(String name) {
        return getWrapped().getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        return getWrapped().getHeaders(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return getWrapped().getHeaderNames();
    }

    @Override
    public int getIntHeader(String name) {
        return getWrapped().getIntHeader(name);
    }

    @Override
    public String getMethod() {
        return getWrapped().getMethod();
    }

    @Override
    public String getPathInfo() {
        return getWrapped().getPathInfo();
    }

    @Override
    public String getPathTranslated() {
        return getWrapped().getPathTranslated();
    }

    @Override
    public String getContextPath() {
        return getWrapped().getContextPath();
    }

    @Override
    public String getQueryString() {
        return getWrapped().getQueryString();
    }

    @Override
    public String getRemoteUser() {
        return getWrapped().getRemoteUser();
    }

    @Override
    public boolean isUserInRole(String role) {
        return getWrapped().isUserInRole(role);
    }

    @Override
    public Principal getUserPrincipal() {
        return getWrapped().getUserPrincipal();
    }

    @Override
    public String getRequestedSessionId() {
        return getWrapped().getRequestedSessionId();
    }

    @Override
    public String getRequestURI() {
        return getWrapped().getRequestURI();
    }

    @Override
    public StringBuffer getRequestURL() {
        return getWrapped().getRequestURL();
    }

    @Override
    public String getServletPath() {
        return getWrapped().getServletPath();
    }

    @Override
    public HttpSession getSession(boolean create) {
        return getWrapped().getSession(create);
    }

    @Override
    public HttpSession getSession() {
        return getWrapped().getSession();
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return getWrapped().isRequestedSessionIdValid();
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return getWrapped().isRequestedSessionIdFromCookie();
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return getWrapped().isRequestedSessionIdFromURL();
    }

    @Deprecated
    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return getWrapped().isRequestedSessionIdFromUrl();
    }

    @Override
    public boolean authenticate(HttpServletResponse response)
            throws IOException, ServletException {
        return getWrapped().authenticate(response);
    }

    @Override
    public void login(String username, String password)
            throws ServletException {
        getWrapped().login(username, password);
    }

    @Override
    public void logout()
            throws ServletException {
        getWrapped().logout();
    }

    @Override
    public Collection<Part> getParts()
            throws IOException, ServletException {
        return getWrapped().getParts();
    }

    @Override
    public Part getPart(String name)
            throws IOException, ServletException {
        return getWrapped().getPart(name);
    }

    @Override
    public String changeSessionId() {
        return getWrapped().changeSessionId();
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass)
            throws IOException, ServletException {
        return getWrapped().upgrade(handlerClass);
    }

}
