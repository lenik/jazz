package net.bodz.bas.disp;


/**
 * URI-style path dispatcher.
 * <p>
 * Plover-dispatcher supersedes the Stapler dispatcher.
 * 
 * @see org.kohsuke.stapler.Dispatcher
 */
public interface IDispatcher
        extends IPathDispatchable {

    String getName();
    
    /**
     * The order of the dispatcher.
     * 
     * Dispatcher with higher order is took first.
     * 
     * @return An integer represents the order of the dispatch. A lower value means higher order.
     */
    int getOrder();

}
