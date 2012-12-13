package net.bodz.bas.t.tree.legacy;

import java.util.TreeSet;

public class NamedNodeSet
        extends TreeSet<INamedNode> {

    private static final long serialVersionUID = 1L;

    public NamedNodeSet() {
        super(NamedNodeComparator.getInstance());
    }

}