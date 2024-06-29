package net.bodz.bas.c.jakarta.servlet;

import java.util.Enumeration;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;

import net.bodz.bas.c.util.MapGlobal;

public class JxServletConfig
        implements
            ServletConfig {

    javax.servlet.ServletConfig jx;

    public JxServletConfig(javax.servlet.ServletConfig jx) {
        this.jx = jx;
    }

    @Override
    public String getServletName() {
        return jx.getServletName();
    }

    @Override
    public ServletContext getServletContext() {
        return JxServletContext.jx2a.cachedMap(jx.getServletContext());
    }

    @Override
    public String getInitParameter(String name) {
        return jx.getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return jx.getInitParameterNames();
    }

    public static final MapGlobal<javax.servlet.ServletConfig, ServletConfig> jx2a //
            = MapGlobal.fn(ServletConfig.class, (s) -> new JxServletConfig(s));

}
