package net.bodz.bas.c.type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.err.IllegalUsageException;

/**
 * Similar to {@link java.util.ServiceLoader} but won't instantiate.
 */
public class IndexedTypes {

    public static final String SERVICES = "META-INF/services";

    static Logger logger = Logger.getLogger(IndexedTypes.class.getName());

    public static <T> Iterable<Class<? extends T>> list(Class<T> serviceBaseType, boolean includeAbstract) {
        // TODO Or using Context class loader?...
        ClassLoader classLoader = serviceBaseType.getClassLoader();
        return list(SERVICES, serviceBaseType, includeAbstract, classLoader);
    }

    public static <T> Iterable<Class<? extends T>> list(String dirName, Class<T> serviceBaseType,
            boolean includeAbstract, ClassLoader classLoader) {
        try {
            return _list(dirName, serviceBaseType, includeAbstract, classLoader);
        } catch (Exception e) {
            throw new IllegalUsageException("Error occurred when listing indexed types: " + e.getMessage(), e);
        }
    }

    static <T> Iterable<Class<? extends T>> _list(String dirName, Class<T> serviceBaseType, boolean includeAbstract,
            ClassLoader classLoader)
            throws IOException, ClassNotFoundException {

        String resourceName = dirName + "/" + serviceBaseType.getName();
        Enumeration<URL> resources = classLoader.getResources(resourceName);
        Set<Class<? extends T>> classes = new LinkedHashSet<Class<? extends T>>();

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            InputStream in = url.openStream();
            try {
                InputStreamReader reader = new InputStreamReader(in, Charsets.UTF8);
                BufferedReader br = new BufferedReader(reader);
                String line;
                while ((line = br.readLine()) != null) {
                    String className = line.trim();

                    if (className.isEmpty())
                        continue;

                    if (className.startsWith("#"))
                        continue;

                    /*
                     * Optimize: Maybe another class loader different to the resource discoverer
                     * should be used.
                     */
                    Class<?> serviceClass;
                    try {
                        serviceClass = Class.forName(className, true, classLoader);
                    } catch (ExceptionInInitializerError e) {
                        logger.log(Level.SEVERE, "Failed to resolve service class: " + className, e);
                        throw e;
                    }

                    if (!serviceBaseType.isAssignableFrom(serviceClass))
                        throw new IllegalUsageException(String.format("Invalid service class %s for %s.", //
                                className, serviceBaseType.getName()));

                    if (Modifier.isAbstract(serviceClass.getModifiers()))
                        if (!includeAbstract)
                            continue;

                    @SuppressWarnings("unchecked")
                    Class<? extends T> casted = (Class<? extends T>) serviceClass;
                    classes.add(casted);
                }
            } finally {
                in.close();
            }
        }

        return classes;
    }

}