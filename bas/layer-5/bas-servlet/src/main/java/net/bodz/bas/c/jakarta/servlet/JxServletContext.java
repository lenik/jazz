package net.bodz.bas.c.jakarta.servlet;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.descriptor.JspConfigDescriptor;

import net.bodz.bas.c.javax.servlet.JaFilter;
import net.bodz.bas.c.javax.servlet.JaServlet;
import net.bodz.bas.c.javax.servlet.JaSessionTrackingMode;
import net.bodz.bas.c.util.ClassNotSupportException;
import net.bodz.bas.c.util.MapGlobal;
import net.bodz.bas.t.coll.TransformedSet;
import net.bodz.bas.t.coll.ValueTransformedMap;
import net.bodz.bas.t.iterator.Enumerations;

public class JxServletContext
        implements
            ServletContext {

    javax.servlet.ServletContext jx;

    public JxServletContext(javax.servlet.ServletContext jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public String getContextPath() {
        return jx.getContextPath();
    }

    @Override
    public ServletContext getContext(String uripath) {
        return JxServletContext.jx2a.cachedMap(jx.getContext(uripath));
    }

    @Override
    public int getMajorVersion() {
        return jx.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return jx.getMinorVersion();
    }

    @Override
    public int getEffectiveMajorVersion() {
        return jx.getEffectiveMajorVersion();
    }

    @Override
    public int getEffectiveMinorVersion() {
        return jx.getEffectiveMinorVersion();
    }

    @Override
    public String getMimeType(String file) {
        return jx.getMimeType(file);
    }

    @Override
    public Set<String> getResourcePaths(String path) {
        return jx.getResourcePaths(path);
    }

    @Override
    public URL getResource(String path)
            throws MalformedURLException {
        return jx.getResource(path);
    }

    @Override
    public InputStream getResourceAsStream(String path) {
        return jx.getResourceAsStream(path);
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return JxRequestDispatcher.jx2a.cachedMap(jx.getRequestDispatcher(path));
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        return JxRequestDispatcher.jx2a.cachedMap(jx.getNamedDispatcher(name));
    }

    @Deprecated
    @Override
    public Servlet getServlet(String name)
            throws ServletException {
        try {
            return JxServlet.jx2a.cachedMap(jx.getServlet(name));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Deprecated
    @Override
    public Enumeration<Servlet> getServlets() {
        return Enumerations.transform(jx.getServlets(), (s) -> JxServlet.jx2a.cachedMap(s));
    }

    @Deprecated
    @Override
    public Enumeration<String> getServletNames() {
        return jx.getServletNames();
    }

    @Override
    public void log(String msg) {
        jx.log(msg);
    }

    @Deprecated
    @Override
    public void log(Exception exception, String msg) {
        jx.log(exception, msg);
    }

    @Override
    public void log(String message, Throwable throwable) {
        jx.log(message, throwable);
    }

    @Override
    public String getRealPath(String path) {
        return jx.getRealPath(path);
    }

    @Override
    public String getServerInfo() {
        return jx.getServerInfo();
    }

    @Override
    public String getInitParameter(String name) {
        return jx.getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return jx.getInitParameterNames();
    }

    @Override
    public boolean setInitParameter(String name, String value) {
        return jx.setInitParameter(name, value);
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
    public void setAttribute(String name, Object object) {
        jx.setAttribute(name, object);
    }

    @Override
    public void removeAttribute(String name) {
        jx.removeAttribute(name);
    }

    @Override
    public String getServletContextName() {
        return jx.getServletContextName();
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, String className) {
        return JxServletRegistration.JxDynamic.jx2a.cachedMap(jx.addServlet(servletName, className));
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
        return JxServletRegistration.JxDynamic.jx2a.cachedMap(//
                jx.addServlet(servletName, //
                        JaServlet.ja2x.cachedMap(servlet)));
    }

    @Deprecated
    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
        throw new ClassNotSupportException();
    }

    public ServletRegistration.Dynamic addJxServlet(String servletName,
            Class<? extends javax.servlet.Servlet> servletClass) {
        return JxServletRegistration.JxDynamic.jx2a.cachedMap(//
                jx.addServlet(servletName, servletClass));
    }

    @Override
    public ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
        return JxServletRegistration.JxDynamic.jx2a.cachedMap(//
                jx.addJspFile(servletName, jspFile));
    }

    @Override
    public <T extends Servlet> T createServlet(Class<T> clazz)
            throws ServletException {
        throw new ClassNotSupportException();
    }

    public <T extends javax.servlet.Servlet> T createJxServlet(Class<T> clazz)
            throws ServletException {
        try {
            return jx.createServlet(clazz);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public ServletRegistration getServletRegistration(String servletName) {
        return JxServletRegistration.jx2a.cachedMap(jx.getServletRegistration(servletName));
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        return ValueTransformedMap.transform(jx.getServletRegistrations(), //
                (x) -> JxServletRegistration.jx2a.cachedMap(x), //
                ServletRegistration.class);
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return JxFilterRegistration.JxDynamic.jx2a.cachedMap(jx.addFilter(filterName, className));
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
        return JxFilterRegistration.JxDynamic.jx2a.cachedMap( //
                jx.addFilter(filterName, JaFilter.ja2x.cachedMap(filter)));
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass) {
        throw new ClassNotSupportException();
    }

    public FilterRegistration.Dynamic addJxFilter(String filterName,
            Class<? extends javax.servlet.Filter> filterClass) {
        return JxFilterRegistration.JxDynamic.jx2a.cachedMap( //
                jx.addFilter(filterName, filterClass));
    }

    @Override
    public <T extends Filter> T createFilter(Class<T> clazz)
            throws ServletException {
        // return jx.createFilter(clazz);
        throw new ClassNotSupportException();
    }

    public <T extends javax.servlet.Filter> T createJxFilter(Class<T> clazz)
            throws ServletException {
        try {
            return jx.createFilter(clazz);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public FilterRegistration getFilterRegistration(String filterName) {
        return JxFilterRegistration.jx2a.cachedMap(jx.getFilterRegistration(filterName));
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        return ValueTransformedMap.transform(jx.getFilterRegistrations(), //
                (x) -> JxFilterRegistration.jx2a.cachedMap(x), FilterRegistration.class);
    }

    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        return JxSessionCookieConfig.jx2a.cachedMap(jx.getSessionCookieConfig());
    }

    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
        jx.setSessionTrackingModes(//
                TransformedSet.transform(sessionTrackingModes, //
                        (a) -> JaSessionTrackingMode.ja2x(a), javax.servlet.SessionTrackingMode.class));
    }

    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        return TransformedSet.transform(jx.getDefaultSessionTrackingModes(), //
                (x) -> JxSessionTrackingMode.jx2a(x), SessionTrackingMode.class);
    }

    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return TransformedSet.transform(jx.getEffectiveSessionTrackingModes(), //
                (x) -> JxSessionTrackingMode.jx2a(x), SessionTrackingMode.class);
    }

    @Override
    public void addListener(String className) {
        jx.addListener(className);
    }

    @Override
    public <T extends EventListener> void addListener(T t) {
        jx.addListener(t);
    }

    @Override
    public void addListener(Class<? extends EventListener> listenerClass) {
        jx.addListener(listenerClass);
    }

    @Override
    public <T extends EventListener> T createListener(Class<T> clazz)
            throws ServletException {
        try {
            return jx.createListener(clazz);
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        return JxJspConfigDescriptor.jx2a.cachedMap(jx.getJspConfigDescriptor());
    }

    @Override
    public ClassLoader getClassLoader() {
        return jx.getClassLoader();
    }

    @Override
    public void declareRoles(String... roleNames) {
        jx.declareRoles(roleNames);
    }

    @Override
    public String getVirtualServerName() {
        return jx.getVirtualServerName();
    }

    @Override
    public int getSessionTimeout() {
        return jx.getSessionTimeout();
    }

    @Override
    public void setSessionTimeout(int sessionTimeout) {
        jx.setSessionTimeout(sessionTimeout);
    }

    @Override
    public String getRequestCharacterEncoding() {
        return jx.getRequestCharacterEncoding();
    }

    @Override
    public void setRequestCharacterEncoding(String encoding) {
        jx.setRequestCharacterEncoding(encoding);
    }

    @Override
    public String getResponseCharacterEncoding() {
        return jx.getResponseCharacterEncoding();
    }

    @Override
    public void setResponseCharacterEncoding(String encoding) {
        jx.setResponseCharacterEncoding(encoding);
    }

    public static final MapGlobal<javax.servlet.ServletContext, JxServletContext> jx2a //
            = MapGlobal.fn(JxServletContext.class, (s) -> new JxServletContext(s));

}
