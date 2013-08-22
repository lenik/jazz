package net.bodz.bas.mf;

import java.util.*;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.mf.spi.IMdaFeaturesProvider;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.t.order.PriorityComparator;

public class MdaFeatures {

    private static ServiceLoader<IMdaFeaturesProvider> mdaFeaturesProviderLoader;

    static TreeSet<IMdaFeaturesProvider> mdaFeaturesProviders;

    static synchronized void reload() {
        if (mdaFeaturesProviderLoader == null)
            mdaFeaturesProviderLoader = ServiceLoader.load(IMdaFeaturesProvider.class);
        else
            mdaFeaturesProviderLoader.reload();

        mdaFeaturesProviders = new TreeSet<IMdaFeaturesProvider>(PriorityComparator.INSTANCE);

        Iterator<IMdaFeaturesProvider> mdaFeaturesProviderIterator = mdaFeaturesProviderLoader.iterator();
        while (mdaFeaturesProviderIterator.hasNext()) {
            IMdaFeaturesProvider mdaFeaturesProvider = mdaFeaturesProviderIterator.next();
            mdaFeaturesProviders.add(mdaFeaturesProvider);
        }
    }

    static {
        reload();
    }

    public static SortedSet<IMdaFeaturesProvider> getMdaFeaturesProviders() {
        return Collections.unmodifiableSortedSet(mdaFeaturesProviders);
    }

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
     * @see IMdaFeaturesProvider#getMdaFeature(Class, Class)
     */
    public static <T> T getMdaFeature(Class<?> objType, Class<T> mdaFeatureType)
            throws QueryException {
        if (objType == null)
            throw new NullPointerException("objType");
        if (mdaFeatureType == null)
            throw new NullPointerException("mdaFeatureType");

        objType = Primitives.box(objType);

        List<IMdaFeaturesProvider> nonDefinedMdaFeaturesProviders = null;
        int index = 0;
        for (IMdaFeaturesProvider provider : mdaFeaturesProviders) {
            T mdaFeature = provider.getMdaFeature(objType, mdaFeatureType);
            if (mdaFeature != null)
                return mdaFeature;
            if (!provider.isAggressive()) {
                if (nonDefinedMdaFeaturesProviders == null)
                    nonDefinedMdaFeaturesProviders = new ArrayList<IMdaFeaturesProvider>(mdaFeaturesProviders.size() - index);
                nonDefinedMdaFeaturesProviders.add(provider);
            }
            index++;
        }

        // Continue to query on non-defined providers for superclasses.
        if (nonDefinedMdaFeaturesProviders != null) {
            // assert !nonDefinedMdaFeaturesProviders.isEmpty();
            while ((objType = objType.getSuperclass()) != null)
                for (IMdaFeaturesProvider provider : nonDefinedMdaFeaturesProviders) {
                    T mdaFeature = provider.getMdaFeature(objType, mdaFeatureType);
                    if (mdaFeature != null)
                        return mdaFeature;
                }
        }

        return null;
    }

    /**
     * Query for specific mdaFeature on the object instance.
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
     * @return MdaFeatures implementation, <code>null</code> if the mdaFeatures isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type mdaFeatures.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>mdaFeaturesType</code> is <code>null</code>.
     * @see IMdaFeaturesProvider#getMdaFeature(Class, Object, Class)
     */
    public static <T> T getMdaFeature(Class<?> objType, Object obj, Class<T> mdaFeatureType)
            throws QueryException {
        if (objType == null)
            throw new NullPointerException("objType");
        if (mdaFeatureType == null)
            throw new NullPointerException("mdaFeatureType");

        objType = Primitives.box(objType);

        for (IMdaFeaturesProvider mdaFeatureProvider : mdaFeaturesProviders) {
            T mdaFeature = mdaFeatureProvider.getMdaFeature(objType, obj, mdaFeatureType);
            if (mdaFeature != null)
                return mdaFeature;
        }
        return null;
    }

    /**
     * Shortcut for {@link #getMdaFeature(Class, Object, Class)} with the <code>objType</code> set to
     * <code>obj.getClass()</code>.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param mdaFeatureType
     *            MdaFeatures facet of interesting, non-<code>null</code>.
     * @return MdaFeatures implementation, <code>null</code> if the mdaFeatures isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type mdaFeatures.
     * @throws NullPointerException
     *             If <code>obj</code> or <code>mdaFeatureType</code> is null.
     */
    public static <T> T getMdaFeature(Object obj, Class<T> mdaFeatureType)
            throws QueryException {
        return getMdaFeature(obj.getClass(), obj, mdaFeatureType);
    }

}
