package net.bodz.bas.c.type;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;
import net.bodz.bas.t.iterator.PrefetchedIterator;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.order.PriorityComparator;

/**
 * Similar to {@link java.util.ServiceLoader} but won't instantiate.
 */
public class IndexedTypes {

    public static final String SERVICES = "META-INF/services";

    static Logger logger = Logger.getLogger(IndexedTypes.class.getName());

    public static <T> Iterable<Class<? extends T>> list(Class<T> serviceBaseType, boolean includeAbstract) {
        ClassLoader classLoader = serviceBaseType.getClassLoader();
        // ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return list(serviceBaseType, includeAbstract, classLoader);
    }

    public static <T> Iterable<Class<? extends T>> list(Class<T> serviceBaseType, boolean includeAbstract,
            ClassLoader classLoader) {
        try {
            return _list(null, serviceBaseType, includeAbstract, classLoader);
        } catch (Exception e) {
            throw new IllegalUsageException("Error occurred when listing indexed types: " + e.getMessage(), e);
        }
    }

    static <T> Iterable<Class<? extends T>> _list(String dirName, Class<T> serviceBaseType, boolean includeAbstract,
            ClassLoader classLoader)
            throws IOException, ClassNotFoundException {

        if (dirName == null) {
            IndexedType aIndexedType = serviceBaseType.getAnnotation(IndexedType.class);
            if (aIndexedType != null) {
                dirName = aIndexedType.publishDir();
            } else {
                // throw new IllegalArgumentException("Not indexed type: " + serviceBaseType);
                dirName = PublishDir.services;
            }
        }

        String resourceName = dirName + "/" + serviceBaseType.getName();

        return new Iterable<Class<? extends T>>() {
            @Override
            public Iterator<Class<? extends T>> iterator() {
                try {
                    Enumeration<URL> resources = classLoader.getResources(resourceName);
                    return new ResolveIterator<T>(serviceBaseType, includeAbstract, classLoader, resources);
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        };

    }

    static class ResolveIterator<T>
            extends PrefetchedIterator<Class<? extends T>> {

        Class<T> serviceBaseType;
        boolean includeAbstract;
        ClassLoader classLoader;

        Enumeration<URL> resources;
        URL currentResource;
        BufferedReader currentReader;
        boolean ignoreIOError = false;
        boolean ignoreROE = true;

        public ResolveIterator(Class<T> serviceBaseType, boolean includeAbstract, ClassLoader classLoader,
                Enumeration<URL> resources) {
            this.serviceBaseType = serviceBaseType;
            this.includeAbstract = includeAbstract;
            this.classLoader = classLoader;
            this.resources = resources;
        }

        void close() {
            if (currentReader != null) {
                try {
                    currentReader.close();
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Can't close the reader: " + e.getMessage(), e);
                    if (ignoreIOError) {
                        // ignore close error.
                    } else {
                        throw new RuntimeException("error close: " + e.getMessage(), e);
                    }
                } finally {
                    currentReader = null;
                }
            }
        }

        @Override
        protected Class<? extends T> fetch() {
            if (currentReader == null) {
                while (resources.hasMoreElements()) {
                    URL resource = resources.nextElement();
//                    System.err.println("resource " + resource);

                    InputStream in;
                    try {
                        in = resource.openStream();
                    } catch (IOException e) {
                        logger.severe(String.format(//
                                "Can't open resource %s to read: %s", currentResource, e.getMessage()));
                        if (ignoreIOError)
                            continue;
                        else
                            throw new RuntimeException(e);
                    }
                    currentResource = resource;

                    InputStreamReader reader = new InputStreamReader(in, Charsets.UTF_8);
                    currentReader = new BufferedReader(reader);
                    break;
                }
                if (currentReader == null)
                    return end();
            }

            String line;
            while (true) {
                try {
                    line = currentReader.readLine();
//                    System.err.println("ReadLine ----- " + line);
                } catch (IOException e) { // SecurityException: digest error
                    logger.log(Level.SEVERE,
                            "Error but ignored: Can't read from " + currentResource + ": " + e.getMessage(), e);

                    File file = FileURL.toNearestFile(currentResource);
                    if (file != null) {
                        Instant fileTime = Instant.ofEpochMilli(file.lastModified());
                        String fileTimeStr = DateTimes.ISO_LOCAL_DATE_TIME.format(LocalDateTime.from(fileTime));
                        String mesg = String.format("    Zip file: %s, length %d, ", file.getPath(), file.length(),
                                fileTimeStr);
                        logger.log(Level.INFO, mesg);
                    }

                    close();
                    return fetch();
                }

                // EOF
                if (line == null) {
                    close();
                    return fetch();
                }

                String className = line.trim();
                if (className.isEmpty())
                    continue;
                if (className.startsWith("#"))
                    continue;

                /*
                 * Optimize: Maybe another class loader different to the resource discoverer should
                 * be used.
                 */
                Class<?> serviceClass;
                try {
                    serviceClass = Class.forName(className, true, classLoader);
                } catch (ExceptionInInitializerError e) {
                    logger.log(Level.SEVERE, "Failed to init service class: " + className, e);
                    if (ignoreROE)
                        continue;
                    else
                        throw e;
                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                    if (ignoreROE) {
                        logger.log(Level.SEVERE, "No such service class: " + className);
                        continue;
                    } else
                        throw new IllegalUsageException(e);
                }

                if (! serviceBaseType.isAnnotation())
                    if (! serviceBaseType.isAssignableFrom(serviceClass)) {
                        String message = String.format(//
                                "Invalid service class %s for %s.", className, serviceBaseType.getName());
                        logger.severe(message);
                        if (ignoreROE)
                            continue;
                        else
                            throw new IllegalUsageException(message);
                    }

                if (Modifier.isAbstract(serviceClass.getModifiers()))
                    if (! includeAbstract)
                        continue;

                @SuppressWarnings("unchecked")
                Class<? extends T> casted = (Class<? extends T>) serviceClass;
                return casted;
            }
        } // fetch()

    }

    public static <S extends IPriority> List<S> loadInOrder(Class<S> service) {
        List<S> list = new ArrayList<S>();
        for (S instance : ServiceLoader.load(service))
            list.add(instance);
        Collections.sort(list, PriorityComparator.INSTANCE);
        return list;
    }

    public static <S extends IPriority> List<S> loadInOrder(Class<S> service, ClassLoader classLoader) {
        List<S> list = new ArrayList<S>();
        for (S instance : ServiceLoader.load(service, classLoader))
            list.add(instance);
        Collections.sort(list, PriorityComparator.INSTANCE);
        return list;
    }

}
