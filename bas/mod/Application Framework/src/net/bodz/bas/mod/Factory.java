package net.bodz.bas.mod;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.util.CompatMethods;

public interface Factory<T> {

    Class<? extends T> getType();

    T create(Object... args) throws CreateException;

    class Ctor<T> implements Factory<T> {

        private Class<? extends T> clazz;
        private Object             outer;

        public Ctor(Class<? extends T> clazz, Object outer) {
            assert clazz != null;
            if (clazz.isMemberClass()) {
                if (outer == null)
                    throw new NullPointerException(AppNLS
                            .getString("Factory.needEnclosing") + clazz); //$NON-NLS-1$
            }
            this.clazz = clazz;
            this.outer = outer;
        }

        public Ctor(Class<? extends T> clazz) {
            this(clazz, null);
        }

        @Override
        public Class<? extends T> getType() {
            return clazz;
        }

        @Override
        public T create(Object... args) throws CreateException {
            try {
                if (clazz.isMemberClass())
                    return CompatMethods.newMemberInstance(clazz, outer, args);
                else
                    return CompatMethods.newInstance(clazz, args);
            } catch (Exception e) {
                throw new CreateException(e.getMessage(), e);
            }
        }
    }

    class Static<T> implements Factory<T> {

        private final T instance;

        public Static(T instance) {
            assert instance != null;
            this.instance = instance;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Class<? extends T> getType() {
            return (Class<? extends T>) instance.getClass();
        }

        @Override
        public T create(Object... args) {
            return instance;
        }

    }

}
