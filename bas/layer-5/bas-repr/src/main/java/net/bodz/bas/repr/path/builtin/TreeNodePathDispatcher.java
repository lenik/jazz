package net.bodz.bas.repr.path.builtin;

import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.tree.ITreeNode;
import net.bodz.bas.t.variant.IVariantMap;

public class TreeNodePathDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_TREE_NODE;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        Object obj = previous.getTarget();

        if (obj == null)
            throw new PathDispatchException("null target.");

        if (!(obj instanceof ITreeNode))
            return null;

        String key = tokens.peek();
        if (key == null)
            return null;

        ITreeNode<?> node = (ITreeNode<?>) obj;

        ITreeNode<?> child = node.getChild(key);
        if (child == null)
            return null;

        return PathArrival.shift(previous, this, child, tokens);
    }

}
