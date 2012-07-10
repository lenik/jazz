package net.bodz.bas.log.log4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

public class Log4jMerger {

    static String fragmentFileName = "META-INF/log4j.properties";

    static boolean loaded;

    static synchronized void load()
            throws IOException {
        if (loaded)
            return;

        List<URL> list = new ArrayList<URL>();

        ClassLoader classLoader = getClassLoader();
        Enumeration<URL> resources = classLoader.getResources(fragmentFileName);
        while (resources.hasMoreElements())
            list.add(resources.nextElement());

        for (int index = list.size() - 1; index >= 0; index--) {
            URL resource = (URL) list.get(index);
            PropertyConfigurator.configure(resource);
        }

        loaded = true;
    }

    static ClassLoader getClassLoader() {
        ClassLoader ccl = Thread.currentThread().getContextClassLoader();
        if (ccl != null)
            return ccl;

        // Reflection.getCaller...

        ClassLoader scl = ClassLoader.getSystemClassLoader();
        return scl;
    }

}
