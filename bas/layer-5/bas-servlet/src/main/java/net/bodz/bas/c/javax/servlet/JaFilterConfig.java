package net.bodz.bas.c.javax.servlet;

import java.util.Enumeration;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import net.bodz.bas.c.util.MapGlobal;

public class JaFilterConfig
        implements
            FilterConfig {

    jakarta.servlet.FilterConfig ja;

    public JaFilterConfig(jakarta.servlet.FilterConfig ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public String getFilterName() {
        return ja.getFilterName();
    }

    @Override
    public ServletContext getServletContext() {
        return JaServletContext.ja2x.cachedMap(ja.getServletContext());
    }

    @Override
    public String getInitParameter(String name) {
        return ja.getInitParameter(name);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return ja.getInitParameterNames();
    }

    public static final MapGlobal<jakarta.servlet.FilterConfig, FilterConfig> ja2x //
            = MapGlobal.fn(FilterConfig.class, (s) -> new JaFilterConfig(s));

}
