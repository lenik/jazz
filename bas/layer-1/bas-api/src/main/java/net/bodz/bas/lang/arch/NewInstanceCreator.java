package net.bodz.bas.lang.arch;

import net.bodz.bas.err.CreateException;

public class NewInstanceCreator<T>
        extends AbstractCreator<T> {

    final Class<? extends T> clazz;

    public NewInstanceCreator(Class<? extends T> clazz) {
        if (clazz == null)
            throw new NullPointerException("clazz");
        this.clazz = clazz;
    }

    @Override
    public T create(Object... parameters)
            throws CreateException {
        try {
            T instance = clazz.newInstance();
            return instance;
        } catch (ReflectiveOperationException e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

}
