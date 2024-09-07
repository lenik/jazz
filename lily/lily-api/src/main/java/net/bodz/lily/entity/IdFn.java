package net.bodz.lily.entity;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.meta.decl.Nullable;

public class IdFn {

    public static boolean idEquals(@Nullable IId<?> a, @Nullable IId<?> b) {
        if (a == b)
            return true;
        if (a == null || b == null)
            return false;
        Object id1 = a.id();
        Object id2 = b.id();
        return Nullables.equals(id1, id2);
    }

    /**
     * Example: <code>fooRef = altId(new Foo(), fooId)</code>
     */
    public static <T extends IId<K>, K> T altId(T skel, K id) {
        skel.id(id);
        return skel;
    }

    /**
     * The first non-null id is used.
     *
     * Example: <code>fooRef = altId(new Foo(), id1, id2, idFallback)</code>
     *
     * @param ids
     *            If all ids is <code>null</code>, fallback is returned.
     */
    @SafeVarargs
    public static <T extends IId<K>, K> T altId(T skel, T fallback, K... ids) {
        for (int i = 0; i < ids.length; i++)
            if (ids[i] != null) {
                skel.id(ids[i]);
                return skel;
            }
        return fallback;
    }

    /**
     * Example: <code>fooRef = mkref(Foo.class, fooId)</code>
     */
    public static <T extends IId<K>, K> T mkref(Class<T> objectType, K id) {
        if (objectType == null)
            throw new NullPointerException("type");
        try {
            T instance = objectType.getConstructor().newInstance();
            instance.id(id);
            return instance;
        } catch (ReflectiveOperationException e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

    public static Class<?> getIdType(Class<? extends IId<?>> clazz) {
        IdType aIdType = clazz.getAnnotation(IdType.class);
        if (aIdType != null)
            return aIdType.value();

        Class<?> decl = TypeParam.infer1(clazz, IId.class, 0);
        return decl;
    }

    @SuppressWarnings("unchecked")
    public static <K> Class<K> _getIdType(Class<?> clazz) {
        IdType aIdType = clazz.getAnnotation(IdType.class);
        if (aIdType != null)
            return (Class<K>) aIdType.value();

        if (!IId.class.isAssignableFrom(clazz))
            return null;

        Class<?> decl = TypeParam.infer1(clazz, IId.class, 0);
        return (Class<K>) decl;
    }

    public static <K> K cast(Class<K> idType, Object id) {
        if (id == null)
            return null;
        Object casted = null;
        if (idType == String.class)
            casted = id.toString();
        else if (idType == Integer.class)
            casted = ((Number) id).intValue();
        else if (idType == Long.class)
            casted = ((Number) id).longValue();
        return idType.cast(casted);
    }

}
