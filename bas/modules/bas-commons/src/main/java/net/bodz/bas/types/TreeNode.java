package net.bodz.bas.types;

import java.util.List;

public interface TreeNode<N extends TreeNode<? extends N>> {

    /**
     * Returned list should not be modified, that is, it may be a copy of the actual children.
     * 
     * @return may <code>null</code> if no child.
     */
    List<? extends N> getChildren();

}
