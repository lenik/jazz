package net.bodz.bas.collection.tree;

public interface TreeNodePredicator<N extends TreeNode<? extends N>> {

    boolean eval(N node);

}
