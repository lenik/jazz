package net.bodz.bas.c.loader;

import java.net.URL;

import net.bodz.bas.io.resource.builtin.URLResource;

public class ClassResource {

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

}
