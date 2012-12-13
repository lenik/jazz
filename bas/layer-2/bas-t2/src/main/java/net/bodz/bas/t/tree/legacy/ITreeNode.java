package net.bodz.bas.t.tree.legacy;

import java.util.List;

public interface ITreeNode<self_t> {

    /**
     * Returned list should not be modified, that is, it may be a copy of the actual children.
     * 
     * @return may <code>null</code> if no child.
     */
    List<? extends self_t> getChildren();

}
