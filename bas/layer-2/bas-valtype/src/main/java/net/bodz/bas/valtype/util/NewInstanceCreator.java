package net.bodz.bas.valtype.util;

import java.lang.reflect.Constructor;

import net.bodz.bas.closure.ICreatorX;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;

import org.apache.commons.lang.ArrayUtils;

public class NewInstanceCreator<T>
        implements ICreatorX<T, ReflectiveOperationException> {

    private final Constructor<T> constructor;
    private final Object[] parameters;

    public NewInstanceCreator(Constructor<T> constructor) {
        if (constructor == null)
            throw new NullPointerException("constructor");
        this.constructor = constructor;
        this.parameters = null;
    }

    public NewInstanceCreator(Constructor<T> constructor, Object... parameters) {
        if (constructor == null)
            throw new NullPointerException("constructor");
        this.constructor = constructor;
        this.parameters = parameters;
    }

    public NewInstanceCreator(Class<?> clazz)
            throws ReflectiveOperationException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        constructor = (Constructor<T>) Jdk7Reflect.getConstructor(clazz);
        this.parameters = null;
    }

    public NewInstanceCreator(Class<?> clazz, Class<?>[] parameterTypes, Object... parameters)
            throws ReflectiveOperationException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (parameterTypes == null)
            throw new NullPointerException("parameterTypes");
        constructor = (Constructor<T>) Jdk7Reflect.getConstructor(clazz, parameterTypes);
        this.parameters = parameters;
    }

    @Override
    public T create()
            throws ReflectiveOperationException {
        if (parameters == null)
            return Jdk7Reflect.newInstance(constructor);
        else
            return Jdk7Reflect.newInstance(constructor, parameters);
    }

    @Override
    public T create(Object... moreParameters)
            throws ReflectiveOperationException {
        Object[] params = this.parameters;
        if (params == null)
            if (moreParameters == null)
                params = new Object[0];
            else
                params = moreParameters;
        else if (moreParameters != null)
            params = ArrayUtils.addAll(params, moreParameters);

        return Jdk7Reflect.newInstance(constructor, params);
    }

}
