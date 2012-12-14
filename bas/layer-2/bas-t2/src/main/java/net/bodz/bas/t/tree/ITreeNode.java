package net.bodz.bas.t.tree;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ITreeNode {

    boolean isMutable();

    /**
     * Get the parent node.
     * 
     * @return <code>null</code> if this node doesn't have a parent.
     */
    ITreeNode getParent();

    int size();

    boolean isEmpty();

    boolean contains(String childKey);

    boolean contains(ITreeNode child);

    ITreeNode getChild(String key);

    ITreeNode getDescendant(String path);

    Set<String> childKeySet();

    Collection<? extends ITreeNode> children();

    String keyOf(ITreeNode child);

    List<String> keysOf(ITreeNode child);

}