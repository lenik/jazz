package net.bodz.bas.traits.provider;

import net.bodz.bas.lang.QueryException;

/**
 * Provide to resolve specific traits for a given class or object.
 */
public interface ITraitsProvider {

    /**
     * High priority.
     * <p>
     * This traits provider should be queryed first.
     * <p>
     * A high priority traits provider should be highly optimized, to resolve the type traits fast.
     */
    int PRIORITY_HIGH = -100;

    /**
     * Normal priority.
     */
    int PRIORITY_NORMAL = 0;

    /**
     * Low priority.
     */
    int PRIORITY_LOW = 100;

    /**
     * Determine the priority of this trait provider compared to the given one.
     * 
     * The priority is represented by an integer, a smaller value means higher priority.
     * 
     * @return An integer value represent the priority.
     * @see #PRIORITY_HIGH
     * @see #PRIORITY_NORMAL
     * @see #PRIORITY_LOW
     */
    int getPriority();

    /**
     * A defined traits provider will include inherited traits in the results.
     * <p>
     * For a specific traits query on the user type, the defined traits provider will be called only
     * once.
     * <p>
     * Correspondingly, for non-defined traits provider, if the concerned traits wasn't included in
     * the query results, then this provider will be later called again with the super class (or
     * interface) of the user type.
     * 
     * @return <code>true</code> If inherited traits are included in the results provided by this
     *         traits provider.
     */
    boolean isDefined();

    /**
     * Query for specific traits about the user object type.
     * <p>
     * For specific traits on the instance object, {@link #getTraits(Class, Object, Class)} should
     * be called.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param traitsType
     *            Traits facet of interesting, non-<code>null</code>.
     * @return Traits implementation, <code>null</code> if the traits isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type traits.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>traitsType</code> is <code>null</code>.
     */
    <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException;

    /**
     * Query for specific traits on the object instance.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param obj
     *            The object instance, may be <code>null</code>.
     *            <p>
     *            If <code>obj</code> is <code>null</code>, this method is just the same as
     *            {@link #getTraits(Class, Class)}.
     * @param traitsType
     *            Traits facet of interesting, non-<code>null</code>.
     * @return Traits implementation, <code>null</code> if the traits isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type traits.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>traitsType</code> is <code>null</code>.
     */
    <T> T getTraits(Class<?> objType, Object obj, Class<T> traitsType)
            throws QueryException;

}
