package net.bodz.bas.c.loader;

import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.builtin.URLResource;

public class ClassResource {

    public static URL getRootURL(Class<?> clazz) {
        String classResName = clazz.getName().replace('.', '/') + ".class";
        URL classResUrl = clazz.getClassLoader().getResource(classResName);

        String rootPath = classResUrl.toString();
        if (!rootPath.endsWith(classResName))
            throw new UnexpectedException("Unexpected URL:  " + classResUrl);

        rootPath = rootPath.substring(0, rootPath.length() - classResName.length());
        rootPath = StringPart.chomp(rootPath, "/");
        rootPath = StringPart.chomp(rootPath, "\\");
        rootPath = StringPart.chomp(rootPath, "!");

        try {
            return new URL(rootPath);
        } catch (MalformedURLException e) {
            throw new UnexpectedException(e);
        }
    }

    /**
     * The class bytes resource of the <code>clazz</code>.
     */
    public static URLResource getData(Class<?> clazz) {
        return new URLResource(getDataURL(clazz));
    }

    /**
     * @param extension
     *            don't include the dot(.)
     */
    public static URLResource getData(Class<?> clazz, String extension) {
        return new URLResource(getDataURL(clazz, extension));
    }

    /**
     * Same as {@link #getDataURLBySuffix(Class, String)} with ".class" as <code>extension</code>.
     */
    public static URL getDataURL(Class<?> clazz) {
        String classSimpleName = clazz.getSimpleName();
        URL url = clazz.getResource(classSimpleName + ".class");
        return url;
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
