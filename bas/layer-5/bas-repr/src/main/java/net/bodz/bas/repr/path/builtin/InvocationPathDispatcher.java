package net.bodz.bas.repr.path.builtin;

import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IMethod;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.invoke.Invocation;
import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;

public class InvocationPathDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_INVOCATION;

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

        String methodName = tokens.peek();
        if (methodName == null)
            return null;

        IType type = PotatoTypes.getInstance().forClass(obj.getClass());
        IMethod method = type.getOverloadedMethod(methodName);
        if (method == null)
            return null;

        Invocation invocation = new Invocation(obj, method);

        tokens.stop();
        return PathArrival.shift(previous, invocation, tokens);
    }

}
