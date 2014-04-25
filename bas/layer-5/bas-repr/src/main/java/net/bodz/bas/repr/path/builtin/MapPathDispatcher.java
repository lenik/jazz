package net.bodz.bas.repr.path.builtin;

import java.util.Map;

import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;

public class MapPathDispatcher
        extends AbstractPathDispatcher {

    @Override
    public int getPriority() {
        return BuiltinPathDispatcherPriorities.PRIORITY_MAP;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        Object obj = previous.getTarget();

        if (obj == null)
            throw new PathDispatchException("null target.");

        if (!(obj instanceof Map<?, ?>))
            return null;

        String key = tokens.peek();
        if (key == null)
            return previous;

        Map<?, ?> map = (Map<?, ?>) obj;
        if (!map.containsKey(key))
            return null;

        Object result = map.get(key);
        return PathArrival.shift(previous, result, tokens);
    }

}
