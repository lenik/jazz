package net.bodz.bas.c.jakarta.servlet;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;

import net.bodz.bas.c.javax.servlet.JaDispatcherType;
import net.bodz.bas.c.util.MapGlobal;
import net.bodz.bas.t.iterator.Enumerations;

public class JxFilterRegistration
        implements
            FilterRegistration {

    javax.servlet.FilterRegistration jx;

    public JxFilterRegistration(javax.servlet.FilterRegistration jx) {
        if (jx == null)
            throw new NullPointerException("jx");
        this.jx = jx;
    }

    @Override
    public void addMappingForServletNames(EnumSet<DispatcherType> dispatcherTypes, boolean isMatchAfter,
            String... servletNames) {
        jx.addMappingForServletNames(//
                Enumerations.transform(dispatcherTypes, javax.servlet.DispatcherType.class,
                        (el) -> JaDispatcherType.ja2x(el)), //
                isMatchAfter, servletNames);
    }

    @Override
    public String getName() {
        return jx.getName();
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
    public Collection<String> getServletNameMappings() {
        return jx.getServletNameMappings();
    }

    @Override
    public void addMappingForUrlPatterns(EnumSet<DispatcherType> dispatcherTypes, boolean isMatchAfter,
            String... urlPatterns) {
        jx.addMappingForUrlPatterns(//
                Enumerations.transform(dispatcherTypes, javax.servlet.DispatcherType.class,
                        (el) -> JaDispatcherType.ja2x(el)), //
                isMatchAfter, urlPatterns);
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
    public Collection<String> getUrlPatternMappings() {
        return jx.getUrlPatternMappings();
    }

    @Override
    public Map<String, String> getInitParameters() {
        return jx.getInitParameters();
    }

    public static final MapGlobal<javax.servlet.FilterRegistration, FilterRegistration> jx2a //
            = MapGlobal.fn(FilterRegistration.class, (s) -> new JxFilterRegistration(s));

    public static class JxDynamic
            implements
                Dynamic {

        javax.servlet.FilterRegistration.Dynamic jx;

        public JxDynamic(javax.servlet.FilterRegistration.Dynamic jx) {
            if (jx == null)
                throw new NullPointerException("jx");
            this.jx = jx;
        }

        @Override
        public void addMappingForServletNames(EnumSet<DispatcherType> dispatcherTypes, boolean isMatchAfter,
                String... servletNames) {
            jx.addMappingForServletNames(//
                    Enumerations.transform(dispatcherTypes, javax.servlet.DispatcherType.class,
                            (el) -> JaDispatcherType.ja2x(el)), //
                    isMatchAfter, servletNames);
        }

        @Override
        public String getName() {
            return jx.getName();
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
        public Collection<String> getServletNameMappings() {
            return jx.getServletNameMappings();
        }

        @Override
        public void addMappingForUrlPatterns(EnumSet<DispatcherType> dispatcherTypes, boolean isMatchAfter,
                String... urlPatterns) {
            jx.addMappingForUrlPatterns(//
                    Enumerations.transform(dispatcherTypes, javax.servlet.DispatcherType.class,
                            (el) -> JaDispatcherType.ja2x(el)), //
                    isMatchAfter, urlPatterns);
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
        public Collection<String> getUrlPatternMappings() {
            return jx.getUrlPatternMappings();
        }

        @Override
        public Map<String, String> getInitParameters() {
            return jx.getInitParameters();
        }

        @Override
        public void setAsyncSupported(boolean isAsyncSupported) {
            jx.setAsyncSupported(isAsyncSupported);
        }

        public static final MapGlobal<javax.servlet.FilterRegistration.Dynamic, jakarta.servlet.FilterRegistration.Dynamic> jx2a //
                = MapGlobal.fn(jakarta.servlet.FilterRegistration.Dynamic.class, (s) -> new JxDynamic(s));

    }

}
