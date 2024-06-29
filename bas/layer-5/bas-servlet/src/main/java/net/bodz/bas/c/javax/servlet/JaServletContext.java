package net.bodz.bas.c.javax.servlet;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.descriptor.JspConfigDescriptor;

import net.bodz.bas.c.jakarta.servlet.JxFilter;
import net.bodz.bas.c.jakarta.servlet.JxServlet;
import net.bodz.bas.c.jakarta.servlet.JxSessionTrackingMode;
import net.bodz.bas.c.util.ClassNotSupportException;
import net.bodz.bas.c.util.MapGlobal;
import net.bodz.bas.t.coll.TransformedSet;
import net.bodz.bas.t.coll.ValueTransformedMap;
import net.bodz.bas.t.iterator.Enumerations;

public class JaServletContext
        implements
            ServletContext {

    jakarta.servlet.ServletContext ja;

    public JaServletContext(jakarta.servlet.ServletContext ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public String getContextPath() {
        return ja.getContextPath();
    }

    @Override
    public ServletContext getContext(String uripath) {
        return ja2x.cachedMap(ja.getContext(uripath));
    }

    @Override
    public int getMajorVersion() {
        return ja.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return ja.getMinorVersion();
    }

    @Override
    public int getEffectiveMajorVersion() {
        return ja.getEffectiveMajorVersion();
    }

    @Override
    public int getEffectiveMinorVersion() {
        return ja.getEffectiveMinorVersion();
    }

    @Override
    public String getMimeType(String file) {
        return ja.getMimeType(file);
    }

    @Override
    public Set<String> getResourcePaths(String path) {
        return ja.getResourcePaths(path);
    }

    @Override
    public URL getResource(String path)
            throws MalformedURLException {
        return ja.getResource(path);
    }

    @Override
    public InputStream getResourceAsStream(String path) {
        return ja.getResourceAsStream(path);
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String path) {
        return JaRequestDispatcher.ja2x.cachedMap(ja.getRequestDispatcher(path));
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String name) {
        return JaRequestDispatcher.ja2x.cachedMap(ja.getNamedDispatcher(name));
    }

    @Deprecated
    @Override
    public Servlet getServlet(String name)
            throws ServletException {
        try {
            return JaServlet.ja2x.cachedMap(ja.getServlet(name));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Deprecated
    @Override
    public Enumeration<Servlet> getServlets() {
        return Enumerations.transform(ja.getServlets(), (s) -> JaServlet.ja2x.cachedMap(s));
    }

    @Deprecated
    @Override
    public Enumeration<String> getServletNames() {
        return ja.getServletNames();
    }

    @Override
    public void log(String msg) {
        ja.log(msg);
    }

    @Deprecated
    @Override
    public void log(Exception exception, String msg) {
        ja.log(exception, msg);
    }

    @Override
    public void log(String message, Throwable throwable) {
        ja.log(message, throwable);
    }

    @Override
    public String getRealPath(String path) {
        return ja.getRealPath(path);
    }

    @Override
    public String getServerInfo() {
        return ja.getServerInfo();
    }

    @Override
    public String getInitParameter(String name) {
        return ja.getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return ja.getInitParameterNames();
    }

    @Override
    public boolean setInitParameter(String name, String value) {
        return ja.setInitParameter(name, value);
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
    public void setAttribute(String name, Object object) {
        ja.setAttribute(name, object);
    }

    @Override
    public void removeAttribute(String name) {
        ja.removeAttribute(name);
    }

    @Override
    public String getServletContextName() {
        return ja.getServletContextName();
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, String className) {
        return JaServletRegistration.JaDynamic.ja2x.cachedMap(ja.addServlet(servletName, className));
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
        return JaServletRegistration.JaDynamic.ja2x.cachedMap(//
                ja.addServlet(servletName, //
                        JxServlet.jx2a.cachedMap(servlet)));
    }

    @Override
    public Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
        throw new ClassNotSupportException();
    }

    public ServletRegistration.Dynamic addJaServlet(String servletName,
            Class<? extends jakarta.servlet.Servlet> servletClass) {
        return JaServletRegistration.JaDynamic.ja2x.cachedMap(//
                ja.addServlet(servletName, servletClass));
    }

    @Override
    public ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
        return JaServletRegistration.JaDynamic.ja2x.cachedMap(//
                ja.addJspFile(servletName, jspFile));
    }

    @Override
    public <T extends Servlet> T createServlet(Class<T> clazz)
            throws ServletException {
        throw new ClassNotSupportException();
    }

    public <T extends jakarta.servlet.Servlet> T createJaServlet(Class<T> clazz)
            throws ServletException {
        try {
            return ja.createServlet(clazz);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public ServletRegistration getServletRegistration(String servletName) {
        return JaServletRegistration.ja2x.cachedMap(ja.getServletRegistration(servletName));
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        return ValueTransformedMap.transform(ja.getServletRegistrations(), //
                (x) -> JaServletRegistration.ja2x.cachedMap(x), //
                ServletRegistration.class);
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return JaFilterRegistration.JaDynamic.ja2x.cachedMap(ja.addFilter(filterName, className));
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
        return JaFilterRegistration.JaDynamic.ja2x.cachedMap(//
                ja.addFilter(filterName, JxFilter.jx2a.cachedMap(filter)));
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass) {
        throw new ClassNotSupportException();
    }

    public FilterRegistration.Dynamic addJaFilter(String filterName,
            Class<? extends jakarta.servlet.Filter> filterClass) {
        return JaFilterRegistration.JaDynamic.ja2x.cachedMap(//
                ja.addFilter(filterName, filterClass));
    }

    @Override
    public <T extends Filter> T createFilter(Class<T> clazz)
            throws ServletException {
        throw new ClassNotSupportException();
    }

    public <T extends jakarta.servlet.Filter> T createJaFilter(Class<T> clazz)
            throws ServletException {
        try {
            return ja.createFilter(clazz);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public FilterRegistration getFilterRegistration(String filterName) {
        return JaFilterRegistration.ja2x.cachedMap(ja.getFilterRegistration(filterName));
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        return ValueTransformedMap.transform(ja.getFilterRegistrations(), //
                (a) -> JaFilterRegistration.ja2x.cachedMap(a), FilterRegistration.class);
    }

    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        return JaSessionCookieConfig.jx2a.cachedMap(ja.getSessionCookieConfig());
    }

    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> sessionTrackingModes) {
        ja.setSessionTrackingModes(//
                TransformedSet.transform(sessionTrackingModes, //
                        (jx) -> JxSessionTrackingMode.jx2a(jx), jakarta.servlet.SessionTrackingMode.class));
    }

    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        return TransformedSet.transform(ja.getDefaultSessionTrackingModes(), //
                (s) -> JaSessionTrackingMode.ja2x(s), SessionTrackingMode.class);
    }

    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return TransformedSet.transform(ja.getEffectiveSessionTrackingModes(), //
                (s) -> JaSessionTrackingMode.ja2x(s), SessionTrackingMode.class);
    }

    @Override
    public void addListener(String className) {
        ja.addListener(className);
    }

    @Override
    public <T extends EventListener> void addListener(T t) {
        ja.addListener(t);
    }

    @Override
    public void addListener(Class<? extends EventListener> listenerClass) {
        ja.addListener(listenerClass);
    }

    @Override
    public <T extends EventListener> T createListener(Class<T> clazz)
            throws ServletException {
        try {
            return ja.createListener(clazz);
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        return JaJspConfigDescriptor.ja2x.cachedMap(ja.getJspConfigDescriptor());
    }

    @Override
    public ClassLoader getClassLoader() {
        return ja.getClassLoader();
    }

    @Override
    public void declareRoles(String... roleNames) {
        ja.declareRoles(roleNames);
    }

    @Override
    public String getVirtualServerName() {
        return ja.getVirtualServerName();
    }

    @Override
    public int getSessionTimeout() {
        return ja.getSessionTimeout();
    }

    @Override
    public void setSessionTimeout(int sessionTimeout) {
        ja.setSessionTimeout(sessionTimeout);
    }

    @Override
    public String getRequestCharacterEncoding() {
        return ja.getRequestCharacterEncoding();
    }

    @Override
    public void setRequestCharacterEncoding(String encoding) {
        ja.setRequestCharacterEncoding(encoding);
    }

    @Override
    public String getResponseCharacterEncoding() {
        return ja.getResponseCharacterEncoding();
    }

    @Override
    public void setResponseCharacterEncoding(String encoding) {
        ja.setResponseCharacterEncoding(encoding);
    }

    public static final MapGlobal<jakarta.servlet.ServletContext, ServletContext> ja2x //
            = MapGlobal.fn(ServletContext.class, (s) -> new JaServletContext(s));

}
