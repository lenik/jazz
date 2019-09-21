package net.bodz.bas.c.type;

import java.lang.reflect.Constructor;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.t.factory.AbstractFactory;

public final class ConstructorFactory<T>
        extends AbstractFactory<T> {

    private final Constructor<T> constructor;
    private final Object[] parameters;

    public ConstructorFactory(Constructor<T> constructor) {
        this(constructor, EMPTY_PARAMS);
    }

    public ConstructorFactory(Constructor<T> constructor, Object... parameters) {
        if (constructor == null)
            throw new NullPointerException("constructor");
        this.constructor = constructor;
        this.parameters = parameters;
    }

    public ConstructorFactory(Class<?> clazz)
            throws ReflectiveOperationException {
        this(clazz, null, EMPTY_PARAMS);
    }

    public ConstructorFactory(Class<?> clazz, Class<?>[] parameterTypes, Object... parameters)
            throws ReflectiveOperationException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (parameterTypes == null)
            this.constructor = (Constructor<T>) clazz.getConstructor();
        else
            this.constructor = (Constructor<T>) clazz.getConstructor(parameterTypes);
        this.parameters = parameters;
    }

    @Override
    public Class<? extends T> getType() {
        return constructor.getDeclaringClass();
    }

    @Override
    public T _create(Class<?>[] argTypes, Object... args)
            throws CreateException {
        Object[] all = Arrays.concat(parameters, args);
        try {
            return constructor.newInstance(all);
        } catch (ReflectiveOperationException e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

}
