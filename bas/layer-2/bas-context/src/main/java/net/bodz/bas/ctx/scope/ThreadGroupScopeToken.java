package net.bodz.bas.ctx.scope;

public class ThreadGroupScopeToken
        extends MutableScopeToken {

    private final ThreadGroup threadGroup;

    /**
     * @param threadGroup
     *            Specify <code>null</code> to refer to top-level {@link ThreadGroup}.
     */
    public ThreadGroupScopeToken(ThreadGroup threadGroup) {
        super(threadGroup.getName(), threadGroup);
        this.threadGroup = threadGroup;
    }

    @Override
    public IScopeToken getParent() {
        if (threadGroup == null)
            return null;
        ThreadGroup parent = threadGroup.getParent();
        if (parent != null)
            return new ThreadGroupScopeToken(parent);
        return null;
    }

    public static ThreadGroupScopeToken getInstance(ThreadGroup threadGroup) {
        return new ThreadGroupScopeToken(threadGroup);
    }

}
