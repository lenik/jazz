package net.bodz.bas.t.factory;

import net.bodz.bas.err.CreateException;

public class Instantiator<T>
        extends AbstractFactory<T> {

    final Class<? extends T> clazz;

    public Instantiator(Class<? extends T> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    @Override
    public Class<? extends T> getType() {
        return clazz;
    }

    @Override
    public T _create(Class<?>[] argTypes, Object... args)
            throws CreateException {
        try {
            T instance = clazz.newInstance();
            return instance;
        } catch (ReflectiveOperationException e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

    public static <T> Instantiator<T> forClass(Class<T> clazz) {
        return new Instantiator<T>(clazz);
    }

}
