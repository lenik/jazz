package net.bodz.bas.types.util;

import java.util.IdentityHashMap;
import java.util.Map;

import net.bodz.bas.lang.a.Typedef;

@Typedef
public class IdentMap<V> extends IdentityHashMap<Object, V> {

    private static final long serialVersionUID = 800527055423862756L;

    public IdentMap() {
        super();
    }

    public IdentMap(int expectedMaxSize) {
        super(expectedMaxSize);
    }

    public IdentMap(Map<? extends Object, ? extends V> m) {
        super(m);
    }

}
