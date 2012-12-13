package net.bodz.bas.t.tree.legacy;

public interface ITreeNodePredicate<N extends ITreeNode<? extends N>> {

    boolean eval(N node);

}
