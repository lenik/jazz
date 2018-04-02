package net.bodz.bas.repr.path;

import net.bodz.bas.t.variant.IVariantMap;

public abstract class AbstractPathDispatcher
        implements IPathDispatcher {

    @Override
    public int getPriority() {
        return 0;
    }

    /**
     * @see #dispatch(IPathArrival, ITokenQueue, IVariantMap)
     */
    @Override
    public IPathArrival dispatch(Object startTarget, String path, IVariantMap<String> q)
            throws PathDispatchException {
        TokenQueue tokens = new TokenQueue(path);
        PathArrival start = new PathArrival(startTarget, tokens.getRemainingPath());
        IPathArrival result = dispatch(start, tokens, q);
        if (!tokens.isEmpty())
            throw new IncompleteDispatchException(tokens.toString());
        return result;
    }

    /**
     * @see #dispatch(IPathArrival, ITokenQueue)
     */
    public IPathArrival dispatch(Object startObject, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        IPathArrival start = new PathArrival(startObject, tokens.getRemainingPath());
        IPathArrival result = dispatch(start, tokens, q);
        return result;
    }

    public Object dispatchTest(Object startObject, String path, IVariantMap<String> q)
            throws PathDispatchException {
        TokenQueue tokens = new TokenQueue(path);
        IPathArrival start = new PathArrival(startObject, tokens.getRemainingPath());
        IPathArrival result = dispatch(start, tokens, q);
        if (result == null || !tokens.isEmpty())
            throw new IncompleteDispatchException(tokens.toString());
        return result.getTarget();
    }

    public Object dispatchTest(Object startObject, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        IPathArrival start = new PathArrival(startObject, tokens.getRemainingPath());
        IPathArrival result = dispatch(start, tokens, q);
        if (result == null || !tokens.isEmpty())
            throw new IncompleteDispatchException(tokens.toString());
        return result.getTarget();
    }

}
