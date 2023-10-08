package net.bodz.bas.doc.node.util;

import net.bodz.bas.doc.node.INode;
import net.bodz.bas.t.list.NullAutoList;

public class NullDocNodeList<E extends INode>
        extends NullAutoList<E>
        implements
            IDocNodeList<E> {

    static final NullDocNodeList<INode> INSTANCE = new NullDocNodeList<>();

    @SuppressWarnings("unchecked")
    public static final <E extends INode> NullDocNodeList<E> getNullDocNodeList() {
        return (NullDocNodeList<E>) INSTANCE;
    }

}