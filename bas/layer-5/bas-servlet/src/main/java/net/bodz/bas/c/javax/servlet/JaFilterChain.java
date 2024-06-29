package net.bodz.bas.c.javax.servlet;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.bodz.bas.c.jakarta.servlet.JxServletRequest;
import net.bodz.bas.c.jakarta.servlet.JxServletResponse;
import net.bodz.bas.c.util.MapGlobal;

public class JaFilterChain
        implements
            FilterChain {

    jakarta.servlet.FilterChain ja;

    public JaFilterChain(jakarta.servlet.FilterChain ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        try {
            ja.doFilter(//
                    JxServletRequest.jx2a.cachedMap(request), //
                    JxServletResponse.jx2a.cachedMap(response));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    public static final MapGlobal<jakarta.servlet.FilterChain, FilterChain> ja2x //
            = MapGlobal.fn(FilterChain.class, (s) -> new JaFilterChain(s));

}
