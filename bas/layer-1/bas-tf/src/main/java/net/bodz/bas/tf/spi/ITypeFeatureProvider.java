package net.bodz.bas.tf.spi;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.rtx.IQueryProxy;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.t.order.IPriority;

/**
 * Provide to resolve specific typeFeatures for a given class or object.
 */
@IndexedType
public interface ITypeFeatureProvider
        extends IPriority, IQueryProxy {

    /**
     * High priority.
     * <p>
     * This typeFeature provider should be queryed first.
     * <p>
     * A high priority typeFeatures provider should be highly optimized, to resolve the type typeFeatures fast.
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
     * Determine the priority of this typeFeature provider compared to the given one.
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
     * An aggressive typeFeatures provider will include all inherited typeFeatures in the results.
     * <p>
     * For a specific typeFeatures query on the user type, the aggressive typeFeatures provider will be called
     * only once.
     * <p>
     * Correspondingly, for non-aggressive typeFeatures provider, if the concerned typeFeatures wasn't included
     * in the query results, then this provider will be later called again with the super class (or
     * interface) of the user type.
     * 
     * @return <code>true</code> If inherited typeFeatures are included in the results provided by this
     *         typeFeatures provider.
     */
    boolean isAggressive();

    /**
     * Query for specific typeFeatures about the user object type.
     * <p>
     * For specific typeFeatures on the instance object, {@link #getTypeFeature(Class, Object, Class)} should be
     * called.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param typeFeatureClass
     *            TypeFeatures facet of interesting, non-<code>null</code>.
     * @return TypeFeatures implementation, <code>null</code> if the typeFeatures isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type typeFeatures.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>typeFeatureClass</code> is <code>null</code>.
     */
    <T> T getTypeFeature(Class<?> objType, Class<T> typeFeatureClass)
            throws QueryException;

    /**
     * Query for specific typeFeatures on the object instance.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param obj
     *            The object instance, may be <code>null</code>.
     *            <p>
     *            If <code>obj</code> is <code>null</code>, this method is just the same as
     *            {@link #getTypeFeature(Class, Class)}.
     * @param typeFeatureClass
     *            TypeFeatures facet of interesting, non-<code>null</code>.
     * @return TypeFeature implementation, <code>null</code> if the typeFeatures isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type typeFeatures.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>typeFeatureClass</code> is <code>null</code>.
     */
    <T> T getTypeFeature(Class<?> objType, Object obj, Class<T> typeFeatureClass)
            throws QueryException;

}
