package net.bodz.bas.c.jakarta.servlet.http;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedHttpServlet
        extends AbstractDecorator<HttpServlet>
        implements
            HttpServletRequest {

    private static final long serialVersionUID = 1L;

    public DecoratedHttpServlet(HttpServlet _orig) {
        super(_orig);
    }

    @Override
    public int hashCode() {
        return getWrapped().hashCode();
    }

    public void destroy() {
        getWrapped().destroy();
    }

    public String getInitParameter(String name) {
        return getWrapped().getInitParameter(name);
    }

    @Override
    public boolean equals(Object obj) {
        return getWrapped().equals(obj);
    }

    public Enumeration<String> getInitParameterNames() {
        return getWrapped().getInitParameterNames();
    }

    public ServletConfig getServletConfig() {
        return getWrapped().getServletConfig();
    }

    @Override
    public ServletContext getServletContext() {
        return getWrapped().getServletContext();
    }

    public String getServletInfo() {
        return getWrapped().getServletInfo();
    }

    public void init(ServletConfig config)
            throws ServletException {
        getWrapped().init(config);
    }

    public void init()
            throws ServletException {
        getWrapped().init();
    }

    public void log(String msg) {
        getWrapped().log(msg);
    }

    public void log(String message, Throwable t) {
        getWrapped().log(message, t);
    }

    public String getServletName() {
        return getWrapped().getServletName();
    }

    @Override
    public String toString() {
        return getWrapped().toString();
    }

    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        getWrapped().service(req, res);
    }

}
