package net.bodz.bas.db.oop;

public interface TabKey {

    int size();

    /**
     * @return -1 if unknown
     */
    int getColumnIndex(int index);

    /**
     * @return <code>null</code> if unknown
     */
    String getColumnName(int index);

    Object get(int index);

    boolean isNull(int index);

    void set(int index, Object value);

}
