package net.bodz.bas.c.javax.servlet;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import net.bodz.bas.c.jakarta.servlet.JxDispatcherType;
import net.bodz.bas.c.util.MapGlobal;
import net.bodz.bas.t.iterator.Enumerations;

public class JaFilterRegistration
        implements
            FilterRegistration {

    jakarta.servlet.FilterRegistration ja;

    public JaFilterRegistration(jakarta.servlet.FilterRegistration ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public void addMappingForServletNames(EnumSet<DispatcherType> dispatcherTypes, boolean isMatchAfter,
            String... servletNames) {
        ja.addMappingForServletNames(//
                Enumerations.transform(dispatcherTypes, jakarta.servlet.DispatcherType.class,
                        (el) -> JxDispatcherType.jx2a(el)), //
                isMatchAfter, servletNames);
    }

    @Override
    public String getName() {
        return ja.getName();
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
    public Collection<String> getServletNameMappings() {
        return ja.getServletNameMappings();
    }

    @Override
    public void addMappingForUrlPatterns(EnumSet<DispatcherType> dispatcherTypes, boolean isMatchAfter,
            String... urlPatterns) {
        ja.addMappingForUrlPatterns(//
                Enumerations.transform(dispatcherTypes, jakarta.servlet.DispatcherType.class,
                        (el) -> JxDispatcherType.jx2a(el)), //
                isMatchAfter, urlPatterns);
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
    public Collection<String> getUrlPatternMappings() {
        return ja.getUrlPatternMappings();
    }

    @Override
    public Map<String, String> getInitParameters() {
        return ja.getInitParameters();
    }

    public static final MapGlobal<jakarta.servlet.FilterRegistration, FilterRegistration> ja2x //
            = MapGlobal.fn(FilterRegistration.class, (s) -> new JaFilterRegistration(s));

    public static class JaDynamic
            implements
                Dynamic {

        jakarta.servlet.FilterRegistration.Dynamic ja;

        public JaDynamic(jakarta.servlet.FilterRegistration.Dynamic ja) {
            if (ja == null)
                throw new NullPointerException("ja");
            this.ja = ja;
        }

        @Override
        public void addMappingForServletNames(EnumSet<DispatcherType> dispatcherTypes, boolean isMatchAfter,
                String... servletNames) {
            ja.addMappingForServletNames(//
                    Enumerations.transform(dispatcherTypes, jakarta.servlet.DispatcherType.class,
                            (el) -> JxDispatcherType.jx2a(el)), //
                    isMatchAfter, servletNames);
        }

        @Override
        public String getName() {
            return ja.getName();
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
        public Collection<String> getServletNameMappings() {
            return ja.getServletNameMappings();
        }

        @Override
        public void addMappingForUrlPatterns(EnumSet<DispatcherType> dispatcherTypes, boolean isMatchAfter,
                String... urlPatterns) {
            ja.addMappingForUrlPatterns(//
                    Enumerations.transform(dispatcherTypes, jakarta.servlet.DispatcherType.class,
                            (el) -> JxDispatcherType.jx2a(el)), //
                    isMatchAfter, urlPatterns);
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
        public Collection<String> getUrlPatternMappings() {
            return ja.getUrlPatternMappings();
        }

        @Override
        public Map<String, String> getInitParameters() {
            return ja.getInitParameters();
        }

        @Override
        public void setAsyncSupported(boolean isAsyncSupported) {
            ja.setAsyncSupported(isAsyncSupported);
        }

        public static final MapGlobal<jakarta.servlet.FilterRegistration.Dynamic, javax.servlet.FilterRegistration.Dynamic> ja2x //
                = MapGlobal.fn(javax.servlet.FilterRegistration.Dynamic.class, (s) -> new JaDynamic(s));

    }

}
