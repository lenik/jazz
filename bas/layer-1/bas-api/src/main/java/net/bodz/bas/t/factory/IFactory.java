package net.bodz.bas.t.factory;

import net.bodz.bas.err.CreateException;

public interface IFactory<T>
        extends IFactoryX<T, CreateException> {

    Class<? extends T> getType();

    T _create(Class<?>[] argTypes, Object... args)
            throws CreateException;

    T create(Object... args)
            throws CreateException;

    T create()
            throws CreateException;

    class Static<T>
            extends AbstractFactory<T> {

        private final T instance;

        public Static(T instance) {
            assert instance != null;
            this.instance = instance;
        }

        @Override
        public Class<? extends T> getType() {
            return (Class<? extends T>) instance.getClass();
        }

        @Override
        public T _create(Class<?>[] argTypes, Object... args) {
            return instance;
        }

    }

}
