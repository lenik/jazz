package net.bodz.bas.repr.path;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

/**
 * URI-style path dispatcher.
 * <p>
 * Plover-dispatcher supersedes the Stapler dispatcher.
 * 
 * @see org.kohsuke.stapler.Dispatcher
 */
@IndexedType
public interface IPathDispatcher
        extends IPathDispatchable, IPriority {

    /**
     * Resolve the tokens with-in the context object.
     * 
     * @param path
     *            Path must be fully dispatched, otherwise <code>null</code> is returned.
     * @return The arrival info, <code>null</code> if the next path token is unknown to the
     *         dispatcher.
     * @throws NullPointerException
     *             If either <code>previous</code> or <code>path</code> is <code>null</code>.
     */
    IPathArrival dispatch(Object obj, String path)
            throws PathDispatchException;

}
