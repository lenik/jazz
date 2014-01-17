package net.bodz.bas.repr.path;

import java.util.Date;

import net.bodz.bas.meta.decl.Shortcut;

public interface IPathArrival {

    /**
     * Get the previous arrival node.
     *
     * @return Previous arrival node, <code>null</code> if none.
     */
    IPathArrival getPrevious();

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

    void setRemainingPath(String remainingPath);

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
    @Shortcut
    Object getLastNonNullTarget();

    /**
     * The latest expires date gathered in the context chain.
     *
     * @return Expires date, <code>null</code> for no-cache.
     */
    Date getExpires();

}