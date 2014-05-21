package net.bodz.bas.context;

public class ThreadGroupContext
        extends MutableContext {

    private final ThreadGroup threadGroup;

    /**
     * @param threadGroup
     *            Specify <code>null</code> to refer to top-level {@link ThreadGroup}.
     */
    public ThreadGroupContext(ThreadGroup threadGroup) {
        super(threadGroup.getName(), threadGroup);
        this.threadGroup = threadGroup;
    }

    @Override
    public IContext getParent() {
        if (threadGroup == null)
            return null;
        ThreadGroup parent = threadGroup.getParent();
        if (parent != null)
            return new ThreadGroupContext(parent);
        return null;
    }

    public static ThreadGroupContext getInstance(ThreadGroup threadGroup) {
        return new ThreadGroupContext(threadGroup);
    }

}
