package net.bodz.bas.repr.path.builtin;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.util.OidTree;

public class OidPathDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = 10;

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

        if (!(obj instanceof OidTree<?>))
            return null;

        OidTree<?> tree = (OidTree<?>) obj;

        int index = 0;
        Object lastNode = null;
        int lastNodeIndex = 0;

        while (index < tokens.available()) {
            String token = tokens.peek(index);
            if (!StringPred.isInteger(token))
                break;

            int ord = Integer.parseInt(token);
            if (!tree.contains(ord))
                break;

            index++;

            tree = tree.get(ord);
            Object node = tree.get();
            if (node != null) {
                lastNode = node;
                lastNodeIndex = index;
            }
        }

        if (lastNode == null)
            return null;

        String[] consumedTokens = tokens.shift(lastNodeIndex);
        return new PathArrival(previous, lastNode, consumedTokens, tokens.getRemainingPath());
    }

}
