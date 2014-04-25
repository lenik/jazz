package net.bodz.bas.repr.path.builtin;

import java.util.List;

import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;

public class ListPathDispatcher
        extends AbstractPathDispatcher {

    @Override
    public int getPriority() {
        return BuiltinPathDispatcherPriorities.PRIORITY_LIST;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        Object obj = previous.getTarget();
        if (obj == null)
            throw new PathDispatchException("null target.");

        if (!(obj instanceof List<?>))
            return null;

        String mayConsume = tokens.peek();
        if (mayConsume == null)
            return previous;

        Integer index = tokens.shiftInt();
        if (index == null)
            return null;

        List<?> list = (List<?>) obj;
        if (index >= list.size())
            throw new PathDispatchException("Index out of range: " + index);

        Object result = list.get(index);
        return new PathArrival(previous, result, mayConsume, tokens.getRemainingPath());
    }

}
