package net.bodz.bas.repr.path;

import net.bodz.bas.t.variant.IVariantMap;

public interface IPathDispatchable {

    /**
     * Resolve the tokens with-in the context object.
     *
     * @param previous
     *            Non-null previous arrival info. For the initial dispatching, the previous arrival
     *            info contains the start object to be dispatched from.
     * @param tokens
     *            Tokens to be consumed by the dispatcher. It's the caller's responsibility to check
     *            whether all tokens are processed.
     * @return The arrival info, <code>null</code> if the next path token is unknown to the
     *         dispatcher.
     * @throws NullPointerException
     *             If either <code>previous</code> or <code>pathTokens</code> is <code>null</code>.
     */
    IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException;

}
