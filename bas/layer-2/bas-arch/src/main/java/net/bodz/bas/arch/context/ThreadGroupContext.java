package net.bodz.bas.arch.context;

import net.bodz.bas.lang.Nullables;

public class ThreadGroupContext
        extends AbstractContext {

    static final String topThreadGroupName = "TopThreadGroup";

    private final IContext fallbackContext;
    private final ThreadGroup threadGroup;

    /**
     * @param threadGroup
     *            Specify <code>null</code> to refer to top-level {@link ThreadGroup}.
     */
    public ThreadGroupContext(IContext fallbackContext, ThreadGroup threadGroup) {
        super(threadGroup == null ? topThreadGroupName : threadGroup.getName());
        this.fallbackContext = fallbackContext;
        this.threadGroup = threadGroup;
    }

    public ThreadGroupContext(ThreadGroup threadGroup) {
        this(StaticContext.getInstance(), threadGroup);
    }

    @Override
    public IContext getParentContext() {
        if (threadGroup == null)
            return null;
        ThreadGroup parent = threadGroup.getParent();
        if (parent != null)
            return new ThreadGroupContext(fallbackContext, parent);
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
        if (!(obj instanceof ThreadGroupContext))
            return false;
        ThreadGroupContext o = (ThreadGroupContext) obj;
        if (!Nullables.equals(threadGroup, o.threadGroup))
            return false;
        if (!Nullables.equals(fallbackContext, o.fallbackContext))
            return false;
        return true;
    }

    public static ThreadGroupContext getInstance(IContext fallbackContext, ThreadGroup threadGroup) {
        return new ThreadGroupContext(fallbackContext, threadGroup);
    }

    public static ThreadGroupContext getInstance(ThreadGroup threadGroup) {
        return new ThreadGroupContext(threadGroup);
    }

}
