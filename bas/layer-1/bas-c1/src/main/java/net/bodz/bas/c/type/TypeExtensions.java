package net.bodz.bas.c.type;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import net.bodz.bas.c.java.net.URLContent;
import net.bodz.bas.err.IllegalUsageException;

public class TypeExtensions {

    String resourcePrefix = "META-INF/extension/";

    boolean includeAbstract;
    ClassLoader defaultClassLoader;
    boolean initialize = true;

    public TypeExtensions() {
        defaultClassLoader = ClassLoader.getSystemClassLoader();
    }

    public String getResourcePrefix() {
        return resourcePrefix;
    }

    public void setResourcePrefix(String resourcePrefix) {
        this.resourcePrefix = resourcePrefix;
    }

    public boolean isIncludeAbstract() {
        return includeAbstract;
    }

    public void setIncludeAbstract(boolean includeAbstract) {
        this.includeAbstract = includeAbstract;
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
    public <T> Iterable<Class<? extends T>> list(Class<T> baseType)
            throws IOException, ClassNotFoundException {

        String resourceName = resourcePrefix + baseType.getName();
        Enumeration<URL> resources = getResourceClassLoader().getResources(resourceName);

        Set<Class<? extends T>> classes = new LinkedHashSet<Class<? extends T>>();

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();

            for (String line : URLContent.readUtf8Lines(url)) {
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
                extClass = Class.forName(fqcn, true, classLoader);

                if (!baseType.isAssignableFrom(extClass))
                    throw new IllegalUsageException(String.format("Invalid type extension for %s: %s.", //
                            baseType.getName(), fqcn));

                if (!includeAbstract)
                    if (Modifier.isAbstract(extClass.getModifiers()))
                        continue;

                @SuppressWarnings("unchecked") Class<? extends T> casted = (Class<? extends T>) extClass;
                classes.add(casted);
            }
        }

        return classes;
    }

    public static <T> Iterable<Class<? extends T>> forClass(Class<T> baseType, boolean includeAbstract)
            throws IOException, ClassNotFoundException {
        TypeExtensions exts = new TypeExtensions();
        exts.setIncludeAbstract(includeAbstract);
        return exts.list(baseType);
    }

}