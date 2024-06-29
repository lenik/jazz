package net.bodz.bas.c.javax.servlet;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import net.bodz.bas.c.util.MapGlobal;

public class JaServletConfig
        implements
            ServletConfig {

    jakarta.servlet.ServletConfig ja;

    public JaServletConfig(jakarta.servlet.ServletConfig ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public String getServletName() {
        return ja.getServletName();
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

    public static final MapGlobal<jakarta.servlet.ServletConfig, ServletConfig> ja2x //
            = MapGlobal.fn(ServletConfig.class, (s) -> new JaServletConfig(s));

}
