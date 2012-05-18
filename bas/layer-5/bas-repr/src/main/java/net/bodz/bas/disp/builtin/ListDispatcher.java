package net.bodz.bas.disp.builtin;

import java.util.List;

import net.bodz.bas.disp.AbstractDispatcher;
import net.bodz.bas.disp.DispatchConfig;
import net.bodz.bas.disp.DispatchException;
import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;
import net.bodz.bas.disp.PathArrival;

public class ListDispatcher
        extends AbstractDispatcher {

    @Override
    public int getPriority() {
        return DispatchConfig.PRIORITY_COLLECTION;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws DispatchException {
        Object obj = previous.getTarget();

        if (!(obj instanceof List<?>))
            return null;

        String mayConsume = tokens.peek();

        Integer index = tokens.shiftInt();
        if (index == null)
            return null;

        List<?> list = (List<?>) obj;
        if (index >= list.size())
            throw new DispatchException("Index out of range: " + index);

        Object result = list.get(index);
        return new PathArrival(previous, result, mayConsume, tokens.getRemainingPath());
    }

}
