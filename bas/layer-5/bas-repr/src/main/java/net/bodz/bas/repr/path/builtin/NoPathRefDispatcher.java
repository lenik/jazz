package net.bodz.bas.repr.path.builtin;

import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.INoPathRef;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class NoPathRefDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = 1000;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        IPathArrival arrival = previous;

        Object target = arrival.getTarget();
        if (!(target instanceof INoPathRef))
            return null;

        try {
            target = ((INoPathRef) target).getTarget();
        } catch (Exception e) {
            throw new PathDispatchException(e.getMessage(), e);
        }
        // if (target != null)
        return new PathArrival(arrival, this, target, new String[0], arrival.getRemainingPath());
    }

}
