package net.bodz.bas.repr.resource;

import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

/**
 * Path: &lt;class-loader&gt;/&lt;resource-name&gt;
 * <p>
 * class-loader:
 * <ul>
 * <li>scl: system class loader.
 * <li>ccl: The thread-context class loader.
 * <li>core: the library class loader (which used to load {@link ClassResourceFactory} class.
 * </ul>
 */
public class ClassResourceFactory
        extends ResourceFactory {

    public static final String SYSTEM_CLASS_LOADER = "scl";
    public static final String CONTEXT_CLASS_LOADER = "ccl";
    public static final String LIBRARY_CLASS_LOADER = "lib";

    private Map<String, ClassLoader> loaders;

    public ClassResourceFactory() {
        loaders = new TreeMap<String, ClassLoader>();

        loaders.put(SYSTEM_CLASS_LOADER, ClassLoader.getSystemClassLoader());
        loaders.put(CONTEXT_CLASS_LOADER, Thread.currentThread().getContextClassLoader());
        loaders.put(LIBRARY_CLASS_LOADER, ClassResourceFactory.class.getClassLoader());
    }

    @Override
    public String getType() {
        return ResourceTypes.RT_CLASS;
    }

    @Override
    public IResource resolve(String path)
            throws ResourceResolveException {
        int slash = path.indexOf('/');
        if (slash == -1)
            throw new ResourceResolveException("Context class/loader for class-resource is missing");

        String contextName = path.substring(0, slash);
        path = path.substring(slash + 1);

        URL url;

        ClassLoader contextLoader = loaders.get(contextName);
        if (contextLoader != null) {
            url = contextLoader.getResource(path);
            if (url == null)
                throw new ResourceResolveException(String.format(
                        "Failed to resolve resource %s with-in class loader %s.", contextName, path));
        } else {
            Class<?> contextClass;
            try {
                contextClass = Class.forName(contextName);
            } catch (ClassNotFoundException e) {
                throw new ResourceResolveException(e.getMessage(), e);
            }
            url = contextClass.getResource(path);
            if (url == null)
                throw new ResourceResolveException(String.format("Failed to resolve resource %s relative to class %s.",
                        path, contextClass));
        }
        return new URLResource(url);
    }

}
