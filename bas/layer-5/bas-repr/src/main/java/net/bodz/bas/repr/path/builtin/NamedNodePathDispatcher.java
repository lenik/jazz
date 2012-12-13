package net.bodz.bas.repr.path.builtin;

import java.util.List;

import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.tree.ITreeNode;

public class NamedNodePathDispatcher
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

        if (!(obj instanceof ITreeNode<?>))
            return null;

        String key = tokens.peek();
        if (key == null)
            return null;

        ITreeNode<?> locator = (ITreeNode<?>) obj;

        List<?> children = locator.getChildren();
        // TODO locator.getChildType().parse(key);
        Object result = children.get(-1/* key */);
        if (result == null)
            return null;

        tokens.shift();
        return new PathArrival(previous, result, key, tokens.getRemainingPath());
    }

}
