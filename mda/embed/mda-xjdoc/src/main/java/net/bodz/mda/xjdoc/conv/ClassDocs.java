package net.bodz.mda.xjdoc.conv;

import java.net.URL;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.tags.JavadocTagBook;

public class ClassDocs {

    public static final String DEFAULT_EXTENSION = "ff";

    /**
     * @return <code>null</code> if no classdoc resource available.
     */
    public static ClassDoc loadFromResource(Class<?> clazz)
            throws ClassDocLoadException {
        return loadFromResource(clazz, DEFAULT_EXTENSION, false);
    }

    /**
     * @return <code>null</code> if no classdoc resource available.
     */
    public static ClassDoc loadFromResource(Class<?> clazz, boolean mustExist)
            throws ClassDocLoadException {
        return loadFromResource(clazz, DEFAULT_EXTENSION, mustExist);
    }

    /**
     * @return <code>null</code> if no classdoc resource available.
     */
    public static ClassDoc loadFromResource(Class<?> clazz, String extension, boolean mustExist)
            throws ClassDocLoadException {

        String fqcn = clazz.getName();
        String resourceName = fqcn.replace('.', '/') + "." + extension;

        ClassLoader resourceLoader = clazz.getClassLoader();
        URL resource = resourceLoader.getResource(resourceName);
        if (resource == null)
            if (mustExist)
                throw new ClassDocLoadException("No classdoc resource available for " + clazz);
            else
                return null;

        IStreamInputSource in = new URLResource(resource);

        JavadocTagBook book = new JavadocTagBook();
        ClassDocFlatfLoader ffLoader = new ClassDocFlatfLoader(book);

        ClassDoc doc;
        try {
            doc = ffLoader.load(clazz.getName(), in);
        } catch (Exception e) {
            throw new ClassDocLoadException(e.getMessage(), e);
        }
        return doc;
    }

}
