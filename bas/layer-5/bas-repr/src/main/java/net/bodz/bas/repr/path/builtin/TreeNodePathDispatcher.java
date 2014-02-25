package net.bodz.bas.repr.path.builtin;

import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.tree.ITreeNode;

public class TreeNodePathDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = 11;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        Object obj = previous.getTarget();

        if (obj == null)
            throw new PathDispatchException("null target.");

        if (!(obj instanceof ITreeNode))
            return null;

        String key = tokens.peek();
        if (key == null)
            return previous;

        ITreeNode<?> node = (ITreeNode<?>) obj;

        ITreeNode<?> child = node.getChild(key);
        if (child == null)
            return null;

        tokens.shift();
        return new PathArrival(previous, child, key, tokens.getRemainingPath());
    }

}
