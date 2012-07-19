package net.bodz.bas.c.type;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.err.IllegalUsageException;

public class ClassLocals {

    static Map<String, ClassLocal<?>> all = new TreeMap<String, ClassLocal<?>>();

    public static ClassLocal<?> getMap(String id) {
        return all.get(id);
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
        return createMap(metadataClass.getCanonicalName(), metadataClass);
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
    public static <T> ClassLocal<T> createMap(String id, Method parserMethod) {
        ClassLocal<T> map = new ClassLocal<T>(parserMethod);
        register(id, map);
        return map;
    }

    public static void register(String id, ClassLocal<?> map) {
        ClassLocal<?> exist = all.get(id);
        if (exist != null)
            throw new IllegalUsageException("Id is already used: " + id);
        all.put(id, map);
    }

}
