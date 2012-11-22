package net.bodz.bas.c.type;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.err.IllegalUsageException;

public class ClassLocals {

    static Map<String, ClassLocal<?>> all = new TreeMap<String, ClassLocal<?>>();

    public static ClassLocal<?> getMap(String id) {
        return all.get(id);
    }

    /**
     * @see ClassLocal#ClassLocal(IMapEntryLoader)
     */
    public static <T> ClassLocal<T> createMap(IMapEntryLoader<Class<?>, T> entryLoader) {
        // Canonical name maybe null, so here we use normal name.
        String className = entryLoader.getClass().getName();
        return createMap(className, entryLoader);
    }

    /**
     * @see ClassLocal#ClassLocal(IMapEntryLoader)
     */
    public static <T> ClassLocal<T> createMap(String id, IMapEntryLoader<Class<?>, T> entryLoader) {
        ClassLocal<T> map = new ClassLocal<T>(entryLoader);
        register(id, map);
        return map;
    }

    /**
     * Each entry holds a metadata of <code>metadataClass</code>.
     * 
     * @param metadataClass
     *            The metadata class. The class must have a public constructor with a single
     *            Class-type parameter.
     * @see ClassLocal#ClassLocal(Class)
     */
    public static <T> ClassLocal<T> createMap(Class<?> metadataClass) {
        // Canonical name of the metadata class maybe null.
        String className = metadataClass.getName();
        return createMap(className, metadataClass);
    }

    /**
     * Each entry holds a metadata of <code>metadataClass</code>.
     * 
     * @param id
     *            The class-local-id, by default, it's the canonical name of the
     *            <code>metadataClass</code>.
     * @param metadataClass
     *            The metadata class. The class must have a public constructor with a single
     *            Class-type parameter.
     * @see ClassLocal#ClassLocal(Class)
     */
    public static <T> ClassLocal<T> createMap(String id, Class<?> metadataClass) {
        ClassLocal<T> map = new ClassLocal<T>(metadataClass);
        register(id, map);
        return map;
    }

    /**
     * Each entry holds value returned from the parser method.
     * 
     * @param classParserMethod
     *            A public static method which receives a single {@link Class} parameter, and
     *            returns the parsed entry value.
     * @throws IllegalUsageException
     *             If the method don't accept a single {@link Class} parameter.
     * @see ClassLocal#ClassLocal(Method)
     */
    public static <T> ClassLocal<T> createMap(Method parserMethod) {
        // Canonical name maybe null.
        String className = parserMethod.getDeclaringClass().getName();

        String signature = new MethodSignature(parserMethod).toString();
        String id = className + "." + signature;

        return createMap(id, parserMethod);
    }

    /**
     * Each entry holds value returned from the parser method.
     * 
     * @param classParserMethod
     *            A public static method which receives a single {@link Class} parameter, and
     *            returns the parsed entry value.
     * @throws IllegalUsageException
     *             If the method don't accept a single {@link Class} parameter.
     * @see ClassLocal#ClassLocal(Method)
     */
    public static <T> ClassLocal<T> createMap(String id, Method parserMethod) {
        ClassLocal<T> map = new ClassLocal<T>(parserMethod);
        register(id, map);
        return map;
    }

    /**
     * Register a class-local map with given id.
     * 
     * @param id
     *            Non-<code>null</code> class-local-id to be registered. The id must be unique.
     * @param classLocal
     *            Non-<code>null</code> class-local map to be registered.
     * @throws IllegalUsageException
     *             If the id is already in use.
     */
    public static void register(String id, ClassLocal<?> classLocal) {
        if (id == null)
            throw new NullPointerException("id");
        if (classLocal == null)
            throw new NullPointerException("classLocal");

        ClassLocal<?> exist = all.get(id);
        if (exist != null)
            throw new IllegalUsageException("Id is already used: " + id);

        all.put(id, classLocal);

        classLocal.addRegisteredId(id);
    }

    /**
     * Remove class-local map from global cache.
     * 
     * @param id
     *            Non-<code>null</code> class-local-id to remove.
     * @return Removed class-local. Return <code>null</code> if the class-local with given id isn't
     *         existed.
     */
    public static ClassLocal<?> remove(String id) {
        if (id == null)
            throw new NullPointerException("id");

        ClassLocal<?> classLocal = all.remove(id);

        if (classLocal != null)
            classLocal.removeRegisteredId(id);

        return classLocal;
    }

}
