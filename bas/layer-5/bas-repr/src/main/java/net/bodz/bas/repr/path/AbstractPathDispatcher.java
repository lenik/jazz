package net.bodz.bas.repr.path;

public abstract class AbstractPathDispatcher
        implements IPathDispatcher {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public IPathArrival dispatch(Object startTarget, String path)
            throws PathDispatchException {
        TokenQueue tokens = new TokenQueue(path);
        PathArrival start = new PathArrival(startTarget, tokens.getRemainingPath());
        IPathArrival result = dispatch(start, tokens);
        if (!tokens.isEmpty())
            throw new IncompleteDispatchException(tokens.toString());
        return result;
    }

    public IPathArrival dispatch(Object startObject, ITokenQueue tokens)
            throws PathDispatchException {
        IPathArrival start = new PathArrival(startObject, tokens.getRemainingPath());
        IPathArrival result = dispatch(start, tokens);
        return result;
    }

    public Object dispatchTest(Object startObject, String path)
            throws PathDispatchException {
        TokenQueue tokens = new TokenQueue(path);
        IPathArrival start = new PathArrival(startObject, tokens.getRemainingPath());
        IPathArrival result = dispatch(start, tokens);
        if (result == null || !tokens.isEmpty())
            throw new IncompleteDispatchException(tokens.toString());
        return result.getTarget();
    }

    public Object dispatchTest(Object startObject, ITokenQueue tokens)
            throws PathDispatchException {
        IPathArrival start = new PathArrival(startObject, tokens.getRemainingPath());
        IPathArrival result = dispatch(start, tokens);
        if (result == null || !tokens.isEmpty())
            throw new IncompleteDispatchException(tokens.toString());
        return result.getTarget();
    }

}
