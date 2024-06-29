package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import net.bodz.bas.c.javax.servlet.JaFilterChain;
import net.bodz.bas.c.javax.servlet.JaFilterConfig;
import net.bodz.bas.c.javax.servlet.JaServletRequest;
import net.bodz.bas.c.javax.servlet.JaServletResponse;
import net.bodz.bas.c.util.MapGlobal;

public class JxFilter
        implements
            Filter {

    javax.servlet.Filter jx;

    public JxFilter(javax.servlet.Filter jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        try {
            jx.init(JaFilterConfig.ja2x.cachedMap(filterConfig));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            jx.doFilter(//
                    JaServletRequest.ja2x.cachedMap(request), //
                    JaServletResponse.ja2x.cachedMap(response), //
                    JaFilterChain.ja2x.cachedMap(chain));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        jx.destroy();
    }

    public static final MapGlobal<javax.servlet.Filter, JxFilter> jx2a //
            = MapGlobal.fn(JxFilter.class, (s) -> new JxFilter(s));

}
