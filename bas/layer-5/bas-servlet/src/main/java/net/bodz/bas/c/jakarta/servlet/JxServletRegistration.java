package net.bodz.bas.c.jakarta.servlet;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletSecurityElement;

import net.bodz.bas.c.javax.servlet.JaMultipartConfigElement;
import net.bodz.bas.c.javax.servlet.JaServletSecurityElement;
import net.bodz.bas.c.util.MapGlobal;

public class JxServletRegistration
        implements
            ServletRegistration {

    javax.servlet.ServletRegistration jx;

    public JxServletRegistration(javax.servlet.ServletRegistration jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public Set<String> addMapping(String... urlPatterns) {
        return jx.addMapping(urlPatterns);
    }

    @Override
    public String getName() {
        return jx.getName();
    }

    @Override
    public Collection<String> getMappings() {
        return jx.getMappings();
    }

    @Override
    public String getClassName() {
        return jx.getClassName();
    }

    @Override
    public boolean setInitParameter(String name, String value) {
        return jx.setInitParameter(name, value);
    }

    @Override
    public String getRunAsRole() {
        return jx.getRunAsRole();
    }

    @Override
    public String getInitParameter(String name) {
        return jx.getInitParameter(name);
    }

    @Override
    public Set<String> setInitParameters(Map<String, String> initParameters) {
        return jx.setInitParameters(initParameters);
    }

    @Override
    public Map<String, String> getInitParameters() {
        return jx.getInitParameters();
    }

    public static final MapGlobal<javax.servlet.ServletRegistration, ServletRegistration> jx2a //
            = MapGlobal.fn(ServletRegistration.class, (s) -> new JxServletRegistration(s));

    public static class JxDynamic
            implements
                Dynamic {

        javax.servlet.ServletRegistration.Dynamic ja;

        public JxDynamic(javax.servlet.ServletRegistration.Dynamic ja) {
            if (ja == null)
                throw new NullPointerException("ja");
            this.ja = ja;
        }

        @Override
        public Set<String> addMapping(String... urlPatterns) {
            return ja.addMapping(urlPatterns);
        }

        @Override
        public String getName() {
            return ja.getName();
        }

        @Override
        public Collection<String> getMappings() {
            return ja.getMappings();
        }

        @Override
        public String getClassName() {
            return ja.getClassName();
        }

        @Override
        public boolean setInitParameter(String name, String value) {
            return ja.setInitParameter(name, value);
        }

        @Override
        public String getRunAsRole() {
            return ja.getRunAsRole();
        }

        @Override
        public void setLoadOnStartup(int loadOnStartup) {
            ja.setLoadOnStartup(loadOnStartup);
        }

        @Override
        public String getInitParameter(String name) {
            return ja.getInitParameter(name);
        }

        @Override
        public Set<String> setInitParameters(Map<String, String> initParameters) {
            return ja.setInitParameters(initParameters);
        }

        @Override
        public Set<String> setServletSecurity(ServletSecurityElement constraint) {
            return ja.setServletSecurity(JaServletSecurityElement.ja2x(constraint));
        }

        @Override
        public Map<String, String> getInitParameters() {
            return ja.getInitParameters();
        }

        @Override
        public void setAsyncSupported(boolean isAsyncSupported) {
            ja.setAsyncSupported(isAsyncSupported);
        }

        @Override
        public void setMultipartConfig(MultipartConfigElement multipartConfig) {
            ja.setMultipartConfig(JaMultipartConfigElement.ja2x(multipartConfig));
        }

        @Override
        public void setRunAsRole(String roleName) {
            ja.setRunAsRole(roleName);
        }

        public static final MapGlobal<javax.servlet.ServletRegistration.Dynamic, jakarta.servlet.ServletRegistration.Dynamic> jx2a //
                = MapGlobal.fn(jakarta.servlet.ServletRegistration.Dynamic.class, (s) -> new JxDynamic(s));

    }

}
