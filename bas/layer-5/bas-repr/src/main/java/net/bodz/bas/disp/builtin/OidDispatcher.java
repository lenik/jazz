package net.bodz.bas.disp.builtin;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.disp.AbstractDispatcher;
import net.bodz.bas.disp.DispatchConfig;
import net.bodz.bas.disp.DispatchException;
import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;
import net.bodz.bas.disp.PathArrival;
import net.bodz.bas.disp.util.OidTree;

public class OidDispatcher
        extends AbstractDispatcher {

    @Override
    public int getOrder() {
        return DispatchConfig.ORDER_OID;
    }

    @Override
    public IPathArrival dispatch(IPathArrival context, ITokenQueue tokens)
            throws DispatchException {
        Object obj = context.getTarget();

        if (!(obj instanceof OidTree<?>))
            return null;

        OidTree<?> tree = (OidTree<?>) obj;

        int index = 0;
        Object lastNode = null;
        int lastNodeIndex = 0;

        while (index < tokens.available()) {
            String token = tokens.peek(index);
            if (!StringPred.isNumber(token))
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
        return new PathArrival(context, lastNode, consumedTokens);
    }

}
