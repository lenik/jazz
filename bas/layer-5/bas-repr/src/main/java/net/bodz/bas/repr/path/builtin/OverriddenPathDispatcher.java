package net.bodz.bas.repr.path.builtin;

import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;

public class OverriddenPathDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = 0;

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

        if (!(obj instanceof IPathDispatchable))
            return null;

        IPathDispatchable overridden = (IPathDispatchable) obj;
        return overridden.dispatch(previous, tokens);
    }

}
