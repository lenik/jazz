package net.bodz.bas.loader;

import java.net.URL;

import net.bodz.bas.jvm.stack.Caller;

public class URLClassLoaders
        extends net.bodz.bas.c.java.net.URLClassLoaders {

    public static void findURLs(Iter it) {
        findURLs(Caller.getCallerClassLoader(0), it);
    }

    public static URL[] findURLs() {
        return findURLs(Caller.getCallerClassLoader(0));
    }

}
