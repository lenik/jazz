package net.bodz.bas.sysctx;

public class ThreadLocalSystemContext
        implements ISystemContext {

    private final ThreadLocal<ISystemContext> tls;

    public ThreadLocalSystemContext() {
        tls = new ThreadLocal<ISystemContext>();
    }

    ISystemContext get() {
        ISystemContext context = tls.get();
        if (context == null)
            tls.set(context = new SystemContext());
        return context;
    }

    @Override
    public IWorkingDirectoryContext getWorkingDirectoryContext() {
        return get().getWorkingDirectoryContext();
    }

    @Override
    public ILocaleContext getLocaleContext() {
        return get().getLocaleContext();
    }

}
