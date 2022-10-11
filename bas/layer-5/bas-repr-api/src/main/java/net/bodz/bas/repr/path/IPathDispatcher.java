package net.bodz.bas.repr.path;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;
import net.bodz.bas.t.variant.IVariantMap;

/**
 * URI-style path dispatcher.
 * <p>
 * Plover-dispatcher supersedes the Stapler dispatcher.
 *
 * @see org.kohsuke.stapler.Dispatcher
 */
@IndexedType
public interface IPathDispatcher
        extends
            IPriority {

    /**
     * Resolve the tokens with-in the context object.
     *
     * @param startObject
     *            The start object.
     * @param path
     *            Path to be dispatched.
     * @return The arrival info.
     * @throws NullPointerException
     *             If either <code>obj</code> or <code>path</code> is <code>null</code>.
     */
    default IPathArrival dispatch(Object startObject, String path, IVariantMap<String> q)
            throws PathDispatchException {
        TokenQueue tokens = new TokenQueue(path);
        PathArrival start = new PathArrival(startObject, tokens.getRemainingPath());
        IPathArrival result = dispatch(start, startObject, tokens, q);
        if (!tokens.isEmpty())
            throw new IncompleteDispatchException(tokens.toString());
        return result;
    }

    default IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        return dispatch(previous, previous.getTarget(), tokens, q);
    }

    IPathArrival dispatch(IPathArrival previous, Object source, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException;

}
