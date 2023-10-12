package net.bodz.bas.doc.node.conv;

import net.bodz.bas.doc.node.INode;
import net.bodz.bas.doc.word.xwpf.IXwNode;

public interface IXwNodeBuilder2<xw_t extends IXwNode, node_t extends INode> {

    xw_t createXwNode(IXwNode context);

    void prepare(xw_t x_node, node_t source);

}
