package net.bodz.bas.doc.node.util;

import java.util.function.Supplier;

import net.bodz.bas.doc.node.INode;
import net.bodz.bas.t.list.AutoList;

public class DocNodeList<T extends INode>
        extends AutoList<T>
        implements
            IDocNodeList<T> {

    private static final long serialVersionUID = 1L;

    public DocNodeList(Class<? extends T> elementClass) {
        super(elementClass);
    }

    public DocNodeList(Supplier<T> factory) {
        super(factory);
    }

}
