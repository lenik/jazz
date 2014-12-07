package net.bodz.bas.c.type;

import java.lang.reflect.Method;
import java.util.TreeMap;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.err.IllegalUsageException;

/**
 * Examples:
 * 
 * <pre>
 * TypeMap-Registry:
 *     "..IdPool":
 *         Map: Class -> IdPool
 * 
 *     "..RecvUtil"
 *         Map: Class -> IMethod  // IType .. recv(Object)
 *         
 *     "..ClassDocToOptionsConverter"
 *         Map: Class -> IOptionGroup
 *     
 *     "..FieldPathDispatcher.EntryLoader"
 *         Map: Class -> Map<String, Field>
 *     
 *     "..SimpleConstructorMap"
 *         Map: Class -> SimpleConstructorMap
 * </pre>
 */
public class TypeMapRegistry
        extends TreeMap<String, LazyTypeMap<?>> {

    private static final long serialVersionUID = 1L;

    public static LazyTypeMap<?> getMap(String id) {
        return instance.get(id);
    }

    /**
     * @see LazyTypeMap#ClassLocal(IMapEntryLoader)
     */
    public static <T> LazyTypeMap<T> createMap(IMapEntryLoader<Class<?>, T> entryLoader) {
        // Canonical name maybe null, so here we use normal name.
        String className = entryLoader.getClass().getName();
        return createMap(className, entryLoader);
    }

    /**
     * @see LazyTypeMap#ClassLocal(IMapEntryLoader)
     */
    public static <T> LazyTypeMap<T> createMap(String id, IMapEntryLoader<Class<?>, T> entryLoader) {
        LazyTypeMap<T> map = new LazyTypeMap<T>(entryLoader);
        addMap(id, map);
        return map;
    }

    /**
     * Each entry holds a metadata of <code>metadataClass</code>.
     * 
     * @param metadataClass
     *            The metadata class. The class must have a public constructor with a single
     *            Class-type parameter.
     * @see LazyTypeMap#ClassLocal(Class)
     */
    public static <T> LazyTypeMap<T> createMap(Class<?> metadataClass) {
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
     * @see LazyTypeMap#ClassLocal(Class)
     */
    public static <T> LazyTypeMap<T> createMap(String id, Class<?> metadataClass) {
        LazyTypeMap<T> map = new LazyTypeMap<T>(metadataClass);
        addMap(id, map);
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
     * @see LazyTypeMap#ClassLocal(Method)
     */
    public static <T> LazyTypeMap<T> createMap(Method parserMethod) {
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
     * @see LazyTypeMap#ClassLocal(Method)
     */
    public static <T> LazyTypeMap<T> createMap(String id, Method parserMethod) {
        LazyTypeMap<T> map = new LazyTypeMap<T>(parserMethod);
        addMap(id, map);
        return map;
    }

    /**
     * Register a class-local map with given id.
     * 
     * @param id
     *            Non-<code>null</code> class-local-id to be registered. The id must be unique.
     * @param map
     *            Non-<code>null</code> class-local map to be registered.
     * @throws IllegalUsageException
     *             If the id is already in use.
     */
    public static void addMap(String id, LazyTypeMap<?> map) {
        if (id == null)
            throw new NullPointerException("id");
        if (map == null)
            throw new NullPointerException("classLocal");

        LazyTypeMap<?> exist = instance.get(id);
        if (exist != null)
            throw new IllegalUsageException("Id is already used: " + id);

        instance.put(id, map);

        map.addRegisteredId(id);
    }

    /**
     * Remove class-local map from global cache.
     * 
     * @param id
     *            Non-<code>null</code> class-local-id to remove.
     * @return Removed class-local. Return <code>null</code> if the class-local with given id isn't
     *         existed.
     */
    public static LazyTypeMap<?> removeMap(String id) {
        if (id == null)
            throw new NullPointerException("id");

        LazyTypeMap<?> map = instance.remove(id);

        if (map != null)
            map.removeRegisteredId(id);

        return map;
    }

    private static TypeMapRegistry instance = new TypeMapRegistry();

    public static TypeMapRegistry getInstance() {
        return instance;
    }

}
