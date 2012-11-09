package net.bodz.bas.c.loader;

public class DefaultClassLoader {

    public static ClassLoader getInstance() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null)
            loader = ClassLoader.getSystemClassLoader();
        return loader;
    }

}
