package net.bodz.bas.context;

import net.bodz.bas.fn.IExecutableX;

/**
 * XXX - not implemented well!
 */
public class CallerContextId
        extends AbstractContextId {

    IContextId fallbackContext;
    IContextId parent;

    public CallerContextId(IContextId parent, String contextName) {
        super(contextName);
    }

    @Override
    public IContextId getParentContextId() {
        return parent;
    }

    <X extends Exception> void runInChildContext(IExecutableX<X> executable) {

    }

    static ThreadLocal<CallerContextId> threadLocal;
    static {
        threadLocal = new ThreadLocal<CallerContextId>();
    }

    public static CallerContextId getCallerContext(IContextId fallbackContext) {
        CallerContextId context = threadLocal.get();
        return context;
    }

    public static CallerContextId getCallerContext() {
        return getCallerContext(StaticContextId.getInstance());
    }

}
