package net.bodz.bas.c.loader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;


public class EmbeddedClassLoader
        extends URLClassLoader {

    public EmbeddedClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    public EmbeddedClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public EmbeddedClassLoader(URL[] urls) {
        super(urls);
    }

    public EmbeddedClassLoader() {
        super(new URL[0]);
    }

    @Override
    protected String findLibrary(String libname) {
        NativeLibraryInstaller installer = NativeLibraryInstaller.getInstance();
        try {
            File localFile = installer.install(libname);
            return localFile == null ? null : localFile.getAbsolutePath();
        } catch (IOException e) {
            throw new Error(e.getMessage(), e);
        }
    }

    public static EmbeddedClassLoader replace(ClassLoader loader) {
        if (loader == null)
            throw new NullPointerException("loader");

        if (loader instanceof EmbeddedClassLoader)
            return (EmbeddedClassLoader) loader;

        if (loader instanceof URLClassLoader) {
            URLClassLoader _loader = (URLClassLoader) loader;
            URL[] urls = _loader.getURLs();
            ClassLoader parent = _loader.getParent();
            return new EmbeddedClassLoader(urls, parent);
        }

        throw new UnsupportedOperationException(String.format(//
                "Can\'t replace the given loader %s to BundledLoader", loader));
    }

}
