package net.bodz.bas.t.tree;

import static net.bodz.bas.t.tree.ITreeCallback.*;

import java.util.List;

public class TreeNodes {

    public static <N extends ITreeNode<? extends N>> void traverse(N start, ITreeCallback<? super N> callback) {
        _traverse(start, callback, 0);
    }

    static <N extends ITreeNode<? extends N>> int _traverse(N start, ITreeCallback<? super N> callback, int level) {
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
