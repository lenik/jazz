package net.bodz.bas.collection.util;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionToString {

    public static String toString(Collection<?> collection) {
        if (collection == null)
            throw new NullPointerException("collection");
        return toString(collection, collection.size() * 10);
    }

    public static String toString(Collection<?> collection, int estimatedSize) {
        if (collection == null)
            throw new NullPointerException("collection");
        StringBuffer buf = new StringBuffer(estimatedSize);
        buf.append("{ ");
        for (Object e : collection) {
            buf.append(e);
            buf.append(", ");
        }
        buf.append(" }");
        return buf.toString();
    }

    public static String toString(Map<?, ?> map) {
        return toString(map, map.size() * 20);
    }

    public static String toString(Map<?, ?> map, int estimatedSize) {
        StringBuffer buf = new StringBuffer(estimatedSize);
        buf.append("{ ");
        for (Entry<?, ?> e : map.entrySet()) {
            buf.append(e.getKey() + "=" + e.getValue() + ", ");
        }
        buf.append(" }");
        return buf.toString();
    }

}
