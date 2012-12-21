package net.bodz.bas.c.loader;

import java.net.URL;
import java.net.URLClassLoader;

import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.sio.IPrintOut;

public class DefaultClassLoader {

    public static ClassLoader getInstance() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null)
            loader = ClassLoader.getSystemClassLoader();
        return loader;
    }

    public static URLClassLoader findFirstURLClassLoader() {
        ClassLoader loader = getInstance();
        return URLClassLoaders.findFirstURLClassLoader(loader);
    }

    public static int addURLs(URL... urls) {
        URLClassLoader urlClassLoader = findFirstURLClassLoader();
        if (urlClassLoader == null)
            throw new Error("Can't find URLClassLoader.");

        return URLClassLoaders.addURLs(urlClassLoader, urls);
    }

    public static void dump(IPrintOut out) {
        URLClassLoaders.dump(getInstance(), out);
    }

    public static String dump() {
        return URLClassLoaders.dump(getInstance());
    }

}
