package net.bodz.bas.c.jakarta.servlet;

import java.util.Enumeration;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;

import net.bodz.bas.c.util.MapGlobal;

public class JxFilterConfig
        implements
            FilterConfig {

    javax.servlet.FilterConfig jx;

    public JxFilterConfig(javax.servlet.FilterConfig jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public String getFilterName() {
        return jx.getFilterName();
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

    public static final MapGlobal<javax.servlet.FilterConfig, FilterConfig> jx2a //
            = MapGlobal.fn(FilterConfig.class, (s) -> new JxFilterConfig(s));

}
