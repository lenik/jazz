package net.bodz.bas.repr.path;

import java.util.Date;

import net.bodz.bas.meta.decl.Shortcut;

public interface IPathArrival {

    String ATTRIBUTE_KEY = IPathArrival.class.getCanonicalName();

    /**
     * Get the previous arrival node.
     * 
     * @return Previous arrival node, <code>null</code> if none.
     */
    IPathArrival getPrevious();

    IPathArrival getPrevious(int n);

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
     * Get the remaining path.
     * 
     * @return <code>null</code> if this is the final arrival.
     */
    String getRemainingPath();

    /**
     * Get the target object.
     * 
     * @return The dispatched object, only the final target can be <code>null</code>.
     */
    Object getTarget();

    /**
     * Get the last non-<code>null</code> target in the arrival chain.
     * 
     * @return <code>null</code> if none.
     */
    @Shortcut
    Object getLastNonNullTarget();

    /**
     * The expire date of the target.
     * 
     * @return Expires date, <code>null</code> for no-cache.
     * @see PathArrival#expires(Date)
     */
    Date getExpires();

    /**
     * The expire date of all targets in the arrival chain.
     * 
     * @return Expires date, <code>null</code> for no-cache.
     */
    Date getMinExpires();

}