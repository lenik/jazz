package net.bodz.bas.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

import sun.dyn.empty.Empty;

public class BundledLoader extends URLClassLoader {

    private LibInstaller installer = new LibInstaller();

    public BundledLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    public BundledLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public BundledLoader(URL[] urls) {
        super(urls);
    }

    public BundledLoader() {
        super(Empty.URLs);
    }

    @Override
    protected String findLibrary(String libname) {
        return installer.findLibrary(this, libname);
    }

    public static BundledLoader replace(ClassLoader loader) {
        if (loader == null)
            return new BundledLoader(Empty.URLs);
        if (loader instanceof BundledLoader)
            return (BundledLoader) loader;
        if (loader instanceof URLClassLoader) {
            URLClassLoader ucl = (URLClassLoader) loader;
            URL[] urls = ucl.getURLs();
            ClassLoader parent = ucl.getParent();
            return new BundledLoader(urls, parent);
        }
        throw new UnsupportedOperationException(String.format(
                "Can\'t replace the given loader %s to BundledLoader", loader)); 
    }

}
