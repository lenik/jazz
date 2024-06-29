package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedServlet
        extends AbstractDecorator<Servlet>
        implements
            Servlet {

    private static final long serialVersionUID = 1L;

    public DecoratedServlet(Servlet _orig) {
        super(_orig);
    }

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        getWrapped().init(config);
    }

    @Override
    public ServletConfig getServletConfig() {
        return getWrapped().getServletConfig();
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        getWrapped().service(req, res);
    }

    @Override
    public String getServletInfo() {
        return getWrapped().getServletInfo();
    }

    @Override
    public void destroy() {
        getWrapped().destroy();
    }

}
