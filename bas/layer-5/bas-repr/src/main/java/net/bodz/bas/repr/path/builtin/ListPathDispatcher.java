package net.bodz.bas.repr.path.builtin;

import java.util.List;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.IPathDispatcher;
import net.bodz.bas.t.variant.IVariantMap;

public class ListPathDispatcher
        implements
            IPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_LIST;
    static final Class<?> ACCEPT_TYPES[] = { List.class };

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
        if (source == null)
            throw new PathDispatchException("null target.");

        if (! (source instanceof List<?>))
            return null;

        String head = tokens.peek();
        if (head == null)
            return null;

        int index = tokens.shiftInt(-1); // -1 if parse error
        if (index < 0)
            return null;

        List<?> list = (List<?>) source;
        if (index >= list.size())
            throw new PathDispatchException("Index out of range: " + index);

        Object result = list.get(index);
        return PathArrival.shift(previous, this, result, tokens);
    }

}
