package net.bodz.bas.c.loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.builtin.URLResource;

public class ClassResource {

    public static File getRootFile(Class<?> clazz) {
        URL rootURL = getRootURL(clazz);
        try {
            return new File(rootURL.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static URL getRootURL(Class<?> clazz) {
        String classBytesName = clazz.getName().replace('.', '/') + ".class";
        URL classBytesUrl = clazz.getClassLoader().getResource(classBytesName);

        String rootUrl = classBytesUrl.toString();

        assert rootUrl.endsWith(classBytesName);
        rootUrl = rootUrl.substring(0, rootUrl.length() - classBytesName.length());

        rootUrl = StringPart.chomp(rootUrl, "/");
        rootUrl = StringPart.chomp(rootUrl, "\\");
        rootUrl = StringPart.chomp(rootUrl, "!");

        try {
            return new URL(rootUrl);
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }
    }

    public static URL getPackageURL(Class<?> clazz) {
        URL classBytes = getClassBytesURL(clazz);
        String classBytesUrl = classBytes.toString();

        int lastSlash = classBytesUrl.lastIndexOf('/');
        if (lastSlash == -1)
            throw new UnexpectedException("No slash found in: " + classBytesUrl);

        String packageUrl = classBytesUrl.substring(0, lastSlash);

        try {
            return new URL(packageUrl);
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }
    }

    /**
     * The class bytes resource of the <code>clazz</code>.
     */
    public static URLResource getClassBytes(Class<?> clazz) {
        return new URLResource(getClassBytesURL(clazz));
    }

    /**
     * Same as {@link #getDataURLBySuffix(Class, String)} with ".class" as <code>extension</code>.
     */
    public static URL getClassBytesURL(Class<?> clazz) {
        String classSimpleName = clazz.getSimpleName();
        URL url = clazz.getResource(classSimpleName + ".class");
        return url;
    }

    public static URL getClassDirURL(Class<?> clazz) {
        String classBytesUrl = getClassBytesURL(clazz).toString();

        assert classBytesUrl.endsWith(".class");
        String classDirUrl = classBytesUrl.substring(0, classBytesUrl.length() - 6) + ".d";

        try {
            return new URL(classDirUrl);
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    /**
     * @param extension
     *            don't include the dot(.)
     */
    public static URLResource getData(Class<?> clazz, String extension) {
        return new URLResource(getDataURL(clazz, extension));
    }

    /**
     * @param extension
     *            don't include the dot(.)
     */
    public static URL getDataURL(Class<?> clazz, String extension) {
        return getDataURLBySuffix(clazz, "." + extension);
    }

    /**
     * @param suffix
     *            must include the dot(.), if necessary
     */
    public static URL getDataURLBySuffix(Class<?> clazz, String suffix) {
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

}
