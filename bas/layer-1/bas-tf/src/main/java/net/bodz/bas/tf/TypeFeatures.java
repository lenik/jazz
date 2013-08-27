package net.bodz.bas.tf;

import java.util.*;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.tf.spi.ITypeFeatureProvider;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.t.order.PriorityComparator;

public class TypeFeatures {

    private static ServiceLoader<ITypeFeatureProvider> typeFeatureProviderLoader;

    static TreeSet<ITypeFeatureProvider> typeFeatureProviders;

    static synchronized void reload() {
        if (typeFeatureProviderLoader == null)
            typeFeatureProviderLoader = ServiceLoader.load(ITypeFeatureProvider.class);
        else
            typeFeatureProviderLoader.reload();

        typeFeatureProviders = new TreeSet<ITypeFeatureProvider>(PriorityComparator.INSTANCE);

        Iterator<ITypeFeatureProvider> typeFeatureProviderIterator = typeFeatureProviderLoader.iterator();
        while (typeFeatureProviderIterator.hasNext()) {
            ITypeFeatureProvider typeFeatureProvider = typeFeatureProviderIterator.next();
            typeFeatureProviders.add(typeFeatureProvider);
        }
    }

    static {
        reload();
    }

    public static SortedSet<ITypeFeatureProvider> getTypeFeatureProviders() {
        return Collections.unmodifiableSortedSet(typeFeatureProviders);
    }

    /**
     * Query for specific typeFeatures about the user object type.
     * <p>
     * For specific typeFeatures on the instance object, {@link #getTypeFeature(Class, Object, Class)}
     * should be called.
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
     * @see ITypeFeatureProvider#getTypeFeature(Class, Class)
     */
    public static <T> T getTypeFeature(Class<?> objType, Class<T> typeFeatureClass)
            throws QueryException {
        if (objType == null)
            throw new NullPointerException("objType");
        if (typeFeatureClass == null)
            throw new NullPointerException("typeFeatureClass");

        objType = Primitives.box(objType);

        List<ITypeFeatureProvider> aggresiveProviders = null;
        int index = 0;
        for (ITypeFeatureProvider provider : typeFeatureProviders) {
            T typeFeature = provider.getTypeFeature(objType, typeFeatureClass);
            if (typeFeature != null)
                return typeFeature;
            if (!provider.isAggressive()) {
                if (aggresiveProviders == null)
                    aggresiveProviders = new ArrayList<ITypeFeatureProvider>(typeFeatureProviders.size() - index);
                aggresiveProviders.add(provider);
            }
            index++;
        }

        // Continue to query on aggresive providers for superclasses.
        if (aggresiveProviders != null) {
            // assert !nonDefinedTypeFeatureProviders.isEmpty();
            while ((objType = objType.getSuperclass()) != null)
                for (ITypeFeatureProvider provider : aggresiveProviders) {
                    T typeFeature = provider.getTypeFeature(objType, typeFeatureClass);
                    if (typeFeature != null)
                        return typeFeature;
                }
        }

        return null;
    }

    /**
     * Query for specific typeFeature on the object instance.
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
     * @return TypeFeatures implementation, <code>null</code> if the typeFeatures isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type typeFeatures.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>typeFeatureClass</code> is <code>null</code>.
     * @see ITypeFeatureProvider#getTypeFeature(Class, Object, Class)
     */
    public static <T> T getTypeFeature(Class<?> objType, Object obj, Class<T> typeFeatureClass)
            throws QueryException {
        if (objType == null)
            throw new NullPointerException("objType");
        if (typeFeatureClass == null)
            throw new NullPointerException("typeFeatureClass");

        objType = Primitives.box(objType);

        for (ITypeFeatureProvider provider : typeFeatureProviders) {
            T typeFeature = provider.getTypeFeature(objType, obj, typeFeatureClass);
            if (typeFeature != null)
                return typeFeature;
        }
        return null;
    }

    /**
     * Shortcut for {@link #getTypeFeature(Class, Object, Class)} with the <code>objType</code> set
     * to <code>obj.getClass()</code>.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param typeFeatureClass
     *            TypeFeatures facet of interesting, non-<code>null</code>.
     * @return TypeFeatures implementation, <code>null</code> if the typeFeatures isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type typeFeatures.
     * @throws NullPointerException
     *             If <code>obj</code> or <code>typeFeatureClass</code> is null.
     */
    public static <T> T getTypeFeature(Object obj, Class<T> typeFeatureClass)
            throws QueryException {
        return getTypeFeature(obj.getClass(), obj, typeFeatureClass);
    }

}
