package net.bodz.bas.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * place holder.
 * 
 * if {@link URLClassLoader} is no more modifiable in future, then the
 * modifiable version will go here.
 */
public class DynamicUCL extends URLClassLoader {

    public DynamicUCL(URL[] urls, ClassLoader parent,
            URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    public DynamicUCL(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public DynamicUCL(URL[] urls) {
        super(urls);
    }

}
