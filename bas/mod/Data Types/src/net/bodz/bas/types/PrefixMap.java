package net.bodz.bas.types;

import static net.bodz.bas.types.PrefixSet.startsWith;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

public class PrefixMap<V> extends HierMap<String, V> {

    private static final long serialVersionUID = 1143952435620164677L;

    public PrefixMap(Comparator<? super String> comparator) {
        super(startsWith, comparator);
    }

    public PrefixMap(Map<? extends String, ? extends V> m) {
        super(startsWith, m);
    }

    public PrefixMap(SortedMap<String, ? extends V> m) {
        super(startsWith, m);
    }

    public PrefixMap() {
        super(startsWith);
    }

}
