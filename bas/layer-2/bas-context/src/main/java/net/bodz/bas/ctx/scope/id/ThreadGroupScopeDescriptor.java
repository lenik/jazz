package net.bodz.bas.ctx.scope.id;

public class ThreadGroupScopeDescriptor
        extends MutableScopeDescriptor {

    private final ThreadGroup threadGroup;

    /**
     * @param threadGroup
     *            Specify <code>null</code> to refer to top-level {@link ThreadGroup}.
     */
    public ThreadGroupScopeDescriptor(ThreadGroup threadGroup) {
        super(threadGroup.getName(), threadGroup);
        this.threadGroup = threadGroup;
    }

    @Override
    public IScopeDescriptor getParent() {
        if (threadGroup == null)
            return null;
        ThreadGroup parent = threadGroup.getParent();
        if (parent != null)
            return new ThreadGroupScopeDescriptor(parent);
        return null;
    }

    public static ThreadGroupScopeDescriptor getInstance(ThreadGroup threadGroup) {
        return new ThreadGroupScopeDescriptor(threadGroup);
    }

}
