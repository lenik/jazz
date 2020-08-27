package net.bodz.bas.repr.path.builtin;

import java.util.Map;

import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class MapPathDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_MAP;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        Object obj = previous.getTarget();

        if (obj == null)
            throw new PathDispatchException("null target.");

        if (!(obj instanceof Map<?, ?>))
            return null;

        String key = tokens.peek();
        if (key == null)
            return null;

        Map<?, ?> map = (Map<?, ?>) obj;
        if (!map.containsKey(key))
            return null;

        Object result = map.get(key);
        return PathArrival.shift(previous, this, result, tokens);
    }

}
