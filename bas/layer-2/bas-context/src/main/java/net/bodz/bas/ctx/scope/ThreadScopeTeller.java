package net.bodz.bas.ctx.scope;

public class ThreadScopeTeller
        extends AbstractScopeTeller {

    @Override
    public IScopeInstance tell() {
        return new ThreadScopeInstance(Thread.currentThread());
    }

}
