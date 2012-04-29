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

    public ListDispatcher() {
        super();
    }

    public ListDispatcher(String name) {
        super(name);
    }

    @Override
    public int getOrder() {
        return DispatchConfig.ORDER_COLLECTION;
    }

    @Override
    public IPathArrival dispatch(IPathArrival context, ITokenQueue tokens)
            throws DispatchException {
        Object obj = context.getTarget();

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
        return new PathArrival(context, result, mayConsume);
    }

}
