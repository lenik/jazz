package net.bodz.bas.disp.builtin;

import java.util.Map;

import net.bodz.bas.disp.AbstractDispatcher;
import net.bodz.bas.disp.DispatchConfig;
import net.bodz.bas.disp.DispatchException;
import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;
import net.bodz.bas.disp.PathArrival;

public class MapDispatcher
        extends AbstractDispatcher {

    @Override
    public int getPriority() {
        return DispatchConfig.PRIORITY_COLLECTION;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws DispatchException {
        Object obj = previous.getTarget();

        if (!(obj instanceof Map<?, ?>))
            return null;

        String key = tokens.shift();
        if (key == null)
            return null;

        Map<?, ?> map = (Map<?, ?>) obj;
        if (!map.containsKey(key))
            return null;

        Object result = map.get(key);
        return new PathArrival(previous, result, key, tokens.getRemainingPath());
    }

}
