package net.bodz.bas.disp.builtin;

import net.bodz.bas.disp.AbstractDispatcher;
import net.bodz.bas.disp.DispatchConfig;
import net.bodz.bas.disp.DispatchException;
import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.IPathDispatchable;
import net.bodz.bas.disp.ITokenQueue;

public class DynamicDispatcher
        extends AbstractDispatcher {

    @Override
    public int getPriority() {
        return DispatchConfig.DYNAMIC_ORDER;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws DispatchException {
        Object obj = previous.getTarget();
        if (!(obj instanceof IPathDispatchable))
            return null;

        IPathDispatchable dispatchable = (IPathDispatchable) obj;
        return dispatchable.dispatch(previous, tokens);
    }

}
