package net.bodz.bas.c.type;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import net.bodz.bas.c.java.net.URLData;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;

public class TypeIndex {

    static final Logger logger = Logger.getLogger(TypeIndex.class.getName());

    ClassLoader classLoader;
    boolean initialize = true;

    private TypeIndex(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * List all defined extensions for the base type.
     *
     * @param baseType
     *            Which type's extensions to be listed.
     * @return Non-<code>null</code> extension types in iterable form.
     * @throws ClassNotFoundException
     * @throws ExceptionInInitializerError
     */
    public <T> Collection<Class<? extends T>> list(Class<T> baseType, boolean includeAbstract)
            throws IOException, ClassNotFoundException {

        String prefix = baseType.isAnnotation() ? PublishDir.features : PublishDir.services;
        String resourceName = prefix + "/" + baseType.getName();
        Enumeration<URL> resources = classLoader.getResources(resourceName);

        Set<Class<? extends T>> classes = new LinkedHashSet<Class<? extends T>>();

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();

            for (String line : URLData.readUtf8Lines(url)) {
                String fqcn = line.trim();

                if (fqcn.isEmpty())
                    continue;

                if (fqcn.startsWith("#"))
                    continue;

                Class<?> extClass;
                try {
                    extClass = Class.forName(fqcn, true, classLoader);
                } catch (ClassNotFoundException e) {
                    logger.severe("(skipped) Undefined implementation name: " + fqcn + " defined in " + url);
                    continue;
                }

                if (!baseType.isAnnotation())
                    if (!baseType.isAssignableFrom(extClass))
                        throw new IllegalUsageException(String.format("Invalid type extension for %s: %s.", //
                                baseType.getName(), fqcn));

                if (!includeAbstract)
                    if (Modifier.isAbstract(extClass.getModifiers()))
                        continue;

                @SuppressWarnings("unchecked")
                Class<? extends T> casted = (Class<? extends T>) extClass;
                classes.add(casted);
            }
        }

        return classes;
    }

    public <T> Collection<Class<? extends T>> listIndexed(Class<T> baseType)
            throws ClassNotFoundException, IOException {
        IndexedType indexing = baseType.getAnnotation(IndexedType.class);
        boolean includeAbstract = false;
        if (indexing != null)
            includeAbstract = indexing.includeAbstract();
        return list(baseType, includeAbstract);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Collection<Class<?>> listAnnodated(Class<? extends Annotation> annotationType)
            throws IOException, ClassNotFoundException {
        Collection list = this.list(annotationType, true);
        return list;
    }

    // static final TypeIndex systemTypeIndex = new TypeIndex(ClassLoader.getSystemClassLoader());

    public static TypeIndex getInstance(ClassLoader classLoader) {
        return new TypeIndex(classLoader);
    }

}
