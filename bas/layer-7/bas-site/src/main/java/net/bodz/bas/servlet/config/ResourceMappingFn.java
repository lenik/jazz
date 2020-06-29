package net.bodz.bas.servlet.config;

import net.bodz.bas.servlet.io.ClassResourceAccessorServlet;
import net.bodz.bas.servlet.io.StaticFileAccessServlet;
import net.bodz.bas.servlet.io.StaticVhostFileAccessServlet;

public class ResourceMappingFn {

    public static ServletDescriptor dirAlias(ServletContextConfig config, String linkName, String target, int maxAgeDays) {
        String mapping = linkName + "/*";
        int maxAge = maxAgeDays * 86400;
        ServletDescriptor servlet = new ServletDescriptor(StaticFileAccessServlet.class);
        servlet.addMapping(mapping);
        servlet.setInitParam(StaticFileAccessServlet.ATTRIBUTE_PATH, target);
        servlet.setInitParam(StaticFileAccessServlet.ATTRIBUTE_MAX_AGE, maxAge);
        return servlet;
    }

    public static ServletDescriptor vdirAlias(ServletContextConfig config, String aliasName, String target,
            int maxAgeDays) {
        String mapping = aliasName + "/*";
        int maxAge = maxAgeDays * 86400;
        ServletDescriptor servlet = new ServletDescriptor(StaticVhostFileAccessServlet.class);
        servlet.addMapping(mapping);
        servlet.setInitParam(StaticVhostFileAccessServlet.ATTRIBUTE_PATH, target);
        servlet.setInitParam(StaticVhostFileAccessServlet.ATTRIBUTE_MAX_AGE, maxAge);
        config.addServlet(servlet);
        return servlet;
    }

    public static ServletDescriptor resAlias(ServletContextConfig config, String linkName, String target, int maxAgeDays) {
        String mapping = linkName + "/*";
        int maxAge = maxAgeDays * 86400;
        ServletDescriptor servlet = new ServletDescriptor(ClassResourceAccessorServlet.class);
        servlet.addMapping(mapping);
        servlet.setInitParam(ClassResourceAccessorServlet.ATTRIBUTE_PATH, target);
        servlet.setInitParam(ClassResourceAccessorServlet.ATTRIBUTE_MAX_AGE, maxAge);
        return servlet;
    }

}
