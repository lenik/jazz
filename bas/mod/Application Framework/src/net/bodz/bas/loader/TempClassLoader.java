package net.bodz.bas.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

import net.bodz.bas.types.util.Arrays2;

public class TempClassLoader extends URLClassLoader {

    private TempClassLoader(URL[] urls, ClassLoader parent,
            URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    private TempClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    private TempClassLoader(URL[] urls) {
        super(urls);
    }

    public static TempClassLoader get(URL[] urls) {
        return get(urls, ClassLoader.getSystemClassLoader());
    }

    public static TempClassLoader get(URL[] urls, ClassLoader parent) {
        assert urls != null;
        // if (parent instanceof URLClassLoader) {
        // URL[] merged = merge(parent, urls);
        // parent = parent.getParent();
        // return new TempURLClassLoader(merged, parent);
        // }
        return new TempClassLoader(urls, parent);
    }

    public static URL[] merge(ClassLoader parent, URL[] urls, boolean recursive) {
        assert urls != null;
        URL[] merged = urls;
        if (parent instanceof URLClassLoader) {
            URLClassLoader ucl = (URLClassLoader) parent;
            URL[] orig = ucl.getURLs();
            merged = Arrays2.concat(orig, urls);
        }
        if (recursive) {
            if (parent != null)
                return merge(parent.getParent(), merged, true);
        }
        return merged;
    }

    public static URL[] merge(ClassLoader parent, URL... urls) {
        return merge(parent, urls, false);
    }

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        assert name != null : "null name"; //$NON-NLS-1$
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            try {
                c = findClass(name);
            } catch (ClassNotFoundException e) {
                // this may invoke findClass(name) again.
                // it's just ok.
                return super.loadClass(name, resolve);
            }
        }
        if (resolve)
            resolveClass(c);
        return c;
    }

}
