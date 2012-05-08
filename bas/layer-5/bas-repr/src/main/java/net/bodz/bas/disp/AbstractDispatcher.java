package net.bodz.bas.disp;

public abstract class AbstractDispatcher
        implements IDispatcher {

    String name;

    public AbstractDispatcher() {
    }

    public AbstractDispatcher(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOrder() {
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
