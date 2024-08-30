package net.bodz.mda.xjdoc.model;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IMergedFlatfForm;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.t.coll.IContainer;

public interface IDocTag<T>
        extends
//            Iterable<DocTagEntry>,
            IJavadocForm,
            IMergedFlatfForm {

    String getTagName();

//    List<DocTagEntry> getEntries();

//    void addEntry(String name, String extension, String text, IOptions options)
//            throws ParseException;

    Class<? extends T> getDataType();

    Class<?> getElementType();

    T getData();

    void setData(T data);

    IContainer<?> getContainer();

    boolean isEmpty();

    default Object toScalar() {
        return getData();
    }

    //

    iString getText();

    iString getText(iString fallback);

    String getString();

    String getString(String fallback);

    Integer getInt()
            throws ParseException;

    int getInt(int fallback);

    Long getLong()
            throws ParseException;

    long getLong(long fallback);

    Boolean getBool()
            throws ParseException;

    boolean getBool(boolean fallback);

    <E extends Enum<E>> E getEnum(Class<E> enumType)
            throws ParseException;

    <E extends Enum<E>> E getEnum(Class<E> enumType, E fallback);

    //

    default String[] getStringArray() {
        return new String[] { getString() };
    }

    default Integer[] getIntArray()
            throws ParseException {
        return new Integer[] { getInt() };
    }

    default Long[] getLongArray()
            throws ParseException {
        return new Long[] { getLong() };
    }

    default Boolean[] getBoolArray()
            throws ParseException {
        return new Boolean[] { getBool() };
    }

    default String[] getStringArray(String fallback) {
        return new String[] { getString(fallback) };
    }

    default int[] getIntArray(int fallback)
            throws ParseException {
        return new int[] { getInt(fallback) };
    }

    default long[] getLongArray(long fallback)
            throws ParseException {
        return new long[] { getLong(fallback) };
    }

    default boolean[] getBoolArray(boolean fallback)
            throws ParseException {
        return new boolean[] { getBool(fallback) };
    }

    //

    Object at(int index);

    Object at(int index, Object fallback);

    default iString getText(int index) {
        return getText(index, null);
    }

    iString getText(int index, iString fallback);

    default String getString(int index) {
        return getString(index, null);
    }

    String getString(int index, String fallback);

    Integer getIntAt(int index)
            throws ParseException;

    int getInt(int index, int fallback);

    Long getLongAt(int index)
            throws ParseException;

    long getLong(int index, long fallback);

    Boolean getBool(int index)
            throws ParseException;

    boolean getBool(int index, boolean fallback);

    <E extends Enum<E>> E getEnum(Class<E> enumType, int index)
            throws ParseException;

    <E extends Enum<E>> E getEnum(Class<E> enumType, int index, E fallback);

}
