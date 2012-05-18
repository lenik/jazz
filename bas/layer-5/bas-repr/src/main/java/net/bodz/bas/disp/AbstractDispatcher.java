package net.bodz.bas.disp;

public abstract class AbstractDispatcher
        implements IDispatcher {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, String path)
            throws DispatchException {
        TokenQueue tokens = new TokenQueue(path);
        IPathArrival target = dispatch(previous, tokens);
        if (!tokens.isEmpty())
            throw new DispatchException("Dispatch failed: " + tokens);
        return target;
    }

}
