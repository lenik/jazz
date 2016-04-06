package net.bodz.bas.ctx.scope.id;

public class ThreadScopeDescriptor
        extends MutableScopeDescriptor {

    public ThreadScopeDescriptor(Thread thread) {
        super(thread.getName(), thread);
    }

    @Override
    protected IScopeDescriptor getInternalParent(Object identity) {
        Thread thread = (Thread) identity;
        ThreadGroup threadGroup = thread.getThreadGroup();
        if (threadGroup == null)
            return null;
        else
            return new ThreadGroupScopeDescriptor(threadGroup);
    }

}
