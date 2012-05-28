package net.bodz.bas.c.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.java.util.LazyHashMap;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.LazyLoadException;

public class ClassLocal<T>
        extends LazyHashMap<Class<?>, T> {

    private static final long serialVersionUID = 1L;

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

}
