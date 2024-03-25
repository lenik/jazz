package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import net.bodz.bas.err.FormatException;

public interface IJsonDumper {

    void dump(Object obj)
            throws IOException, FormatException;

    default boolean dumpArray(Object array)
            throws IOException, FormatException {
        return dumpArray(array, false);
    }

    boolean dumpArray(Object array, boolean spread)
            throws IOException, FormatException;

    default boolean dumpCollection(Collection<?> collection)
            throws IOException, FormatException {
        return dumpCollection(collection, false);
    }

    boolean dumpCollection(Collection<?> collection, boolean spread)
            throws IOException, FormatException;

    default boolean dumpMap(Map<?, ?> map)
            throws IOException, FormatException {
        return dumpMap(map, false);
    }

    boolean dumpMap(Map<?, ?> map, boolean spread)
            throws IOException, FormatException;

    default boolean dumpObject(Class<?> type, Object obj)
            throws IOException, FormatException {
        return dumpObject(type, obj, false);
    }

    boolean dumpObject(Class<?> type, Object obj, boolean spread)
            throws IOException, FormatException;
}
