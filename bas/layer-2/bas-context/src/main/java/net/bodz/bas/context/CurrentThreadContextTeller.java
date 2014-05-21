package net.bodz.bas.context;

public class CurrentThreadContextTeller
        implements IContextTeller {

    @Override
    public IContext tell() {
        return new ThreadContext(Thread.currentThread());
    }

}
