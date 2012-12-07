package net.bodz.bas.repr.naming;

import java.util.TreeSet;

public class NamedNodeSet
        extends TreeSet<INamedNode> {

    private static final long serialVersionUID = 1L;

    public NamedNodeSet() {
        super(NamedNodeComparator.getInstance());
    }

}