package net.bodz.bas.c.loader;

import java.net.URL;
import java.util.List;

import net.bodz.bas.c.java.net.IClassLoaderContentHandler;
import net.bodz.bas.jvm.stack.Caller;

public class URLClassLoaders
        extends net.bodz.bas.c.java.net.URLClassLoaders {

    public static void traverse(IClassLoaderContentHandler handler) {
        traverse(Caller.getCallerClassLoader(0), handler);
    }

    public static List<URL> findURLs() {
        ClassLoader classLoader = Caller.getCallerClassLoader(0);
        return collectURLs(classLoader);
    }

}
