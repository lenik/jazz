package net.bodz.bas.typer;

import java.util.*;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.t.order.PriorityComparator;
import net.bodz.bas.typer.spi.ITyperProvider;

public class Typers {

    private static ServiceLoader<ITyperProvider> typerProviderLoader;

    static TreeSet<ITyperProvider> typerProviders;

    static synchronized void reload() {
        if (typerProviderLoader == null)
            typerProviderLoader = ServiceLoader.load(ITyperProvider.class);
        else
            typerProviderLoader.reload();

        typerProviders = new TreeSet<ITyperProvider>(PriorityComparator.INSTANCE);

        Iterator<ITyperProvider> typerProviderIterator = typerProviderLoader.iterator();
        while (typerProviderIterator.hasNext()) {
            ITyperProvider typerProvider = typerProviderIterator.next();
            typerProviders.add(typerProvider);
        }
    }

    static {
        reload();
    }

    public static SortedSet<ITyperProvider> getTyperProviders() {
        return Collections.unmodifiableSortedSet(typerProviders);
    }

    /**
     * Query for specific typers about the user object type.
     * <p>
     * For specific typers on the instance object, {@link #getTyper(Class, Object, Class)}
     * should be called.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param typerClass
     *            Typers facet of interesting, non-<code>null</code>.
     * @return Typers implementation, <code>null</code> if the typers isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type typers.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>typerClass</code> is <code>null</code>.
     * @see ITyperProvider#getTyper(Class, Class)
     */
    public static <T> T getTyper(Class<?> objType, Class<T> typerClass)
            throws QueryException {
        if (objType == null)
            throw new NullPointerException("objType");
        if (typerClass == null)
            throw new NullPointerException("typerClass");

        objType = Primitives.box(objType);

        List<ITyperProvider> aggresiveProviders = null;
        int index = 0;
        for (ITyperProvider provider : typerProviders) {
            T typer = provider.getTyper(objType, typerClass);
            if (typer != null)
                return typer;
            if (!provider.isAggressive()) {
                if (aggresiveProviders == null)
                    aggresiveProviders = new ArrayList<ITyperProvider>(typerProviders.size() - index);
                aggresiveProviders.add(provider);
            }
            index++;
        }

        // Continue to query on aggresive providers for superclasses.
        if (aggresiveProviders != null) {
            // assert !nonDefinedTyperProviders.isEmpty();
            while ((objType = objType.getSuperclass()) != null)
                for (ITyperProvider provider : aggresiveProviders) {
                    T typer = provider.getTyper(objType, typerClass);
                    if (typer != null)
                        return typer;
                }
        }

        return null;
    }

    /**
     * Query for specific typer on the object instance.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param obj
     *            The object instance, may be <code>null</code>.
     *            <p>
     *            If <code>obj</code> is <code>null</code>, this method is just the same as
     *            {@link #getTyper(Class, Class)}.
     * @param typerClass
     *            Typers facet of interesting, non-<code>null</code>.
     * @return Typers implementation, <code>null</code> if the typers isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type typers.
     * @throws NullPointerException
     *             If <code>objType</code> or <code>typerClass</code> is <code>null</code>.
     * @see ITyperProvider#getTyper(Class, Object, Class)
     */
    public static <T> T getTyper(Class<?> objType, Object obj, Class<T> typerClass)
            throws QueryException {
        if (objType == null)
            throw new NullPointerException("objType");
        if (typerClass == null)
            throw new NullPointerException("typerClass");

        objType = Primitives.box(objType);

        for (ITyperProvider provider : typerProviders) {
            T typer = provider.getTyper(objType, obj, typerClass);
            if (typer != null)
                return typer;
        }
        return null;
    }

    /**
     * Shortcut for {@link #getTyper(Class, Object, Class)} with the <code>objType</code> set
     * to <code>obj.getClass()</code>.
     * 
     * @param objType
     *            The user object type to be queryed, non-<code>null</code>.
     * @param typerClass
     *            Typers facet of interesting, non-<code>null</code>.
     * @return Typers implementation, <code>null</code> if the typers isn't defined.
     * @throws QueryException
     *             If exception occurred when query for specific type typers.
     * @throws NullPointerException
     *             If <code>obj</code> or <code>typerClass</code> is null.
     */
    public static <T> T getTyper(Object obj, Class<T> typerClass)
            throws QueryException {
        return getTyper(obj.getClass(), obj, typerClass);
    }

}
