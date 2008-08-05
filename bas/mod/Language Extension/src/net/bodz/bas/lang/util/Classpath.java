package net.bodz.bas.lang.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.Err;
import net.bodz.bas.lang.err.IdentifiedException;
import net.bodz.bas.lang.err.UnexpectedException;

public class Classpath {

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

    public static void addURL(ClassLoader loader, URL url) {
        if (URLClassLoader_addURL == null)
            throw new Error("can't access URLClassLoader.addURL()");

        if (!(loader instanceof URLClassLoader))
            throw new UnsupportedOperationException("can't addURL to "
                    + loader.getClass());

        try {
            // System.err.println("addURL " + url + " -> " + loader);
            URLClassLoader_addURL.invoke(loader, url);
        } catch (IllegalAccessException e) {
            throw new IdentifiedException(e.getMessage(), e);
        } catch (InvocationTargetException e) {
            Err.unwrap(e);
        }
    }

    public static void addURL(URL url) throws IOException {
        addURL(ClassLoader.getSystemClassLoader(), url);
    }

    public static void dumpURLs(ClassLoader loader, CharOut out) {
        out.println("; loader " + loader);
        if (!(loader instanceof URLClassLoader)) {
            out.println("; (no url info)");
        } else {
            URLClassLoader uloader = (URLClassLoader) loader;
            URL[] urls = uloader.getURLs();
            for (URL url : urls)
                out.println(url);
        }
        ClassLoader parent = loader.getParent();
        if (parent != null) {
            out.println();
            dumpURLs(parent, out);
        }
    }

    public static void dumpURLs(CharOut out) {
        dumpURLs(Caller.getCallerClassLoader(), out);
    }

}
