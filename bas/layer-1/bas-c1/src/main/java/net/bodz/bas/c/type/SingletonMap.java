package net.bodz.bas.c.type;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.java.util.LazyHashMap;
import net.bodz.bas.err.LazyLoadException;

public class SingletonMap
        extends LazyHashMap<Class<?>, Object> {

    private static final long serialVersionUID = 1L;

    static class Instantiator
            implements IMapEntryLoader<Class<?>, Object> {

        @Override
        public Object loadValue(Class<?> key)
                throws LazyLoadException {
            try {
                return key.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new LazyLoadException(e.getMessage(), e);
            }
        }

        static final Instantiator INSTANCE = new Instantiator();

    }

    public SingletonMap() {
        super(Instantiator.INSTANCE);
    }

}
