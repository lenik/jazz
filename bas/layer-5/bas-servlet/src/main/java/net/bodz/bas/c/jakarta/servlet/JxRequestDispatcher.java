package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import net.bodz.bas.c.javax.servlet.JaServletRequest;
import net.bodz.bas.c.javax.servlet.JaServletResponse;
import net.bodz.bas.c.util.MapGlobal;

public class JxRequestDispatcher
        implements
            RequestDispatcher {

    javax.servlet.RequestDispatcher jx;

    public JxRequestDispatcher(javax.servlet.RequestDispatcher jx) {
        this.jx = jx;
    }

    @Override
    public void forward(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        try {
            jx.forward(//
                    JaServletRequest.ja2x.cachedMap(request), //
                    JaServletResponse.ja2x.cachedMap(response));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void include(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        try {
            jx.include(//
                    JaServletRequest.ja2x.cachedMap(request), //
                    JaServletResponse.ja2x.cachedMap(response));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    public static final MapGlobal<javax.servlet.RequestDispatcher, RequestDispatcher> jx2a//
            = MapGlobal.fn(RequestDispatcher.class, (s) -> new JxRequestDispatcher(s));

}
