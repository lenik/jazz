package net.bodz.bas.types;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import net.bodz.bas.lang.a.Typedef;

@Typedef
public class TreeTextMap<T> extends TreeMap<String, T> implements TextMap<T> {

    private static final long serialVersionUID = -6119969398907948801L;

    public TreeTextMap() {
        super();
    }

    public TreeTextMap(Comparator<? super String> comparator) {
        super(comparator);
    }

    public TreeTextMap(Map<? extends String, ? extends T> m) {
        super(m);
    }

    public TreeTextMap(SortedMap<String, ? extends T> m) {
        super(m);
    }

}
