package net.bodz.bas.ctx.scope;

public class ThreadGroupScopeTeller
        extends AbstractScopeTeller {

    @Override
    public IScopeInstance tell() {
        Thread thread = Thread.currentThread();
        ThreadGroup threadGroup = thread.getThreadGroup();
        if (threadGroup == null)
            return null;
        else
            return new ThreadGroupScopeInstance(threadGroup);
    }

}
