package net.bodz.redist.obfuz.util;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * To avoid class conflicts, don't load any class other then the Java Runtimes.
 */
public class ProtectedClassLoader
        extends URLClassLoader {

    public ProtectedClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    public ProtectedClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public ProtectedClassLoader(URL[] urls) {
        super(urls);
    }

}
