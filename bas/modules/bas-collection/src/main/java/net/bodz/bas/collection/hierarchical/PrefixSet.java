package net.bodz.bas.collection.hierarchical;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;


public class PrefixSet extends HierSet<String> {

    private static final long serialVersionUID = 5167281099342679515L;

    public PrefixSet(Collection<? extends String> c) {
        super(c);
    }

    public PrefixSet(Comparator<? super String> comparator) {
        super(comparator);
    }

    public PrefixSet(SortedSet<String> s) {
        super(s);
    }

    public PrefixSet() {
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
