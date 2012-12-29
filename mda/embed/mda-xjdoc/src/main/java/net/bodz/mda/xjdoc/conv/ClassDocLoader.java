package net.bodz.mda.xjdoc.conv;

import java.net.URL;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.tags.JavadocTagLibrary;

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

        ClassLoader resourceLoader = clazz.getClassLoader();
        URL resource = resourceLoader.getResource(resourceName);
        if (resource == null)
            return null;

        IStreamInputSource in = new URLResource(resource);

        JavadocTagLibrary taglib = new JavadocTagLibrary();
        ClassDocFlatfLoader ffLoader = new ClassDocFlatfLoader(taglib);

        ClassDoc doc;
        try {
            doc = ffLoader.load(clazz.getName(), in);
        } catch (Exception e) {
            throw new ClassDocLoadException(e.getMessage(), e);
        }
        return doc;
    }

}
