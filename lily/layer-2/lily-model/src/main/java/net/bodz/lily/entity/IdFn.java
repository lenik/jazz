package net.bodz.lily.entity;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.type.TypeParam;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.repr.form.meta.Nullable;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;

public class IdFn {

    public static boolean idEquals(@Nullable IId<?> a, @Nullable IId<?> b) {
        if (a == b)
            return true;
        if (a == null || b == null)
            return false;
        Object id1 = a.getId();
        Object id2 = b.getId();
        return Nullables.equals(id1, id2);
    }

    /**
     * Example: <code>fooRef = altId(new Foo(), fooId)</code>
     */
    public static <T extends IId<K>, K> T altId(T skel, K id) {
        skel.setId(id);
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
                skel.setId(ids[i]);
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
            T instance = objectType.newInstance();
            instance.setId(id);
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
        // if (!IId.class.isAssignableFrom(clazz))
        // return null;
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

    static IVarConverter<String> strConv = VarConverters.getConverter(String.class);

    public static <K> K parseId(Class<K> idType, String s)
            throws ParseException {
        try {
            return strConv.to(s, idType);
        } catch (TypeConvertException e) {
            throw new ParseException("failed to parse: " + e.getMessage(), e);
        }
    }

    public static <K> K convertId(Class<K> idType, Object anyVal)
            throws ParseException {
        IVarConverter<K> idConv = VarConverters.getConverter(idType);
        try {
            K id = idConv.from(anyVal);
            return id;
        } catch (TypeConvertException e) {
            throw new ParseException("failed to convert: " + e.getMessage(), e);
        }
    }

}
