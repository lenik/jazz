package net.bodz.bas.c.object;

public class ObjectInfo {

    static final String NULL_ID = "(null)";

    public static String getSystemId(Object object) {
        if (object == null)
            return NULL_ID;
        String typeName = object.getClass().getName();
        int hash = System.identityHashCode(object);
        String id = String.format("%s@0x%x", typeName, hash);
        return id;
    }

    public static String getSimpleId(Object object) {
        if (object == null)
            return NULL_ID;
        IdPool pool = IdPool.getInstance(object.getClass());
        return pool.getSimpleId(object);
    }

    public static String getCanonicalId(Object object) {
        if (object == null)
            return NULL_ID;
        IdPool pool = IdPool.getInstance(object.getClass());
        return pool.getCanonicalId(object);
    }

}
