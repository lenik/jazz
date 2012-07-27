package net.bodz.bas.c.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Manifest;

import net.bodz.bas.err.IdentifiedException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.builtin.URLResource;

public class ClassResource {

    /**
     * @return <code>null</code> if no manifest.
     */
    public static Manifest getManifest(Class<?> clazz) {
        URL url = clazz.getResource("/META-INF/MANIFEST.MF");
        if (url == null)
            return null;
        InputStream in = null;
        try {
            in = url.openStream();
            Manifest manifest = new Manifest(in);
            in.close();
            return manifest;
        } catch (IOException e) {
            throw new IllegalStateException("Bad manifest: " + clazz, e);
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    /**
     * The class bytes resource of the <code>clazz</code>.
     */
    public static URLResource classData(Class<?> clazz) {
        return new URLResource(classDataURL(clazz));
    }

    /**
     * @param extension
     *            don't include the dot(.)
     */
    public static URLResource classData(Class<?> clazz, String extension) {
        return new URLResource(classDataURL(clazz, extension));
    }

    /**
     * Same as {@link #classDataURLBySuffix(Class, String)} with ".class" as <code>extension</code>.
     */
    public static URL classDataURL(Class<?> clazz) {
        String classSimpleName = clazz.getSimpleName();
        URL url = clazz.getResource(classSimpleName + ".class");
        return url;
    }

    /**
     * @param extension
     *            don't include the dot(.)
     */
    public static URL classDataURL(Class<?> clazz, String extension) {
        return classDataURLBySuffix(clazz, "." + extension);
    }

    /**
     * @param suffix
     *            must include the dot(.), if necessary
     */
    public static URL classDataURLBySuffix(Class<?> clazz, String suffix) {
        String classSimpleName = clazz.getSimpleName();
        String resourceName = classSimpleName + suffix;
        return clazz.getResource(resourceName);
// String spec = classSimpleName + suffix;
// try {
// URL url = new URL(context, spec);
// return url;
// } catch (MalformedURLException e) {
// throw new IllegalArgumentException(e);
// }
    }

    public static URL getRootResourceURL(Class<?> clazz) {
        ClassLoader classLoader = clazz.getClassLoader();
        if (classLoader == null)
            throw new NullPointerException("can't getClassLoader");
        String pkg = clazz.getPackage().getName();
        String base = clazz.getSimpleName() + ".class";
        String name = pkg.replace('.', '/') + '/' + base;
        return getRootResourceURL(classLoader, name);
    }

    public static URL getRootResourceURL(ClassLoader classLoader, String hintPath) {
        hintPath = hintPath.replace('\\', '/');
        URL url = classLoader.getResource(hintPath);
        String s = url.toString();
        if (!s.endsWith(hintPath))
            throw new UnexpectedException("Got bad resource URL: " + s);
        s = s.substring(0, s.length() - hintPath.length());
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
    }

}
