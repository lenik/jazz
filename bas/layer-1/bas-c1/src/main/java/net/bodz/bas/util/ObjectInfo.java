package net.bodz.bas.util;

public class ObjectInfo {

    public static String getObjectId(Object object) {
        if (object == null)
            return "null";
        String typeName = object.getClass().getName();
        int hash = System.identityHashCode(object);
        String id = String.format("%s@0x%x", typeName, hash);
        return id;
    }

}
