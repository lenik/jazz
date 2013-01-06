package net.bodz.bas.c.loader;

public class ClassLoaders {

    public static ClassLoader getCompileClassLoader() {
        return getRuntimeClassLoader();
    }

    public static ClassLoader getRuntimeClassLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null)
            loader = ClassLoader.getSystemClassLoader();
        return loader;
    }

    public static ClassLoader getTestClassLoader() {
        return getRuntimeClassLoader();
    }

    public static ClassLoader getLibraryClassLoader() {
        return getRuntimeClassLoader();
    }

}
