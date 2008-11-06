package net.bodz.bas.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

import net.bodz.bas.types.util.Empty;

public class BundledLoader extends URLClassLoader {

    private LibInstaller installer = new LibInstaller();

    public BundledLoader(URL[] urls, ClassLoader parent,
            URLStreamHandlerFactory factory) {
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

}
