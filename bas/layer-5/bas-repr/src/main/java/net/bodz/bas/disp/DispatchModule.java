package net.bodz.bas.disp;

import net.bodz.bas.err.IllegalUsageException;

public abstract class DispatchModule
        // extends Module
        implements IPathDispatchable {

    private IDispatcher delegate;

    public abstract IDispatcher getDispatcher();

    final IDispatcher loadDispatcher() {
        if (delegate == null) {
            synchronized (this) {
                if (delegate == null) {

                    delegate = getDispatcher();

                    if (delegate == null)
                        throw new IllegalUsageException("Get delegated dispatcher returns null");
                }
            }
        }
        return delegate;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws DispatchException {
        return loadDispatcher().dispatch(previous, tokens);
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, String path)
            throws DispatchException {
        return loadDispatcher().dispatch(previous, path);
    }

}