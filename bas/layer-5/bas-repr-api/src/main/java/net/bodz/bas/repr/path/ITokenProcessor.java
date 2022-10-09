package net.bodz.bas.repr.path;

public interface ITokenProcessor
        extends
            ITokenQueue {

    /**
     * Get current arrival.
     */
    IPathArrival getArrival();

    void consume(int tokenCount, Object resolver, Object target);

}
