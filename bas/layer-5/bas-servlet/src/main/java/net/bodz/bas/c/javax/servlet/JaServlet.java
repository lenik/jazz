package net.bodz.bas.c.javax.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.bodz.bas.c.jakarta.servlet.JxServletConfig;
import net.bodz.bas.c.jakarta.servlet.JxServletRequest;
import net.bodz.bas.c.jakarta.servlet.JxServletResponse;
import net.bodz.bas.c.util.MapGlobal;

public class JaServlet
        implements
            Servlet {

    jakarta.servlet.Servlet ja;

    public JaServlet(jakarta.servlet.Servlet ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public void init(ServletConfig config)
            throws ServletException {
        try {
            ja.init(JxServletConfig.jx2a.cachedMap(config));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res)
            throws ServletException, IOException {
        try {
            ja.service(//
                    JxServletRequest.jx2a.cachedMap(req), //
                    JxServletResponse.jx2a.cachedMap(res));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public ServletConfig getServletConfig() {
        return JaServletConfig.ja2x.cachedMap(ja.getServletConfig());
    }

    @Override
    public String getServletInfo() {
        return ja.getServletInfo();
    }

    @Override
    public void destroy() {
        ja.destroy();
    }

    public static final MapGlobal<jakarta.servlet.Servlet, JaServlet> ja2x //
            = MapGlobal.fn(JaServlet.class, (s) -> new JaServlet(s));

}
