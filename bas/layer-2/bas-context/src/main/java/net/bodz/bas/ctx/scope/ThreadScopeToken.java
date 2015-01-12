package net.bodz.bas.ctx.scope;

public class ThreadScopeToken
        extends MutableScopeToken {

    public ThreadScopeToken(Thread thread) {
        super(thread.getName(), thread);
    }

    @Override
    protected IScopeToken getInternalParent(Object identity) {
        Thread thread = (Thread) identity;
        ThreadGroup threadGroup = thread.getThreadGroup();
        if (threadGroup == null)
            return null;
        else
            return new ThreadGroupScopeToken(threadGroup);
    }

}
