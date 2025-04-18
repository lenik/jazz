package net.bodz.bas.repr.path;

import net.bodz.bas.err.ParseException;

public class DefaultTokenProcessor
        extends DecoratedTokenQueue
        implements ITokenProcessor {

    private static final long serialVersionUID = 1L;

    IPathArrival arrival;

    public DefaultTokenProcessor(IPathArrival previous, ITokenQueue tokenQueue) {
        super(tokenQueue);
        this.arrival = previous;
    }

    @Override
    public ITokenQueue clone() {
        return null;
    }

    @Override
    public IPathArrival getArrival() {
        return arrival;
    }

    @Override
    public void consume(int tokenCount, Object resolver, Object target) {
        PathArrival newArrival = PathArrival.shift(tokenCount, arrival, resolver, target, getWrapped());
        this.arrival = newArrival;
    }

}
