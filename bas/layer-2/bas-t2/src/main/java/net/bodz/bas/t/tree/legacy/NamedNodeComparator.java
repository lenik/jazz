package net.bodz.bas.t.tree.legacy;

import java.util.Comparator;

public class NamedNodeComparator
        implements Comparator<INamedNode> {

    @Override
    public int compare(INamedNode o1, INamedNode o2) {
        assert o1 != null && o2 != null;
        int p1 = o1.getPriority();
        int p2 = o2.getPriority();
        int cmp = p1 - p2;
        if (cmp != 0)
            return cmp;

        String n1 = o1.getClass().getName();
        String n2 = o2.getClass().getName();
        cmp = n1.compareTo(n2);
        if (cmp != 0)
            return cmp;

        if (o1.equals(o2))
            return 0;
        return -1;
    }

    private static final NamedNodeComparator instance = new NamedNodeComparator();

    public static NamedNodeComparator getInstance() {
        return instance;
    }

}