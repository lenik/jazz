package net.bodz.bas.mod;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.types.util.CompatMethods;

public interface Factory {

    Class<?> getType();

    Object create(Object... args) throws CreateException;

    class Ctor implements Factory {

        private Class<?> clazz;
        private Object   outer;

        public Ctor(Class<?> clazz, Object outer) {
            assert clazz != null;
            if (clazz.isMemberClass()) {
                if (outer == null)
                    throw new NullPointerException(
                            "no outer specified for member " + clazz);
            }
            this.clazz = clazz;
            this.outer = outer;
        }

        public Ctor(Class<?> clazz) {
            this(clazz, null);
        }

        @Override
        public Class<?> getType() {
            return clazz;
        }

        @Override
        public Object create(Object... args) throws CreateException {
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

    class Static implements Factory {

        private final Object instance;

        public Static(Object instance) {
            assert instance != null;
            this.instance = instance;
        }

        @Override
        public Class<?> getType() {
            return (Class<?>) instance.getClass();
        }

        @Override
        public Object create(Object... args) {
            return instance;
        }

    }

}
