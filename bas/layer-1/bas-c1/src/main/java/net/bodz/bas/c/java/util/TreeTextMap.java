package net.bodz.bas.c.java.util;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TreeTextMap<V>
        extends TreeMap<String, V>
        implements TextMap<V> {

    private static final long serialVersionUID = 1L;

    public TreeTextMap() {
        super();
    }

    public TreeTextMap(Comparator<? super String> comparator) {
        super(comparator);
    }

    public TreeTextMap(Map<? extends String, ? extends V> m) {
        super(m);
    }

    public TreeTextMap(SortedMap<String, ? extends V> m) {
        super(m);
    }

}
