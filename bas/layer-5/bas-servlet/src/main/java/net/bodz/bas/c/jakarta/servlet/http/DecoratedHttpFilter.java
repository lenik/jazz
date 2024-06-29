package net.bodz.bas.c.jakarta.servlet.http;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedHttpFilter
        extends AbstractDecorator<HttpFilter>
        implements
            HttpFilter {

    private static final long serialVersionUID = 1L;

    public DecoratedHttpFilter(HttpFilter _orig) {
        super(_orig);
    }

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        getWrapped().init(filterConfig);
    }

    @Override
    public void destroy() {
        getWrapped().destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        getWrapped().doFilter(request, response, chain);
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        getWrapped().doFilter(request, response, chain);
    }

}
