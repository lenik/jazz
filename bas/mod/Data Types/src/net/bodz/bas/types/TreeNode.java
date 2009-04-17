package net.bodz.bas.types;

import java.util.List;

public interface TreeNode<T extends TreeNode<? extends T>> {

    List<? extends T> getChildren();

}
