package net.bodz.bas.disp;

import java.util.Date;

import net.bodz.bas.disp.util.ArrivalBacktraceCallback;

public interface IPathArrival {

    /**
     * Get the previous arrival node.
     * 
     * @return Parent dispatch context, <code>null</code> if none.
     */
    IPathArrival getParent();

    /**
     * The partial path tokens consumed by this context.
     * 
     * @return Non-empty string array contains of the consumed path tokens.
     */
    String[] getConsumedTokens();

    /**
     * The partial path consumed by this context.
     * 
     * @return Non-<code>null</code> consumed path.
     */
    String getConsumedPath();

    /**
     * Get the rest path.
     * 
     * @return <code>null</code> if this is the final arrival.
     */
    String getRestPath();

    void setRestPath(String parameterPath);

    /**
     * Back trace the dispatch arrivals.
     * 
     * @param callback
     *            Non-<code>null</code> callback on each arrival.
     * @return Whether any of arrival is handled by the callback, <code>false</code> if not handled
     *         at all.
     */
    <E extends Exception> boolean backtrace(ArrivalBacktraceCallback<E> callback)
            throws E;

    /**
     * Get the target object.
     * 
     * @return The dispatched object, only the final target can be <code>null</code>.
     */
    Object getTarget();

    /**
     * Get the last non-<code>null</code> target in the chain.
     * 
     * @return <code>null</code> if none.
     */
    Object getLastNonNullTarget();

    /**
     * The latest expires date gathered in the context chain.
     * 
     * @return Expires date, <code>null</code> for no-cache.
     */
    Date getExpires();

}