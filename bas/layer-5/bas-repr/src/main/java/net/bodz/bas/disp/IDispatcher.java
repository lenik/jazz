package net.bodz.bas.disp;

import net.bodz.bas.util.order.IPriority;

/**
 * URI-style path dispatcher.
 * <p>
 * Plover-dispatcher supersedes the Stapler dispatcher.
 * 
 * @see org.kohsuke.stapler.Dispatcher
 */
public interface IDispatcher
        extends IPathDispatchable, IPriority {

}
