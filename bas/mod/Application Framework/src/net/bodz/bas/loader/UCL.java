package net.bodz.bas.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.Err;
import net.bodz.bas.lang.err.IdentifiedException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.types.util.Empty;

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

    private static class Collect extends Iter {

        private List<URL> list;

        public Collect() {
            this.list = new ArrayList<URL>();
        }

        @Override
        public boolean entry(URL classpath) {
            list.add(classpath);
            return true;
        }

        public URL[] get() {
            return list.toArray(Empty.URLs);
        }

    }

    public static void findURLs(ClassLoader loader, Iter it) {
        if (!it.loader(loader))
            return;
        if (!(loader instanceof URLClassLoader))
            return;
        URLClassLoader uloader = (URLClassLoader) loader;
        URL[] urls = uloader.getURLs();
        for (URL url : urls)
            if (!it.entry(url))
                break;
        ClassLoader parent = loader.getParent();
        if (parent != null)
            findURLs(parent, it);
    }

    public static URL[] findURLs(ClassLoader loader) {
        Collect collect = new Collect();
        findURLs(loader, collect);
        return collect.get();
    }

    public static void findURLs(Iter it) {
        findURLs(Caller.getCallerClassLoader(), it);
    }

    public static URL[] findURLs() {
        return findURLs(Caller.getCallerClassLoader());
    }

    public static abstract class Iter {

        public abstract boolean entry(URL classpath);

        /**
         * (hint) it's useful to break if loader is-a
         */
        public boolean loader(ClassLoader loader) {
            String className = loader.getClass().getName();
            if (className.equals("sun.misc.Launcher$ExtClassLoader"))
                return false;
            return true;
        }

    }

    private static class Dumper extends Iter {

        private final CharOut out;
        private boolean       cont;

        public Dumper(CharOut out) {
            this.out = out;
        }

        @Override
        public boolean entry(URL classpath) {
            out.println(classpath);
            return true;
        }

        @Override
        public boolean loader(ClassLoader loader) {
            if (cont)
                out.println();
            cont = true;
            out.println("; loader " + loader);
            if (!super.loader(loader)) {
                out.println("; (system loader ignored)");
                return false;
            }
            if (!(loader instanceof URLClassLoader))
                out.println("; (no url info)");
            return true;
        }

    }

    public static void dump(ClassLoader loader, CharOut out) {
        findURLs(loader, new Dumper(out));
    }

    public static String dump(ClassLoader loader) {
        Buffer buf = new Buffer();
        dump(loader, buf);
        return buf.toString();
    }

}
