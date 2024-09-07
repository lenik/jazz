package net.bodz.bas.c.javax.servlet;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.descriptor.JspConfigDescriptor;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedServletContext
        extends AbstractDecorator<ServletContext>
        implements ServletContext, IAttributes {

    private static final long serialVersionUID = 1L;

    public DecoratedServletContext(ServletContext _orig) {
        super(_orig);
    }

    @Override
    public String getContextPath() {
        return getWrapped().getContextPath();
    }

    @Override
    public ServletContext getContext(String uripath) {
        return getWrapped().getContext(uripath);
    }

    @Override
    public int getMajorVersion() {
        return getWrapped().getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return getWrapped().getMinorVersion();
    }

    @Override
    public int getEffectiveMajorVersion() {
        return getWrapped().getEffectiveMajorVersion();
    }

    @Override
    public int getEffectiveMinorVersion() {
        return getWrapped().getEffectiveMinorVersion();
    }

    @Override
    public String getMimeType(String file) {
        return getWrapped().getMimeType(file);
    }

    @Override
    public Set<String> getResourcePaths(String path) {
        return getWrapped().getResourcePaths(path);
    }

    @Override
    public URL getResource(String path)
            throws MalformedURLException {
        return getWrapped().getResource(path);
    }

    @Override
    public InputStream getResourceAsStream(String path) {
        return getWrapped().getResourceAsStream(path);
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return getWrapped().getRequestDispatcher(path);
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        return getWrapped().getNamedDispatcher(name);
    }

    @Override
    @Deprecated
    public Servlet getServlet(String name)
            throws ServletException {
        return getWrapped().getServlet(name);
    }

    @Override
    @Deprecated
    public Enumeration<Servlet> getServlets() {
        return getWrapped().getServlets();
    }

    @Override
    @Deprecated
    public Enumeration<String> getServletNames() {
        return getWrapped().getServletNames();
    }

    @Override
    public void log(String msg) {
        getWrapped().log(msg);
    }

    @Deprecated
    @Override
    public void log(Exception exception, String msg) {
        getWrapped().log(exception, msg);
    }

    @Override
    public void log(String message, Throwable throwable) {
        getWrapped().log(message, throwable);
    }

    @Override
    public String getRealPath(String path) {
        return getWrapped().getRealPath(path);
    }

    @Override
    public String getServerInfo() {
        return getWrapped().getServerInfo();
    }

    @Override
    public String getInitParameter(String name) {
        return getWrapped().getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return getWrapped().getInitParameterNames();
    }

    @Override
    public boolean setInitParameter(String name, String value) {
        return getWrapped().setInitParameter(name, value);
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
    public void setAttribute(String name, Object object) {
        getWrapped().setAttribute(name, object);
    }

    @Override
    public void removeAttribute(String name) {
        getWrapped().removeAttribute(name);
    }

    @Override
    public String getServletContextName() {
        return getWrapped().getServletContextName();
    }

    @Override
    public Dynamic addServlet(String servletName, String className) {
        return getWrapped().addServlet(servletName, className);
    }

    @Override
    public Dynamic addServlet(String servletName, Servlet servlet) {
        return getWrapped().addServlet(servletName, servlet);
    }

    @Override
    public Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
        return getWrapped().addServlet(servletName, servletClass);
    }

    @Override
    public <T extends Servlet> T createServlet(Class<T> clazz)
            throws ServletException {
        return getWrapped().createServlet(clazz);
    }

    @Override
    public ServletRegistration getServletRegistration(String servletName) {
        return getWrapped().getServletRegistration(servletName);
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        return getWrapped().getServletRegistrations();
    }

    @Override
    public javax.servlet.FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return getWrapped().addFilter(filterName, className);
    }

    @Override
    public javax.servlet.FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
        return getWrapped().addFilter(filterName, filter);
    }

    @Override
    public javax.servlet.FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass) {
        return getWrapped().addFilter(filterName, filterClass);
    }

    @Override
    public <T extends Filter> T createFilter(Class<T> clazz)
            throws ServletException {
        return getWrapped().createFilter(clazz);
    }

    @Override
    public FilterRegistration getFilterRegistration(String filterName) {
        return getWrapped().getFilterRegistration(filterName);
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        return getWrapped().getFilterRegistrations();
    }

    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        return getWrapped().getSessionCookieConfig();
    }

    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
        getWrapped().setSessionTrackingModes(sessionTrackingModes);
    }

    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        return getWrapped().getDefaultSessionTrackingModes();
    }

    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return getWrapped().getEffectiveSessionTrackingModes();
    }

    @Override
    public void addListener(String className) {
        getWrapped().addListener(className);
    }

    @Override
    public <T extends EventListener> void addListener(T t) {
        getWrapped().addListener(t);
    }

    @Override
    public void addListener(Class<? extends EventListener> listenerClass) {
        getWrapped().addListener(listenerClass);
    }

    @Override
    public <T extends EventListener> T createListener(Class<T> clazz)
            throws ServletException {
        return getWrapped().createListener(clazz);
    }

    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        return getWrapped().getJspConfigDescriptor();
    }

    @Override
    public ClassLoader getClassLoader() {
        return getWrapped().getClassLoader();
    }

    @Override
    public void declareRoles(String... roleNames) {
        getWrapped().declareRoles(roleNames);
    }

}
