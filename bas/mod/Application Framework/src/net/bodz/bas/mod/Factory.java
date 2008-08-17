package net.bodz.bas.mod;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.types.util.CompatMethods;

public interface Factory<T> {

    Class<T> getType();

    T create(Object... args) throws CreateException;

    class Ctor<ET> implements Factory<ET> {

        private Class<ET> clazz;
        private Object    outer;

        @SuppressWarnings("unchecked")
        public Ctor(Class<? extends ET> clazz, Object outer) {
            assert clazz != null;
            if (clazz.isMemberClass()) {
                if (outer == null)
                    throw new NullPointerException(
                            "no outer specified for member " + clazz);
            }
            this.clazz = (Class<ET>) clazz;
            this.outer = outer;
        }

        public Ctor(Class<? extends ET> clazz) {
            this(clazz, null);
        }

        @Override
        public Class<ET> getType() {
            return clazz;
        }

        @Override
        public ET create(Object... args) throws CreateException {
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

    class Static<ET> implements Factory<ET> {

        private final ET instance;

        public Static(ET instance) {
            assert instance != null;
            this.instance = instance;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Class<ET> getType() {
            return (Class<ET>) instance.getClass();
        }

        @Override
        public ET create(Object... args) {
            return instance;
        }

    }

}
