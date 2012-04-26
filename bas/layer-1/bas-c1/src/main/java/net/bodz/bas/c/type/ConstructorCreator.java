package net.bodz.bas.c.type;

import java.lang.reflect.Constructor;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.model.AbstractCreator;

import org.apache.commons.lang.ArrayUtils;

public final class ConstructorCreator<T>
        extends AbstractCreator<T> {

    private final Constructor<T> constructor;
    private final Object[] parameters;

    public ConstructorCreator(Constructor<T> constructor) {
        this(constructor, EMPTY_PARAMS);
    }

    public ConstructorCreator(Constructor<T> constructor, Object... parameters) {
        if (constructor == null)
            throw new NullPointerException("constructor");
        this.constructor = constructor;
        this.parameters = parameters;
    }

    public ConstructorCreator(Class<?> clazz)
            throws ReflectiveOperationException {
        this(clazz, null, EMPTY_PARAMS);
    }

    public ConstructorCreator(Class<?> clazz, Class<?>[] parameterTypes, Object... parameters)
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
    public T create(Object... parameters)
            throws CreateException {
        try {
            return _create(parameters);
        } catch (ReflectiveOperationException e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

    public T _create(Object... moreParameters)
            throws ReflectiveOperationException {
        Object[] all = ArrayUtils.addAll(parameters, moreParameters);
        return constructor.newInstance(all);
    }

}
