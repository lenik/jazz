package net.bodz.bas.lang.arch;

import net.bodz.bas.err.CreateException;

public interface IFactory<T> {

    Class<? extends T> getType();

    /**
     * @param argTypes
     *            may be <code>null</code> if not applicable. if argTypes isn't null, its length
     *            must be equals to the number of args.
     * @param args
     *            each argument must be instance of corresponding argType, or <code>null</code>.
     */
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
