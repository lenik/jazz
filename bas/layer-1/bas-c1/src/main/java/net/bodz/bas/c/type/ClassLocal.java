package net.bodz.bas.c.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.java.util.LazyHashMap;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LazyLoadException;

public class ClassLocal<T>
        extends LazyHashMap<Class<?>, T> {

    private static final long serialVersionUID = 1L;

    Set<String> registeredIds;

    public ClassLocal(IMapEntryLoader<Class<?>, T> entryLoader) {
        super(entryLoader);
    }

    /**
     * Each entry holds a metadata of <code>metadataClass</code>.
     * 
     * @param metadataClass
     *            The metadata class. The class must have a public constructor with a single
     *            Class-type parameter.
     * @throws IllegalUsageException
     *             If <code>metadataClass</code> doesn't have desired constructor.
     */
    public ClassLocal(Class<?> metadataClass) {
        super(new MetadataClassEntryLoader<T>(metadataClass));
    }

    /**
     * Each entry holds value returned from the parser method.
     * 
     * @param classParserMethod
     *            A public static method which receives a single {@link Class} parameter, and
     *            returns the parsed entry value.
     * @throws IllegalUsageException
     *             If the method don't accept a single {@link Class} parameter.
     */
    public ClassLocal(Method classParserMethod) {
        super(new ClassParserEntryLoader<T>(classParserMethod));
    }

    /**
     * Used by {@link ClassLocal#ClassLocal(Class)}.
     */
    private static class MetadataClassEntryLoader<V>
            implements IMapEntryLoader<Class<?>, V> {

        // final Class<?> metadataClass;
        final Constructor<V> ctor;

        public MetadataClassEntryLoader(Class<?> metadataClass)
                throws IllegalUsageException {
            if (metadataClass == null)
                throw new NullPointerException("metadataClass");
            // this.metadataClass = metadataClass;
            try {
                this.ctor = (Constructor<V>) metadataClass.getConstructor(Class.class);
            } catch (NoSuchMethodException e) {
                throw new IllegalUsageException(e.getMessage(), e);
            }
        }

        @Override
        public V loadValue(Class<?> clazz)
                throws LazyLoadException {
            try {
                return ctor.newInstance(clazz);
            } catch (ReflectiveOperationException e) {
                throw new LazyLoadException("Failed to load entry for " + clazz, e);
            }
        }

    }

    /**
     * Used by {@link ClassLocal#ClassLocal(Method)}.
     */
    private static class ClassParserEntryLoader<V>
            implements IMapEntryLoader<Class<?>, V> {

        final Method parserMethod;

        public ClassParserEntryLoader(Method parserMethod) {
            if (parserMethod == null)
                throw new NullPointerException("parserMethod");
            int mod = parserMethod.getModifiers();
            if (!(Modifier.isPublic(mod) || Modifier.isStatic(mod)))
                throw new IllegalUsageException("Bad parser method: " + parserMethod);
            this.parserMethod = parserMethod;
        }

        @Override
        public V loadValue(Class<?> clazz)
                throws LazyLoadException {
            try {
                V result = (V) parserMethod.invoke(null, clazz);
                return result;
            } catch (ReflectiveOperationException e) {
                throw new LazyLoadException("Failed to load entry for " + clazz, e);
            }
        }

    }

    public Set<String> getRegisteredIds() {
        if (registeredIds == null) {
            synchronized (this) {
                if (registeredIds == null) {
                    registeredIds = new TreeSet<String>();
                }
            }
        }
        return registeredIds;
    }

    /**
     * Get a registered id.
     * 
     * If there are multiple registered ids, one of them is returned. However, it's picked randomly.
     * 
     * @return One of the registered id. Return <code>null</code> if this class-local isn't
     *         registered at all.
     */
    public synchronized String getRegisteredId() {
        if (registeredIds == null)
            return null;
        if (registeredIds.isEmpty())
            return null;
        String first = registeredIds.iterator().next();
        return first;
    }

    public synchronized boolean addRegisteredId(String registeredId) {
        return getRegisteredIds().add(registeredId);
    }

    public synchronized void removeRegisteredId(String registeredId) {
        if (registeredIds != null)
            registeredIds.remove(registeredId);
    }

}
