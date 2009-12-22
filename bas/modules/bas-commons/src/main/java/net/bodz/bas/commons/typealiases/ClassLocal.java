package net.bodz.bas.commons.typealiases;

import java.util.IdentityHashMap;
import java.util.Map;

public class ClassLocal<T>
        extends IdentityHashMap<Class<?>, T> {

    private static final long serialVersionUID = 1L;

    public ClassLocal() {
        super();
    }

    public ClassLocal(int expectedMaxSize) {
        super(expectedMaxSize);
    }

    public ClassLocal(Map<? extends Class<?>, ? extends T> m) {
        super(m);
    }

}
