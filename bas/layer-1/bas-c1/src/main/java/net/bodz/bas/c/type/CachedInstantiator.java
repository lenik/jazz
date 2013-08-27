package net.bodz.bas.c.type;

import java.lang.reflect.Constructor;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.java.util.LazyHashMap;
import net.bodz.bas.c.java.util.array.ArrayWrapper;
import net.bodz.bas.err.LazyLoadException;

public class CachedInstantiator
        extends LazyHashMap<Class<?>, PerCtor> {

    private static final long serialVersionUID = 1L;

    public CachedInstantiator() {
        super(new PerClassEntryLoader());
    }

    public <T> T instantiate(Class<T> clazz, Object... parameters)
            throws LazyLoadException {
        Class<?>[] parameterTypes = TypeArray.getClasses(Object.class, parameters);
        return instantiate(clazz, parameterTypes, parameters);
    }

    public <T> T instantiate(Class<T> clazz, Class<?>[] parameterTypes, Object... parameters)
            throws LazyLoadException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (parameterTypes == null)
            throw new NullPointerException("parameterTypes");
        if (parameters == null)
            throw new NullPointerException("parameters");
        PerCtor perCtor = getOrLoad(clazz);
        PerInitargs perInitargs = perCtor.getOrLoad(Arrays.wrap(parameterTypes));
        Object instance = perInitargs.getOrLoad(Arrays.wrap(parameters));
        return clazz.cast(instance);
    }

    public void clear(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        remove(clazz);
    }

    public void clear(Class<?> clazz, Class<?>... parameterTypes) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (parameterTypes == null)
            throw new NullPointerException("parameterTypes");
        PerCtor perCtor = get(clazz);
        if (perCtor != null)
            perCtor.remove(Arrays.wrap(parameterTypes));
    }

    static CachedInstantiator instance = new CachedInstantiator();

    public static CachedInstantiator getInstance() {
        return instance;
    }

}

class PerClassEntryLoader
        implements IMapEntryLoader<Class<?>, PerCtor> {

    public PerClassEntryLoader() {
    }

    @Override
    public PerCtor loadValue(Class<?> key)
            throws LazyLoadException {
        return new PerCtor(key);
    }

}

class PerCtor
        extends LazyHashMap<ArrayWrapper<Class<?>>, PerInitargs> {

    private static final long serialVersionUID = 1L;

    public PerCtor(Class<?> clazz) {
        super(new PerCtorEntryLoader(clazz));
    }

}

class PerCtorEntryLoader
        implements IMapEntryLoader<ArrayWrapper<Class<?>>, PerInitargs> {

    private final Class<?> clazz;

    public PerCtorEntryLoader(Class<?> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    @Override
    public PerInitargs loadValue(ArrayWrapper<Class<?>> key)
            throws LazyLoadException {
        Constructor<?> ctor;
        try {
            ctor = clazz.getConstructor(key.array);
        } catch (ReflectiveOperationException e) {
            throw new LazyLoadException(e.getMessage(), e);
        }
        return new PerInitargs(ctor);
    }

}

class PerInitargs
        extends LazyHashMap<ArrayWrapper<Object>, Object> {

    private static final long serialVersionUID = 1L;

    public PerInitargs(Constructor<?> ctor) {
        super(new PerInitargsEntryLoader(ctor));
    }

}

class PerInitargsEntryLoader
        implements IMapEntryLoader<ArrayWrapper<Object>, Object> {

    private final Constructor<?> ctor;

    public PerInitargsEntryLoader(Constructor<?> ctor) {
        if (ctor == null)
            throw new NullPointerException("ctor");
        this.ctor = ctor;
    }

    @Override
    public Object loadValue(ArrayWrapper<Object> key)
            throws LazyLoadException {
        try {
            return ctor.newInstance(key.array);
        } catch (ReflectiveOperationException e) {
            throw new LazyLoadException(e.getMessage(), e);
        }
    }

}
