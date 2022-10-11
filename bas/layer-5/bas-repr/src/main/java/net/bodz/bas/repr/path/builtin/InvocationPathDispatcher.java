package net.bodz.bas.repr.path.builtin;

import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.invoke.Invocation;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatcher;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class InvocationPathDispatcher
        implements
            IPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_INVOCATION;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, Object source, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (source == null)
            throw new PathDispatchException("null source.");

        String methodName = tokens.peek();
        if (methodName == null)
            return null;

        IType type = PotatoTypes.getInstance().loadType(source.getClass());
        IMethod method = type.getOverloadedMethod(methodName);
        if (method == null)
            return null;

        Invocation invocation = new Invocation(source, method);

        tokens.stop();
        return PathArrival.shift(previous, this, invocation, tokens);
    }

}
