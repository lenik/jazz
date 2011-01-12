package net.bodz.bas.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.Manifest;

import net.bodz.bas.util.exception.IdentifiedException;
import net.bodz.bas.util.exception.UnexpectedException;

public class ClassResource {

    /**
     * @return <code>null</code> if no manifest.
     */
    public static Manifest getManifest(Class<?> clazz) {
        URL url = clazz.getResource("/META-INF/MANIFEST.MF"); //$NON-NLS-1$
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
     * Same as {@link #_classData(Class, String)} with ".class" as <code>extension</code>.
     */
    public static URL classData(Class<?> clazz) {
        String thisName = clazz.getSimpleName();
        return clazz.getResource(thisName + ".class"); //$NON-NLS-1$
    }

    /**
     * @param extension
     *            don't include the dot(.)
     */
    public static URL classData(Class<?> clazz, String extension) {
        return _classData(clazz, "." + extension); //$NON-NLS-1$
    }

    /**
     * @param extension
     *            must include the dot(.), if necessary
     */
    public static URL _classData(Class<?> clazz, String extension) {
        String thisName = clazz.getSimpleName();
        // the resource may not exist.
        // clazz.getResource(thisName + extension);
        URL self = classData(clazz);
        String spec = thisName + extension;
        try {
            URL url = new URL(self, spec);
            return url;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static URL getRootResource(Class<?> clazz) {
        ClassLoader classLoader = clazz.getClassLoader();
        if (classLoader == null)
            throw new NullPointerException("can't getClassLoader"); //$NON-NLS-1$
        String pkg = clazz.getPackage().getName();
        String base = clazz.getSimpleName() + ".class"; //$NON-NLS-1$
        String name = pkg.replace('.', '/') + '/' + base;
        return getRootResource(classLoader, name);
    }

    public static URL getRootResource(ClassLoader classLoader, String hintPath) {
        hintPath = hintPath.replace('\\', '/');
        URL url = classLoader.getResource(hintPath);
        String s = url.toString();
        if (!s.endsWith(hintPath))
            throw new UnexpectedException("Got bad resource URL: " + s);
        s = s.substring(0, s.length() - hintPath.length());
        try {
            url = new URL(s);
        } catch (MalformedURLException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
        if (url == null)
            throw new Error("Can't find root resource: " + s);
        return url;
    }

}
