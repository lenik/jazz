package net.bodz.bas.repr.path;

import java.util.List;

import net.bodz.bas.meta.decl.Shortcut;

public interface IPathArrival {

    /**
     * Get the previous arrival node.
     * 
     * @return Previous arrival node, <code>null</code> if none.
     */
    IPathArrival getPrevious();

    IPathArrival getPrevious(int n);

    IPathArrival getPrevious(Object target);

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

    String getConsumedFullPath();

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

    List<IPathArrival> toList(boolean mergeTransients);

}