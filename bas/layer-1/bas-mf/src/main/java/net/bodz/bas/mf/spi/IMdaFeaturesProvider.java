package net.bodz.bas.mf.spi;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.rtx.IQueryProxy;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.t.order.IPriority;

/**
 * Provide to resolve specific mdaFeatures for a given class or object.
 */
@IndexedType
public interface IMdaFeaturesProvider
        extends IPriority, IQueryProxy {

    /**
     * High priority.
     * <p>
     * This mdaFeature provider should be queryed first.
     * <p>
     * A high priority mdaFeatures provider should be highly optimized, to resolve the type mdaFeatures fast.
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
     * Determine the priority of this mdaFeature provider compared to the given one.
     * 
     * The priority is represented by an integer, a smaller value means higher priority.
     * 
     * @return An integer value represent the priority.
     * @see #PRIORITY_HIGH
     * @see #PRIORITY_NORMAL
     * @see #PRIORITY_LOW
     */
    @Override
    int getPriority();

    /**
     * An aggressive mdaFeatures provider will include all inherited mdaFeatures in the results.
     * <p>
     * For a specific mdaFeatures query on the user type, the aggressive mdaFeatures provider will be called
     * only once.
     * <p>
     * Correspondingly, for non-aggressive mdaFeatures provider, if the concerned mdaFeatures wasn't included
     * in the query results, then this provider will be later called again with the super class (or
     * interface) of the user type.
     * 
     * @return <code>true</code> If inherited mdaFeatures are included in the results provided by this
     *         mdaFeatures provider.
     */
    boolean isAggressive();

    /**
     * Query for specific mdaFeatures about the user object type.
     * <p>
     * For specific mdaFeatures on the instance object, {@link #getMdaFeature(Class, Object, Class)} should be
     * called.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param mdaFeatureType
     *            MdaFeatures facet of interesting, non-<code>null</code>.
     * @return MdaFeatures implementation, <code>null</code> if the mdaFeatures isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type mdaFeatures.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>mdaFeaturesType</code> is <code>null</code>.
     */
    <T> T getMdaFeature(Class<?> objType, Class<T> mdaFeatureType)
            throws QueryException;

    /**
     * Query for specific mdaFeatures on the object instance.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param obj
     *            The object instance, may be <code>null</code>.
     *            <p>
     *            If <code>obj</code> is <code>null</code>, this method is just the same as
     *            {@link #getMdaFeature(Class, Class)}.
     * @param mdaFeatureType
     *            MdaFeatures facet of interesting, non-<code>null</code>.
     * @return MdaFeature implementation, <code>null</code> if the mdaFeatures isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type mdaFeatures.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>mdaFeaturesType</code> is <code>null</code>.
     */
    <T> T getMdaFeature(Class<?> objType, Object obj, Class<T> mdaFeatureType)
            throws QueryException;

}
