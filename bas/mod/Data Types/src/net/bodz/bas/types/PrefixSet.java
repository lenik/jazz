package net.bodz.bas.types;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;

import net.bodz.bas.lang.Predicate2s;

public class PrefixSet extends HierSet<String> {

    private static final long serialVersionUID = 5167281099342679515L;

    static class StartsWith implements Predicate2s<String> {
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

    static final StartsWith startsWith = new StartsWith();

    public PrefixSet(Collection<? extends String> c) {
        super(startsWith, c);
    }

    public PrefixSet(Comparator<? super String> comparator) {
        super(startsWith, comparator);
    }

    public PrefixSet(SortedSet<String> s) {
        super(startsWith, s);
    }

    public PrefixSet() {
        super(startsWith);
    }

}
