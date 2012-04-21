package net.bodz.bas.context;

public class CurrentThreadContextResolver
        extends AbstractContextResolver {

    @Override
    public IContext resolve(IContext fallback) {
        Thread currentThread = Thread.currentThread();
        return ThreadContext.getInstance(fallback, currentThread);
    }

    static final CurrentThreadContextResolver instance = new CurrentThreadContextResolver();

    public static CurrentThreadContextResolver getInstance() {
        return instance;
    }

}
