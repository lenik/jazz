package net.bodz.bas.c.type;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.net.URL;
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

    ClassLoader defaultClassLoader;
    boolean initialize = true;

    public TypeIndex() {
        defaultClassLoader = ClassLoader.getSystemClassLoader();
    }

    public ClassLoader getDefaultClassLoader() {
        return defaultClassLoader;
    }

    public void setDefaultClassLoader(ClassLoader defaultClassLoader) {
        this.defaultClassLoader = defaultClassLoader;
    }

    protected ClassLoader getResourceClassLoader() {
        return defaultClassLoader;
    }

    protected ClassLoader getClassLoader(Class<?> baseType, String fqcn) {
        return defaultClassLoader;
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
    public <T> Iterable<Class<? extends T>> list(Class<T> baseType, boolean includeAbstract)
            throws IOException, ClassNotFoundException {

        String prefix = baseType.isAnnotation() ? PublishDir.features : PublishDir.services;
        String resourceName = prefix + "/" + baseType.getName();
        Enumeration<URL> resources = getResourceClassLoader().getResources(resourceName);

        Set<Class<? extends T>> classes = new LinkedHashSet<Class<? extends T>>();

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();

            for (String line : URLData.readUtf8Lines(url)) {
                String fqcn = line.trim();

                if (fqcn.isEmpty())
                    continue;

                if (fqcn.startsWith("#"))
                    continue;

                /*
                 * Optimize: Maybe another class loader different to the resource discoverer should
                 * be used.
                 */
                Class<?> extClass;
                ClassLoader classLoader = getClassLoader(baseType, fqcn);

                try {
                    extClass = Class.forName(fqcn, true, classLoader);
                } catch (ClassNotFoundException e) {
                    logger.severe("Undefined implementation name: " + fqcn);
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

    static final TypeIndex sclTypeIndex = new TypeIndex();

    public static TypeIndex getSclTypeIndex() {
        return sclTypeIndex;
    }

    public <T> Iterable<Class<? extends T>> listIndexed(Class<T> baseType)
            throws ClassNotFoundException, IOException {
        IndexedType indexing = baseType.getAnnotation(IndexedType.class);
        boolean includeAbstract = false;
        if (indexing != null)
            includeAbstract = indexing.includeAbstract();
        return list(baseType, includeAbstract);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Iterable<Class<?>> listAnnodated(Class<? extends Annotation> annotationType)
            throws IOException, ClassNotFoundException {
        Iterable list = this.list(annotationType, true);
        return list;
    }

}
