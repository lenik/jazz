package net.bodz.bas.types;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

public class PrefixMap<V> extends HierMap<String, V> {

    private static final long serialVersionUID = 1143952435620164677L;

    public PrefixMap(Comparator<? super String> comparator) {
        super(comparator);
    }

    public PrefixMap(Map<? extends String, ? extends V> m) {
        super(m);
    }

    public PrefixMap(SortedMap<String, ? extends V> m) {
        super(m);
    }

    public PrefixMap() {
        super();
    }

    @Override
    public boolean derives(String prefix, String s) {
        return s.startsWith(prefix);
    }

    @Override
    public String shrink(String s) {
        if (s.isEmpty())
            return null;
        return s.substring(0, s.length() - 1);
    }

}
