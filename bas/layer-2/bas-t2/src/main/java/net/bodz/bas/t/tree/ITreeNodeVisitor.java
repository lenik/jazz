package net.bodz.bas.t.tree;

public interface ITreeNodeVisitor<node_t extends ITreeNode<node_t>> {

    void node(node_t node);

}
