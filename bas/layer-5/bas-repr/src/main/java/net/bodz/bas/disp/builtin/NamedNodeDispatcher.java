package net.bodz.bas.disp.builtin;

import java.util.List;

import net.bodz.bas.collection.tree.TreeNode;
import net.bodz.bas.disp.AbstractDispatcher;
import net.bodz.bas.disp.DispatchConfig;
import net.bodz.bas.disp.DispatchException;
import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;
import net.bodz.bas.disp.PathArrival;

public class NamedNodeDispatcher
        extends AbstractDispatcher {

    public NamedNodeDispatcher() {
        super();
    }

    public NamedNodeDispatcher(String name) {
        super(name);
    }

    @Override
    public int getOrder() {
        return DispatchConfig.ORDER_NAMED_NODE;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws DispatchException {
        Object obj = previous.getTarget();

        if (!(obj instanceof TreeNode<?>))
            return null;

        String key = tokens.peek();
        if (key == null)
            return null;

        TreeNode<?> locator = (TreeNode<?>) obj;

        List<?> children = locator.getChildren();
        // TODO locator.getChildType().parse(key);
        Object result = children.get(-1/* key */);
        if (result == null)
            return null;

        tokens.shift();
        return new PathArrival(previous, result, key, tokens.getRemainingPath());
    }

}
