package net.bodz.bas.repr.path;

import net.bodz.bas.meta.decl.Shortcut;

public interface IPathArrival {

    String ATTRIBUTE_KEY = IPathArrival.class.getName();

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

    Object getResolver();

    /**
     * Get the target object.
     *
     * @return The dispatched object, only the final target can be <code>null</code>.
     */
    Object getTarget();

    default IPathArrival target(Object newTarget) {
        return target(getResolver(), newTarget);
    }

    IPathArrival target(Object newResolver, Object newTarget);

    // TODO target annotations

    boolean isMultiple();

    /**
     * Get the last non-<code>null</code> target in the arrival chain.
     *
     * @return <code>null</code> if none.
     */
    @Shortcut
    Object getLastNonNullTarget();

    /**
     * @see PathArrivalList#leftToRight()
     * @see PathArrivalList#rightToLeft()
     * @see PathArrivalList#mergeTransients()
     */
    PathArrivalList toList();

    default PathArrival shift(Object resolver, Object target, ITokenQueue tokens) {
        return shift(1, resolver, target, tokens);
    }

    default PathArrival shift(int n, Object resolver, Object target, ITokenQueue tokens) {
        return PathArrival.shift(n, this, resolver, target, tokens);
    }

}