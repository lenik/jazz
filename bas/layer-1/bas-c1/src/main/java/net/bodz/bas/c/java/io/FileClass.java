package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.Manifest;

import net.bodz.bas.err.IdentifiedException;
import net.bodz.bas.err.UnexpectedException;

public class FileClass {

    public static File getClassFile(Class<?> clazz) {
        String resName = clazz.getName().replace('.', '/') + ".class";
        URL url = clazz.getClassLoader().getResource(resName);
        URI uri;
        try {
            uri = url.toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        File file = new File(uri);
        return file;
    }

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
