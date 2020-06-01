package net.bodz.bas.servlet.config;

import net.bodz.bas.servlet.config.AbstractServletContextConfigurer;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.servlet.config.ServletDescriptor;
import net.bodz.bas.servlet.io.ClassResourceAccessorServlet;
import net.bodz.bas.servlet.io.StaticFileAccessServlet;
import net.bodz.bas.servlet.io.StaticVhostFileAccessServlet;

public abstract class AbstractResourceMappings
        extends AbstractServletContextConfigurer {

    public static class fn {

        public static ServletDescriptor localLink(ServletContextConfig config, String linkName, String target,
                int maxAgeDays) {
            String mapping = linkName + "/*";
            int maxAge = maxAgeDays * 86400;
            ServletDescriptor servlet = new ServletDescriptor(StaticFileAccessServlet.class);
            servlet.addMapping(mapping);
            servlet.setInitParam(StaticFileAccessServlet.ATTRIBUTE_PATH, target);
            servlet.setInitParam(StaticFileAccessServlet.ATTRIBUTE_MAX_AGE, maxAge);
            return servlet;
        }

        public static ServletDescriptor localVLink(ServletContextConfig config, String linkName, String target,
                int maxAgeDays) {
            String mapping = linkName + "/*";
            int maxAge = maxAgeDays * 86400;
            ServletDescriptor servlet = new ServletDescriptor(StaticVhostFileAccessServlet.class);
            servlet.addMapping(mapping);
            servlet.setInitParam(StaticVhostFileAccessServlet.ATTRIBUTE_PATH, target);
            servlet.setInitParam(StaticVhostFileAccessServlet.ATTRIBUTE_MAX_AGE, maxAge);
            config.addServlet(servlet);
            return servlet;
        }

        public static ServletDescriptor resourceLink(ServletContextConfig config, String linkName, String target,
                int maxAgeDays) {
            String mapping = linkName + "/*";
            int maxAge = maxAgeDays * 86400;
            ServletDescriptor servlet = new ServletDescriptor(ClassResourceAccessorServlet.class);
            servlet.addMapping(mapping);
            servlet.setInitParam(ClassResourceAccessorServlet.ATTRIBUTE_PATH, target);
            servlet.setInitParam(ClassResourceAccessorServlet.ATTRIBUTE_MAX_AGE, maxAge);
            return servlet;
        }

    }

}
