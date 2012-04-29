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
    public IPathArrival dispatch(IPathArrival context, String path)
            throws DispatchException {
        TokenQueue tq = new TokenQueue(path);
        IPathArrival target = dispatch(context, tq);
        if (!tq.isEmpty())
            throw new DispatchException("Dispatch isn't completed: " + tq);
        return target;
    }

}
