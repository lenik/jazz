package net.bodz.bas.c.jakarta.servlet;

import java.util.Enumeration;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;

import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedServletConfig
        extends AbstractDecorator<ServletConfig>
        implements
            ServletConfig {

    private static final long serialVersionUID = 1L;

    public DecoratedServletConfig(ServletConfig _orig) {
        super(_orig);
    }

    @Override
    public String getServletName() {
        return getWrapped().getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return getWrapped().getServletContext();
    }

    @Override
    public String getInitParameter(String name) {
        return getWrapped().getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return getWrapped().getInitParameterNames();
    }

}
