package net.bodz.bas.doc.node.util;

import net.bodz.bas.doc.node.INode;

public class DocNodeLists {

    public static <E extends INode> NullDocNodeList<E> emptyList() {
        return NullDocNodeList.getNullDocNodeList();
    }

}
