package net.bodz.bas.context;

import net.bodz.bas.lang.arch.IExecutableX;

/**
 * XXX - not implemented well!
 */
public class CallerContext
        extends AbstractContext {

    IContext fallbackContext;
    IContext parent;

    public CallerContext(IContext parent, String contextName) {
        super(contextName);
    }

    @Override
    public IContext getParentContext() {
        return parent;
    }

    <X extends Exception> void runInChildContext(IExecutableX<X> executable) {

    }

    static ThreadLocal<CallerContext> threadLocal;
    static {
        threadLocal = new ThreadLocal<CallerContext>();
    }

    public static CallerContext getCallerContext(IContext fallbackContext) {
        CallerContext context = threadLocal.get();
        return context;
    }

    public static CallerContext getCallerContext() {
        return getCallerContext(StaticContext.getInstance());
    }

}
