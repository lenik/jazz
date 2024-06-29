package net.bodz.bas.c.jakarta.servlet;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import net.bodz.bas.c.javax.servlet.JaServletConfig;
import net.bodz.bas.c.javax.servlet.JaServletRequest;
import net.bodz.bas.c.javax.servlet.JaServletResponse;
import net.bodz.bas.c.util.MapGlobal;

public class JxServlet
        implements
            Servlet {

    javax.servlet.Servlet jx;

    public JxServlet(javax.servlet.Servlet jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        try {
            jx.init(JaServletConfig.ja2x.cachedMap(config));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        try {
            jx.service(//
                    JaServletRequest.ja2x.cachedMap(req), //
                    JaServletResponse.ja2x.cachedMap(res));
        } catch (javax.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return JxServletConfig.jx2a.cachedMap(jx.getServletConfig());
    }

    @Override
    public String getServletInfo() {
        return jx.getServletInfo();
    }

    @Override
    public void destroy() {
        jx.destroy();
    }

    public static final MapGlobal<javax.servlet.Servlet, JxServlet> jx2a //
            = MapGlobal.fn(JxServlet.class, (s) -> new JxServlet(s));

}
