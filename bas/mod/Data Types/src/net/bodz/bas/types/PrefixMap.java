package net.bodz.bas.types;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;

import net.bodz.bas.lang.Predicate2;

public class PrefixMap<V> extends HierMap<String, V> {

    private static final long serialVersionUID = 1143952435620164677L;

    private static class Branchp implements Predicate2<String, String> {
        @Override
        public boolean eval(String a, String b) {
            if (a == b)
                return true;
            if (a == null || b == null)
                return false;
            if (a.startsWith(b))
                return true;
            if (b.startsWith(a))
                return true;
            return false;
        }
    }

    public static final Branchp BRANCHP = new Branchp();

    public PrefixMap(Comparator<? super String> comparator) {
        super(BRANCHP, comparator);
    }

    public PrefixMap(Map<? extends String, ? extends V> m) {
        super(BRANCHP, m);
    }

    public PrefixMap(SortedMap<String, ? extends V> m) {
        super(BRANCHP, m);
    }

    public PrefixMap() {
        super(BRANCHP);
    }

}
