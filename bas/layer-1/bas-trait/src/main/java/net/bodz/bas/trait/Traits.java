package net.bodz.bas.trait;

import java.util.*;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.bas.trait.spi.ITraitsProvider;

public class Traits {

    private static ServiceLoader<ITraitsProvider> traitsProviderLoader;

    static TreeSet<ITraitsProvider> traitsProviders;

    static synchronized void reload() {
        if (traitsProviderLoader == null)
            traitsProviderLoader = ServiceLoader.load(ITraitsProvider.class);
        else
            traitsProviderLoader.reload();

        traitsProviders = new TreeSet<ITraitsProvider>(PriorityComparator.INSTANCE);

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
     * For specific traits on the instance object, {@link #getTrait(Class, Object, Class)} should be
     * called.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param traitType
     *            Traits facet of interesting, non-<code>null</code>.
     * @return Traits implementation, <code>null</code> if the traits isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type traits.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>traitsType</code> is <code>null</code>.
     * @see ITraitsProvider#getTrait(Class, Class)
     */
    public static <T> T getTrait(Class<?> objType, Class<T> traitType)
            throws QueryException {
        if (objType == null)
            throw new NullPointerException("objType");
        if (traitType == null)
            throw new NullPointerException("traitType");

        objType = Primitives.box(objType);

        List<ITraitsProvider> nonDefinedTraitsProviders = null;
        int index = 0;
        for (ITraitsProvider provider : traitsProviders) {
            T trait = provider.getTrait(objType, traitType);
            if (trait != null)
                return trait;
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
                    T trait = provider.getTrait(objType, traitType);
                    if (trait != null)
                        return trait;
                }
        }

        return null;
    }

    /**
     * Query for specific trait on the object instance.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param obj
     *            The object instance, may be <code>null</code>.
     *            <p>
     *            If <code>obj</code> is <code>null</code>, this method is just the same as
     *            {@link #getTrait(Class, Class)}.
     * @param traitType
     *            Traits facet of interesting, non-<code>null</code>.
     * @return Traits implementation, <code>null</code> if the traits isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type traits.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>traitsType</code> is <code>null</code>.
     * @see ITraitsProvider#getTrait(Class, Object, Class)
     */
    public static <T> T getTrait(Class<?> objType, Object obj, Class<T> traitType)
            throws QueryException {
        if (objType == null)
            throw new NullPointerException("objType");
        if (traitType == null)
            throw new NullPointerException("traitType");

        objType = Primitives.box(objType);

        for (ITraitsProvider traitProvider : traitsProviders) {
            T trait = traitProvider.getTrait(objType, obj, traitType);
            if (trait != null)
                return trait;
        }
        return null;
    }

    /**
     * Shortcut for {@link #getTrait(Class, Object, Class)} with the <code>objType</code> set to
     * <code>obj.getClass()</code>.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param traitType
     *            Traits facet of interesting, non-<code>null</code>.
     * @return Traits implementation, <code>null</code> if the traits isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type traits.
     * @throws NullPointerException
     *             If <code>obj</code> or <code>traitType</code> is null.
     */
    public static <T> T getTrait(Object obj, Class<T> traitType)
            throws QueryException {
        return getTrait(obj.getClass(), obj, traitType);
    }

}
