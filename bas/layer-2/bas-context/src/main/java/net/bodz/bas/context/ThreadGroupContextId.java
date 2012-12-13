package net.bodz.bas.context;

import net.bodz.bas.c.object.Nullables;

public class ThreadGroupContextId
        extends AbstractContextId {

    static final String topThreadGroupName = "TopThreadGroup";

    private final IContextId fallbackContext;
    private final ThreadGroup threadGroup;

    /**
     * @param threadGroup
     *            Specify <code>null</code> to refer to top-level {@link ThreadGroup}.
     */
    public ThreadGroupContextId(IContextId fallbackContext, ThreadGroup threadGroup) {
        super(threadGroup == null ? topThreadGroupName : threadGroup.getName());
        this.fallbackContext = fallbackContext;
        this.threadGroup = threadGroup;
    }

    public ThreadGroupContextId(ThreadGroup threadGroup) {
        this(StaticContextId.getInstance(), threadGroup);
    }

    @Override
    public IContextId getParentContextId() {
        if (threadGroup == null)
            return null;
        ThreadGroup parent = threadGroup.getParent();
        if (parent != null)
            return new ThreadGroupContextId(fallbackContext, parent);
        return fallbackContext;
    }

    @Override
    public int hashCode() {
        int hash = 0xd317c4f2;
        if (threadGroup != null)
            hash += threadGroup.hashCode();
        if (fallbackContext != null)
            hash += fallbackContext.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ThreadGroupContextId))
            return false;
        ThreadGroupContextId o = (ThreadGroupContextId) obj;
        if (!Nullables.equals(threadGroup, o.threadGroup))
            return false;
        if (!Nullables.equals(fallbackContext, o.fallbackContext))
            return false;
        return true;
    }

    public static ThreadGroupContextId getInstance(IContextId fallbackContext, ThreadGroup threadGroup) {
        return new ThreadGroupContextId(fallbackContext, threadGroup);
    }

    public static ThreadGroupContextId getInstance(ThreadGroup threadGroup) {
        return new ThreadGroupContextId(threadGroup);
    }

}
