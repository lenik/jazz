package net.bodz.bas.context;

public class ThreadContext
        extends MutableContext {

    public ThreadContext(Thread thread) {
        super(thread.getName(), thread);
    }

    @Override
    protected IContext getInternalParent(Object identity) {
        Thread thread = (Thread) identity;
        ThreadGroup threadGroup = thread.getThreadGroup();
        if (threadGroup == null)
            return null;
        else
            return new ThreadGroupContext(threadGroup);
    }

}
