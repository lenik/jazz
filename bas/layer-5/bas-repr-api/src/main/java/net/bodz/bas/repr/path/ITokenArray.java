package net.bodz.bas.repr.path;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

public interface ITokenArray {

    /**
     * Whether there is a trailing slash.
     *
     * @return <code>true</code> if there is a trailing slash.
     * @see net.bodz.bas.vfs.path.IPath#isEntered()
     */
    boolean isEntered();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    default boolean isNotEmpty() {
        return size() > 0;
    }

    String get(int index);

    default String getFirst() {
        return get(0);
    }

    default String getLast() {
        return get(size() - 1);
    }

    byte getByte(int index)
            throws ParseException;

    byte getByte(int index, byte fallback);

    short getShort(int index)
            throws ParseException;

    short getShort(int index, short fallback);

    int getInt(int index)
            throws ParseException;

    int getInt(int index, int fallback);

    long getLong(int index)
            throws ParseException;

    long getLong(int index, long fallback);

    float getFloat(int index)
            throws ParseException;

    float getFloat(int index, float fallback);

    double getDouble(int index)
            throws ParseException;

    double getDouble(int index, double fallback);

    boolean getBoolean(int index)
            throws ParseException;

    boolean getBoolean(int index, boolean fallback);

    <E extends Enum<E>> E get(Class<E> enumType, int index)
            throws ParseException;

    <E extends Enum<E>> E get(Class<E> enumType, int index, E fallback);

    @NotNull
    default String[] toArray() {
        int n = size();
        String[] array = new String[n];
        for (int i = 0; i < n; i++)
            array[i] = get(i);
        return array;
    }

}
