package net.bodz.bas.traits;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.SortedSet;
import java.util.TreeSet;

import net.bodz.bas.lang.QueryException;

public class Traits {

    private static ServiceLoader<ITraitsProvider> traitsProviderLoader;

    static TreeSet<ITraitsProvider> traitsProviders;

    static synchronized void reload() {
        if (traitsProviderLoader == null)
            traitsProviderLoader = ServiceLoader.load(ITraitsProvider.class);
        else
            traitsProviderLoader.reload();

        traitsProviders = new TreeSet<ITraitsProvider>(TraitsProviderComparator.getInstance());

        Iterator<ITraitsProvider> traitsProviderIterator = traitsProviderLoader.iterator();
        while (traitsProviderIterator.hasNext()) {
            ITraitsProvider traitsProvider = traitsProviderIterator.next();
            traitsProviders.add(traitsProvider);
        }
    }

    static {
        reload();
    }

    public static SortedSet<ITraitsProvider> getTraitsProviders() {
        return Collections.unmodifiableSortedSet(traitsProviders);
    }

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
     * @see ITraitsProvider#getTraits(Class, Class)
     */
    public static <T> T getTraits(Class<?> objType, Class<T> traitsType)
            throws QueryException {
        List<ITraitsProvider> nonDefinedTraitsProviders = null;
        int index = 0;
        for (ITraitsProvider provider : traitsProviders) {
            T traits = provider.getTraits(objType, traitsType);
            if (traits != null)
                return traits;
            if (!provider.isAggressive()) {
                if (nonDefinedTraitsProviders == null)
                    nonDefinedTraitsProviders = new ArrayList<ITraitsProvider>(traitsProviders.size() - index);
                nonDefinedTraitsProviders.add(provider);
            }
            index++;
        }

        // Continue to query on non-defined providers for superclasses.
        if (nonDefinedTraitsProviders != null) {
            // assert !nonDefinedTraitsProviders.isEmpty();
            while ((objType = objType.getSuperclass()) != null)
                for (ITraitsProvider provider : nonDefinedTraitsProviders) {
                    T traits = provider.getTraits(objType, traitsType);
                    if (traits != null)
                        return traits;
                }
        }

        return null;
    }

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
     * @see ITraitsProvider#getTraits(Class, Object, Class)
     */
    public static <T> T getTraits(Class<?> objType, Object obj, Class<T> traitsType)
            throws QueryException {
        for (ITraitsProvider traitsProvider : traitsProviders) {
            T traits = traitsProvider.getTraits(objType, obj, traitsType);
            if (traits != null)
                return traits;
        }
        return null;
    }

    /**
     * Shortcut for {@link #getTraits(Class, Object, Class)} with the <code>objType</code> set to
     * <code>obj.getClass()</code>.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param traitsType
     *            Traits facet of interesting, non-<code>null</code>.
     * @return Traits implementation, <code>null</code> if the traits isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type traits.
     * @throws NullPointerException
     *             If <code>obj</code> or <code>traitsType</code> is null.
     */
    public static <T> T getTraits(Object obj, Class<T> traitsType)
            throws QueryException {
        return getTraits(obj.getClass(), obj, traitsType);
    }

}
