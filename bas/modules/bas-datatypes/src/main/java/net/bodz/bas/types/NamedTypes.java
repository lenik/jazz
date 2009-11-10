package net.bodz.bas.types;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class NamedTypes<T> extends TreeMap<String, Class<? extends T>> {

    private static final long serialVersionUID = 6927344643332690478L;

    public NamedTypes() {
        super();
    }

    public NamedTypes(Comparator<? super String> comparator) {
        super(comparator);
    }

    public NamedTypes(Map<? extends String, ? extends Class<? extends T>> m) {
        super(m);
    }

    public NamedTypes(SortedMap<String, ? extends Class<? extends T>> m) {
        super(m);
    }

}
