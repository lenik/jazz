package net.bodz.bas.arch.context;

import net.bodz.bas.util.Nullables;

public class ThreadContext
        extends AbstractContext {

    private final IContext fallbackContext;
    private final Thread thread;

    /**
     * @throws NullPointerException
     *             If <code>thread</code> is <code>null</code>.
     */
    public ThreadContext(IContext fallbackContext, Thread thread) {
        super(thread.getName());
        this.fallbackContext = fallbackContext;
        this.thread = thread;
    }

    public ThreadContext(Thread thread) {
        this(StaticContext.getInstance(), thread);
    }

    @Override
    public IContext getParentContext() {
        ThreadGroup threadGroup = thread.getThreadGroup();
        return new ThreadGroupContext(fallbackContext, threadGroup);
    }

    @Override
    public int hashCode() {
        int hash = 0x219ba60b;
        hash += thread.hashCode();
        if (fallbackContext != null)
            hash += fallbackContext.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ThreadContext))
            return false;
        ThreadContext o = (ThreadContext) obj;
        if (!thread.equals(o.thread))
            return false;
        if (!Nullables.equals(fallbackContext, o.fallbackContext))
            return false;
        return true;
    }

    public static ThreadContext getInstance(IContext fallbackContext, Thread thread) {
        return new ThreadContext(fallbackContext, thread);
    }

    public static ThreadContext getInstance(Thread thread) {
        return new ThreadContext(thread);
    }

    public static ThreadContext getCurrentThreadContext(IContext fallbackContext) {
        Thread currentThread = Thread.currentThread();
        return getInstance(fallbackContext, currentThread);
    }

    public static ThreadContext getCurrentThreadContext() {
        return getCurrentThreadContext(StaticContext.getInstance());
    }

}
