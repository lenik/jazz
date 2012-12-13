package net.bodz.bas.t.tree;

public interface TreeNodePredicator<N extends TreeNode<? extends N>> {

    boolean eval(N node);

}
