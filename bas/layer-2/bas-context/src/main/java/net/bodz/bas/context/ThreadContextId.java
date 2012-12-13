package net.bodz.bas.context;

import net.bodz.bas.c.object.Nullables;

public class ThreadContextId
        extends AbstractContextId {

    private final IContextId fallbackContext;
    private final Thread thread;

    /**
     * @throws NullPointerException
     *             If <code>thread</code> is <code>null</code>.
     */
    public ThreadContextId(IContextId fallbackContext, Thread thread) {
        super(thread.getName());
        this.fallbackContext = fallbackContext;
        this.thread = thread;
    }

    public ThreadContextId(Thread thread) {
        this(StaticContextId.getInstance(), thread);
    }

    @Override
    public IContextId getParentContextId() {
        ThreadGroup threadGroup = thread.getThreadGroup();
        return new ThreadGroupContextId(fallbackContext, threadGroup);
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
        if (!(obj instanceof ThreadContextId))
            return false;
        ThreadContextId o = (ThreadContextId) obj;
        if (!thread.equals(o.thread))
            return false;
        if (!Nullables.equals(fallbackContext, o.fallbackContext))
            return false;
        return true;
    }

    public static ThreadContextId getInstance(IContextId fallbackContext, Thread thread) {
        return new ThreadContextId(fallbackContext, thread);
    }

    public static ThreadContextId getInstance(Thread thread) {
        return new ThreadContextId(thread);
    }

}
