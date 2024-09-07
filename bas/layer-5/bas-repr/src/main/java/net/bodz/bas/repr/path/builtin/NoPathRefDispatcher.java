package net.bodz.bas.repr.path.builtin;

import net.bodz.bas.repr.path.INoPathRef;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatcher;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class NoPathRefDispatcher
        implements
            IPathDispatcher {

    public static final int PRIORITY = 1000;
    static final Class<?> ACCEPT_TYPES[] = { INoPathRef.class };

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public Class<?>[] getAcceptTypes() {
        return ACCEPT_TYPES;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, Object source, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        IPathArrival arrival = previous;

        if (! (source instanceof INoPathRef))
            return null;

        Object target;
        try {
            target = ((INoPathRef) source).getTarget();
        } catch (Exception e) {
            throw new PathDispatchException(e.getMessage(), e);
        }
        // if (target != null)
        return new PathArrival(arrival, this, target, new String[0], arrival.getRemainingPath());
    }

}
