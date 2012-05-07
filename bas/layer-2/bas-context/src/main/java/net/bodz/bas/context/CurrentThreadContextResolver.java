package net.bodz.bas.context;

public class CurrentThreadContextResolver
        extends AbstractContextResolver {

    @Override
    public IContextId resolve(IContextId fallback) {
        Thread currentThread = Thread.currentThread();
        return ThreadContextId.getInstance(fallback, currentThread);
    }

    static final CurrentThreadContextResolver instance = new CurrentThreadContextResolver();

    public static CurrentThreadContextResolver getInstance() {
        return instance;
    }

}
