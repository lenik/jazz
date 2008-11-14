package net.bodz.bas.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import net.bodz.bas.lang.err.Err;
import net.bodz.bas.lang.err.IdentifiedException;
import net.bodz.bas.lang.err.UnexpectedException;

public class UCL {

    static Method URLClassLoader_addURL;
    static {
        Class<?> clazz = URLClassLoader.class;
        try {
            URLClassLoader_addURL = clazz
                    .getDeclaredMethod("addURL", URL.class);
            URLClassLoader_addURL.setAccessible(true);
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    /**
     * @return {@link URLClassLoader} which contains the specified url, or
     *         <code>null</code> if not found.
     */
    public static URLClassLoader exists(ClassLoader cl, URL url,
            boolean findParents) {
        if (url == null)
            throw new NullPointerException();
        while (cl != null) {
            if (cl instanceof URLClassLoader) {
                URLClassLoader ucl = (URLClassLoader) cl;
                for (URL u : ucl.getURLs())
                    if (url.equals(u))
                        return ucl;
            }
            if (findParents)
                cl = cl.getParent();
            else
                break;
        }
        return null;
    }

    /**
     * try to not add duplicated urls.
     * 
     * @return <code>false</code> if specified url is already existed in ucl or
     *         its parents.
     */
    public static boolean addURL(URLClassLoader ucl, URL url) {
        if (URLClassLoader_addURL == null)
            throw new Error("can't access URLClassLoader.addURL()");
        try {
            URLClassLoader exists = exists(ucl, url, true);
            if (exists != null)
                return false;
            URLClassLoader_addURL.invoke(ucl, url);
        } catch (IllegalAccessException e) {
            throw new IdentifiedException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            Err.unwrap(e);
        }
        return true;
    }

    public static URLClassLoader addOrCreate(ClassLoader loader, URL... urls) {
        if (loader instanceof URLClassLoader) {
            URLClassLoader ucl = (URLClassLoader) loader;
            for (URL url : urls)
                addURL(ucl, url);
            return ucl;
        }
        return new URLClassLoader(urls, loader);
    }

}
