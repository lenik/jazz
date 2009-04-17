package net.bodz.bas.types;

import static net.bodz.bas.types.TreeCallback.BREAK;
import static net.bodz.bas.types.TreeCallback.CANCEL;
import static net.bodz.bas.types.TreeCallback.OK;

import java.util.List;

public class TreeNodes {

    public static <T extends TreeNode<? extends T>> void traverse(T start,
            TreeCallback<? super T> callback) {
        _traverse(start, callback, 0);
    }

    static <T extends TreeNode<? extends T>> int _traverse(T start,
            TreeCallback<? super T> callback, int level) {
        int status = callback.each(start, level);
        if (status != OK)
            return status;
        List<? extends T> children = start.getChildren();
        L: for (T child : children) {
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
