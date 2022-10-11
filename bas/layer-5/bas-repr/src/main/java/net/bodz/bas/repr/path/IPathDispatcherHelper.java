package net.bodz.bas.repr.path;

import net.bodz.bas.t.variant.IVariantMap;

public interface IPathDispatcherHelper {

    default Object dispatchTest(IPathDispatcher pd, Object startObject, String path, IVariantMap<String> q)
            throws PathDispatchException {
        TokenQueue tokens = new TokenQueue(path);
        IPathArrival start = new PathArrival(startObject, tokens.getRemainingPath());
        IPathArrival result = pd.dispatch(start, startObject, tokens, q);
        if (result == null || !tokens.isEmpty())
            throw new IncompleteDispatchException(tokens.toString());
        return result.getTarget();
    }

    default Object dispatchTest(IPathDispatcher pd, Object startObject, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        IPathArrival start = new PathArrival(startObject, tokens.getRemainingPath());
        IPathArrival result = pd.dispatch(start, startObject, tokens, q);
        if (result == null || !tokens.isEmpty())
            throw new IncompleteDispatchException(tokens.toString());
        return result.getTarget();
    }

}
