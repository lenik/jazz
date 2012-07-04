package net.bodz.bas.collection.tree;

import static net.bodz.bas.collection.tree.TreeCallback.*;

import java.util.List;

public class TreeNodes {

    public static <N extends TreeNode<? extends N>> void traverse(N start, TreeCallback<? super N> callback) {
        _traverse(start, callback, 0);
    }

    static <N extends TreeNode<? extends N>> int _traverse(N start, TreeCallback<? super N> callback, int level) {
        int status = callback.each(start, level);
        if (status != OK)
            return status;
        List<? extends N> children = start.getChildren();
        if (children != null)
            L: for (N child : children) {
                status = _traverse(child, callback, level + 1);
                switch (status) {
                case OK:
                    break;
                case BREAK:
                    break L;
                case CANCEL:
                    return CANCEL;
                }
            }
        return OK;
    }

}
