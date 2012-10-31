package net.bodz.bas.log.impl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;

public class Log4jMerger {

    static String fragmentFileName = "META-INF/log4j.properties";

    static boolean loaded;

    static synchronized void load()
            throws IOException {
        if (loaded)
            return;

        Set<URL> set = new LinkedHashSet<URL>();
        ClassLoader scl = ClassLoader.getSystemClassLoader();
        ClassLoader ccl = Thread.currentThread().getContextClassLoader();
        collectFragmentResources(set, Log4jMerger.class.getClassLoader());
        collectFragmentResources(set, scl);
        collectFragmentResources(set, ccl);

        // Reverse the resources, so the later jar will override the first ones.
        int n = set.size();
        List<URL> reverseList = new ArrayList<URL>(n);
        for (URL url : set)
            reverseList.add(url);

        for (int i = n - 1; i >= 0; i--) {
            URL resource = reverseList.get(i);
            PropertyConfigurator.configure(resource);
        }
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

}
