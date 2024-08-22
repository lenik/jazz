package net.bodz.bas.repr.path;

import net.bodz.bas.err.ParseException;

public interface IStaticTokenArray {

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    String get(int index);

    default String getLast() {
        if (isEmpty())
            throw new TokenUnderflowException();
        return get(size() - 1);
    }

    int getInt(int index)
            throws ParseException;

    int getInt(int index, int fallback);

    long getLong(int index)
            throws ParseException;

    long getLong(int index, long fallback);

    <E extends Enum<E>> E get(Class<E> enumType, int index)
            throws ParseException;

    <E extends Enum<E>> E get(Class<E> enumType, int index, E fallback);

}
