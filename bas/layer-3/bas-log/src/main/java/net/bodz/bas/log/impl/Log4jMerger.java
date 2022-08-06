package net.bodz.bas.log.impl;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;

import net.bodz.bas.log.log4j.ILog4jConfigurer;

@Deprecated
public class Log4jMerger
        implements
            ILog4jConfigurer {

    static String fragmentFileName = "META-INF/log4j.properties";

    @Override
    public void initLog4j(Configuration config) {
        load();
    }

    static boolean loaded;
    static List<URL> resources;

    static synchronized void load() {
        if (loaded)
            return;

        Set<URL> set = new LinkedHashSet<URL>();
        ClassLoader scl = ClassLoader.getSystemClassLoader();
        ClassLoader ccl = Thread.currentThread().getContextClassLoader();

        try {
            collectFragmentResources(set, Log4jMerger.class.getClassLoader());
            collectFragmentResources(set, scl);
            collectFragmentResources(set, ccl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        resources = new ArrayList<URL>(set);

        // Reverse the resources, so the later jar will override the first ones.
        Collections.reverse(resources);
        loaded = true;
    }

    static void collectFragmentResources(Collection<URL> collection, ClassLoader classLoader)
            throws IOException {
        Enumeration<URL> resources = classLoader.getResources(fragmentFileName);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            collection.add(resource);
        }
    }

    @Override
    public void setupBuilder(ConfigurationBuilder<? extends Configuration> builder) {
        for (URL resource : resources) {
//          new PropertiesConfigurationBuilder()//
//                  .setConfigurationSource(source)//
//                  .setRootProperties(properties)//
//                  .setLoggerContext(loggerContext)//
//                  .build();
        }
    }

}
