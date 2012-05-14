package net.bodz.bas.disp.req;

import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;

public interface IRequestDispatch {

    String ATTRIBUTE_KEY = IRequestDispatch.class.getCanonicalName();

    /**
     * The original dispatch path, or resource path.
     * 
     * @return Non-<code>null</code> resource path (without any context-uri).
     */
    String getDispatchPath();

    // Stateful extension

    /**
     * The token queue being processed.
     * 
     * When the dispatch started, the token queue contains tokens to be dispatched, and after
     * dispatch is completed, all processed tokens are consumed, rest only the unprocessed tokens.
     * 
     * @return The token queue object.
     */
    ITokenQueue getTokenQueue();

    /**
     * Get the arrival information from dispatch process.
     */
    IPathArrival getArrival();

    /**
     * The same as arrival.target.
     * 
     * @see IPathArrival#getTarget()
     */
    <T> T getTarget();

    /**
     * The same as arrival.remainingPath.
     * 
     * @see IPathArrival#getRemainingPath()
     */
    String getRemainingPath();

}
