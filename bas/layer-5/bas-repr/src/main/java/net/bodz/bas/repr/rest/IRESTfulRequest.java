package net.bodz.bas.repr.rest;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;
import net.bodz.bas.vfs.util.MIMEType;

public interface IRESTfulRequest
        extends HttpServletRequest {

    /**
     * The resource path.
     * 
     * @return Non-<code>null</code> resource path (without any context-uri).
     */
    String getDispatchPath();

    /**
     * The verb on resource.
     * 
     * @return Non-<code>null</code> verb literal on the resource.
     */
    String getMethod();

    /**
     * The MIME type for the desired output.
     * 
     * @return Non-<code>null</code> MIME literal.
     */
    MIMEType getTargetContentType();

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
