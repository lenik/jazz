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
     * @param obj
     *            The start object.
     * @param path
     *            Path to be dispatched.
     * @return The arrival info.
     * @throws NullPointerException
     *             If either <code>obj</code> or <code>path</code> is <code>null</code>.
     */
    IPathArrival dispatch(Object obj, String path)
            throws PathDispatchException;

}
