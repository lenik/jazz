package net.bodz.bas.c.java.net;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.IPrintOut;

public class URLClassLoaders {

    public static List<ClassLoader> getStack(ClassLoader cl) {
        List<ClassLoader> stack = new ArrayList<>();
        for (; cl != null; cl = cl.getParent())
            stack.add(cl);
        return stack;
    }

    public static URLClassLoader findFirstURLClassLoader(ClassLoader loader) {
        while (loader != null) {
            if (loader instanceof URLClassLoader)
                return (URLClassLoader) loader;
            loader = loader.getParent();
        }
        return null;
    }

    public static List<File> getUserClassPath(ClassLoader cl) {
        URLClassLoader ucl = findFirstURLClassLoader(cl);
        if (ucl == null)
            return getJavaClassPath();
        List<File> localFileList = new ArrayList<File>();
        for (URL url : ucl.getURLs()) {
            File localFile = FileURL.toFile(url, null);
            if (localFile != null)
                localFileList.add(localFile);
        }
        return localFileList;
    }

    public static List<File> getJavaClassPath() {
        String javaClassPath = SystemProperties.getJavaClassPath();
        String[] entries = javaClassPath.split(File.pathSeparator);
        List<File> files = new ArrayList<>(entries.length);
        for (String entry : entries)
            files.add(new File(entry));
        return files;
    }

    @Deprecated
    public static List<File> getLocalURLs(ClassLoader cl) {
        return getLocalURLs(cl, 1000);
    }

    static List<File> getLocalURLs(ClassLoader cl, int size) {
        List<File> localFileList = new ArrayList<File>();
        int i = 0;
        while (cl != null) {
            if (cl instanceof URLClassLoader) {
                if (++i > size)
                    break;
                URLClassLoader ucl = (URLClassLoader) cl;
                for (URL url : ucl.getURLs()) {
                    File localFile = FileURL.toFile(url, null);
                    if (localFile != null)
                        localFileList.add(localFile);
                }
            }

            cl = cl.getParent();
        }
        return localFileList;
    }

    /**
     * @return {@link URLClassLoader} which contains the specified url, or <code>null</code> if not
     *         found.
     */
    public static URLClassLoader findClassLoaderForURL(ClassLoader loader, URL url) {
        if (loader == null)
            throw new NullPointerException("loader");
        if (url == null)
            throw new NullPointerException();

        while (loader != null) {
            if (loader instanceof URLClassLoader) {
                URLClassLoader ucl = (URLClassLoader) loader;
                for (URL u : ucl.getURLs())
                    if (url.equals(u))
                        return ucl;
            }
            loader = loader.getParent();
        }
        return null;
    }

    public static boolean containsURL1(ClassLoader classLoader, URL url) {
        URLClassLoader urlClassLoader = findClassLoaderForURL(classLoader, url);
        return urlClassLoader != null;
    }

    public static boolean containsURL(URLClassLoader urlClassLoader, URL url) {
        if (urlClassLoader == null)
            throw new NullPointerException("urlClassLoader");
        if (url == null)
            throw new NullPointerException("url");

        for (URL u : urlClassLoader.getURLs())
            if (url.equals(u))
                return true;

        return false;
    }

    public static int addURLs1(ClassLoader classLoader, URL... urls) {
        URLClassLoader urlClassLoader = findFirstURLClassLoader(classLoader);
        if (urlClassLoader == null)
            throw new Error("Can't find URLClassLoader.");
        else
            return addURLs(urlClassLoader, urls);
    }

    /**
     * try to not add duplicated urls.
     *
     * @return <code>false</code> if specified url is already existed in ucl or its parents.
     */
    public static int addURLs(URLClassLoader urlClassLoader, URL... urls) {
        Method URLClassLoader_addURL;
        Class<?> clazz = URLClassLoader.class;
        try {
            URLClassLoader_addURL = clazz.getDeclaredMethod("addURL", URL.class);
            URLClassLoader_addURL.setAccessible(true);
        } catch (Throwable e) {
            throw new Error("can\'t access URLClassLoader.addURL(): " + e.getMessage(), e);
        }

        int added = 0;
        try {
            for (URL url : urls) {
                URLClassLoader foundLoader = findClassLoaderForURL(urlClassLoader, url);
                if (foundLoader == null) {
                    URLClassLoader_addURL.invoke(urlClassLoader, url);
                    added++;
                }
            }
        } catch (ReflectiveOperationException e) {
            throw new UnexpectedException(e);
        }
        return added;
    }

    public static final Set<String> stopClassLoaderNames;
    static {
        stopClassLoaderNames = new HashSet<String>();
        stopClassLoaderNames.add("sun.misc.Launcher$ExtClassLoader");
    }

    public static void traverse(ClassLoader loader, IClassLoaderContentHandler handler) {
        if (!handler.classLoader(loader))
            return;

        if (loader instanceof URLClassLoader) {
            URLClassLoader urlClassLoader = (URLClassLoader) loader;
            URL[] urls = urlClassLoader.getURLs();
            for (URL url : urls)
                if (!handler.classpathURL(url))
                    break;
        }

        ClassLoader parent = loader.getParent();
        if (parent != null)
            traverse(parent, handler);
    }

    public static List<URL> collectURLs(ClassLoader loader) {
        final List<URL> list = new ArrayList<URL>();
        traverse(loader, new IClassLoaderContentHandler() {
            @Override
            public boolean classLoader(ClassLoader loader) {
                return !stopClassLoaderNames.contains(loader.getClass().getName());
            }

            @Override
            public boolean classpathURL(URL classpathURL) {
                list.add(classpathURL);
                return true;
            }
        });
        return list;
    }

    private static class Dumper
            implements
                IClassLoaderContentHandler {

        private final IPrintOut out;
        private boolean cont;

        public Dumper(IPrintOut out) {
            this.out = out;
        }

        @Override
        public boolean classpathURL(URL classpath) {
            out.println(classpath);
            return true;
        }

        @Override
        public boolean classLoader(ClassLoader loader) {
            if (cont)
                out.println();
            cont = true;
            out.println("; loader " + loader);
            if (stopClassLoaderNames.contains(loader.getClass().getName())) {
                out.println("; (stopped)");
                return false;
            }

            if (!(loader instanceof URLClassLoader))
                out.println("; (no url info)");
            return true;
        }

    }

    public static void dump(ClassLoader loader, IPrintOut out) {
        traverse(loader, new Dumper(out));
    }

    public static String dump(ClassLoader loader) {
        BCharOut buf = new BCharOut();
        dump(loader, buf);
        return buf.toString();
    }

}
