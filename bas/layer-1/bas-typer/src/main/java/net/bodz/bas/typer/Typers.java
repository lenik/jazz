package net.bodz.bas.typer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import net.bodz.bas.c.primitive.Primitives;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.spi.ITyperProvider;

public class Typers {

    static Map<Class<?>, Map<Class<?>, Object>> cache = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T getGenericTyper(Class<?> objType, Class<?> typerClass)
            throws QueryException {
        return getTyper(objType, (Class<T>) typerClass);
    }

    /**
     * Query for specific typers about the user object type.
     * <p>
     * For specific typers on the instance object, {@link #getTyper(Class, Object, Class)} should be
     * called.
     *
     * @param objType    The user object type to be queryed, non-<code>null</code>.
     * @param typerClass Typers facet of interesting, non-<code>null</code>.
     * @return Typers implementation, <code>null</code> if the typers isn't defined.
     * @throws QueryException       If exception occurred when query for specific type typers.
     * @throws NullPointerException If <code>objType</code> or <code>typerClass</code> is <code>null</code>.
     * @see ITyperProvider#getTyper(Class, Class)
     */
    public static <T> T getTyper(Class<?> objType, Class<T> typerClass)
            throws QueryException {
        if (objType == null)
            throw new NullPointerException("objType");
        if (typerClass == null)
            throw new NullPointerException("typerClass");

        objType = Primitives.box(objType);

        Map<Class<?>, Object> cachedTypers = cache.get(objType);
        if (cachedTypers != null) {
            @SuppressWarnings("unchecked")
            T typer = (T) cachedTypers.get(typerClass);
            if (typer != null)
                return typer;
        }

        T typer = findTyper(objType, (Class<T>) typerClass);

        if (typer != null) {
            if (cachedTypers == null) {
                cachedTypers = new HashMap<>();
                cache.put(objType, cachedTypers);
            }
            cachedTypers.put(typerClass, typer);
        }
        return typer;
    }

    @SuppressWarnings("unchecked")
    public static <T> T findGenericTyper(Class<?> objType, Class<?> typerClass)
            throws QueryException {
        return findTyper(objType, (Class<T>) typerClass);
    }

    public static <T> T findTyper(Class<?> objType, Class<T> typerClass)
            throws QueryException {
        TreeSet<ITyperProvider> providers = TyperProviders.sorted();
        for (ITyperProvider provider : providers) {
            T typer = provider.getTyper(objType, typerClass);
            if (typer != null)
                return typer;
        }

        // Continue to query on aggresive providers for superclasses.
        List<ITyperProvider> aggresiveList = TyperProviders.aggresives();
        if (!aggresiveList.isEmpty())
            while (true) {
                objType = objType.getSuperclass();
                if (objType == null || objType == Object.class)
                    break;

                for (ITyperProvider provider : aggresiveList) {
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
     * @param objType    The user object type to be queryed, non-<code>null</code>.
     * @param obj        The object instance, may be <code>null</code>.
     *                   <p>
     *                   If <code>obj</code> is <code>null</code>, this method is just the same as
     *                   {@link #getTyper(Class, Class)}.
     * @param typerClass Typers facet of interesting, non-<code>null</code>.
     * @return Typers implementation, <code>null</code> if the typers isn't defined.
     * @throws QueryException       If exception occurred when query for specific type typers.
     * @throws NullPointerException If <code>objType</code> or <code>typerClass</code> is <code>null</code>.
     * @see ITyperProvider#getTyper(Class, Object, Class)
     */
    public static <T> T getTyper(Class<?> objType, Object obj, Class<T> typerClass)
            throws QueryException {
        if (objType == null)
            throw new NullPointerException("objType");
        if (typerClass == null)
            throw new NullPointerException("typerClass");

        objType = Primitives.box(objType);

        for (ITyperProvider provider : TyperProviders.sorted()) {
            T typer = provider.getTyper(objType, obj, typerClass);
            if (typer != null)
                return typer;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getGenericObjectTyper(Object obj, Class<?> typerClass)
            throws QueryException {
        return getObjectTyper(obj, (Class<T>) typerClass);
    }

    /**
     * Shortcut for {@link #getTyper(Class, Object, Class)} with the <code>objType</code> set to
     * <code>obj.getClass()</code>.
     *
     * @param objType    The user object type to be queryed, non-<code>null</code>.
     * @param typerClass Typers facet of interesting, non-<code>null</code>.
     * @return Typers implementation, <code>null</code> if the typers isn't defined.
     * @throws QueryException       If exception occurred when query for specific type typers.
     * @throws NullPointerException If <code>obj</code> or <code>typerClass</code> is null.
     */
    public static <T> T getObjectTyper(Object obj, Class<T> typerClass)
            throws QueryException {
        return (T) getTyper(obj.getClass(), obj, typerClass);
    }

}
