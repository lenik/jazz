package net.bodz.bas.c.javax.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.bodz.bas.c.jakarta.servlet.JxFilterChain;
import net.bodz.bas.c.jakarta.servlet.JxFilterConfig;
import net.bodz.bas.c.jakarta.servlet.JxServletRequest;
import net.bodz.bas.c.jakarta.servlet.JxServletResponse;
import net.bodz.bas.c.util.MapGlobal;

public class JaFilter
        implements
            Filter {

    jakarta.servlet.Filter ja;

    public JaFilter(jakarta.servlet.Filter ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        try {
            ja.init(JxFilterConfig.jx2a.cachedMap(filterConfig));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            ja.doFilter(//
                    JxServletRequest.jx2a.cachedMap(request), //
                    JxServletResponse.jx2a.cachedMap(response), //
                    JxFilterChain.jx2a.cachedMap(chain));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        ja.destroy();
    }

    public static final MapGlobal<jakarta.servlet.Filter, JaFilter> ja2x //
            = MapGlobal.fn(JaFilter.class, (s) -> new JaFilter(s));

}
