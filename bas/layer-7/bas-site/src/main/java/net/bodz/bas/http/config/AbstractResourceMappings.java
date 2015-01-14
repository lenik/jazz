package net.bodz.bas.http.config;

import net.bodz.bas.http.config.AbstractServletContextConfigurer;
import net.bodz.bas.http.config.ServletDescriptor;
import net.bodz.bas.http.servlet.ClassResourceAccessorServlet;
import net.bodz.bas.http.servlet.FileAccessorServlet;

public abstract class AbstractResourceMappings
        extends AbstractServletContextConfigurer {

    protected ServletDescriptor localLink(String linkName, String target, int maxAgeDays) {
        String mapping = linkName + "/*";
        int maxAge = maxAgeDays * 86400;
        ServletDescriptor servlet = new ServletDescriptor(FileAccessorServlet.class);
        servlet.addMapping(mapping);
        servlet.setInitParam(FileAccessorServlet.ATTRIBUTE_PATH, target);
        servlet.setInitParam(FileAccessorServlet.ATTRIBUTE_MAX_AGE, maxAge);
        return servlet;
    }

    protected ServletDescriptor resourceLink(String linkName, String target, int maxAgeDays) {
        String mapping = linkName + "/*";
        int maxAge = maxAgeDays * 86400;
        ServletDescriptor servlet = new ServletDescriptor(ClassResourceAccessorServlet.class);
        servlet.addMapping(mapping);
        servlet.setInitParam(ClassResourceAccessorServlet.ATTRIBUTE_PATH, target);
        servlet.setInitParam(ClassResourceAccessorServlet.ATTRIBUTE_MAX_AGE, maxAge);
        return servlet;
    }

}
