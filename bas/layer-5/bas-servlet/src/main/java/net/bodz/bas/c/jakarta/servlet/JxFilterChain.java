package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import net.bodz.bas.c.javax.servlet.JaServletRequest;
import net.bodz.bas.c.javax.servlet.JaServletResponse;
import net.bodz.bas.c.util.MapGlobal;

public class JxFilterChain
        implements
            FilterChain {

    javax.servlet.FilterChain jx;

    public JxFilterChain(javax.servlet.FilterChain jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        try {
            jx.doFilter(//
                    JaServletRequest.ja2x.cachedMap(request), //
                    JaServletResponse.ja2x.cachedMap(response));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    public static final MapGlobal<javax.servlet.FilterChain, FilterChain> jx2a //
            = MapGlobal.fn(FilterChain.class, (s) -> new JxFilterChain(s));

}
