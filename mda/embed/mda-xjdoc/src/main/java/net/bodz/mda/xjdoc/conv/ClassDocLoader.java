package net.bodz.mda.xjdoc.conv;

import java.net.URL;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.taglib.TagLibraryLoader;
import net.bodz.mda.xjdoc.taglib.TagLibrarySet;

public class ClassDocLoader {

    public static final String DEFAULT_EXTENSION = "ff";

    /**
     * @return <code>null</code> if no classdoc resource available.
     */
    public static ClassDoc load(Class<?> clazz)
            throws ClassDocLoadException {
        return load(clazz, DEFAULT_EXTENSION);
    }

    /**
     * @return <code>null</code> if no classdoc resource available.
     */
    public static ClassDoc load(Class<?> clazz, String extension)
            throws ClassDocLoadException {

        String fqcn = clazz.getName();
        String resourceName = fqcn.replace('.', '/') + "." + extension;

        /**
         * For Maven exec:java, the system class loader just includes plexus-classworlds, and
         * context class loader should be used.
         */
        ClassLoader resourceLoader = Thread.currentThread().getContextClassLoader();
        if (resourceLoader == null) {
            resourceLoader = clazz.getClassLoader();
            if (resourceLoader == null)
                throw new NullPointerException("resourceLoader");
        }

        URL resource = resourceLoader.getResource(resourceName);
        if (resource == null)
            return null;

        IStreamInputSource in = new URLResource(resource);

        TagLibraryLoader tagLibraryLoader = new TagLibraryLoader(resourceLoader);
        TagLibrarySet taglibs = tagLibraryLoader.parseSet("*");

        ClassDocFlatfLoader ffLoader = new ClassDocFlatfLoader(taglibs);

        ClassDoc doc;
        try {
            doc = ffLoader.load(clazz.getName(), in);
        } catch (Exception e) {
            throw new ClassDocLoadException(e.getMessage(), e);
        }
        return doc;
    }

}
