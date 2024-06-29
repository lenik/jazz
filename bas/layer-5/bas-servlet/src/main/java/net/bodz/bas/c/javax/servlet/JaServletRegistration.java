package net.bodz.bas.c.javax.servlet;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletSecurityElement;

import net.bodz.bas.c.jakarta.servlet.JxMultipartConfigElement;
import net.bodz.bas.c.jakarta.servlet.JxServletSecurityElement;
import net.bodz.bas.c.util.MapGlobal;

public class JaServletRegistration
        implements
            ServletRegistration {

    jakarta.servlet.ServletRegistration ja;

    public JaServletRegistration(jakarta.servlet.ServletRegistration ja) {
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
    public String getInitParameter(String name) {
        return ja.getInitParameter(name);
    }

    @Override
    public Set<String> setInitParameters(Map<String, String> initParameters) {
        return ja.setInitParameters(initParameters);
    }

    @Override
    public Map<String, String> getInitParameters() {
        return ja.getInitParameters();
    }

    public static final MapGlobal<jakarta.servlet.ServletRegistration, ServletRegistration> ja2x//
            = MapGlobal.fn(ServletRegistration.class, (s) -> new JaServletRegistration(s));

    public static class JaDynamic
            implements
                Dynamic {

        jakarta.servlet.ServletRegistration.Dynamic ja;

        public JaDynamic(jakarta.servlet.ServletRegistration.Dynamic ja) {
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
            return ja.setServletSecurity(JxServletSecurityElement.jx2a(constraint));
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
            ja.setMultipartConfig(JxMultipartConfigElement.jx2a(multipartConfig));
        }

        @Override
        public void setRunAsRole(String roleName) {
            ja.setRunAsRole(roleName);
        }

        public static final MapGlobal<jakarta.servlet.ServletRegistration.Dynamic, javax.servlet.ServletRegistration.Dynamic> ja2x //
                = MapGlobal.fn(javax.servlet.ServletRegistration.Dynamic.class, (s) -> new JaDynamic(s));

    }

}
