package net.bodz.bas.doc.node.conv;

import net.bodz.bas.doc.node.INode;
import net.bodz.bas.doc.word.xwpf.IXwNode;

public abstract class XwNodeBuilder<xw_t extends IXwNode>
        implements
            IXwNodeBuilder2<xw_t, INode> {

    @Override
    public final void prepare(xw_t x_node, INode source) {
        prepare(x_node);
    }

    public void prepare(xw_t x_node) {
    }

}
