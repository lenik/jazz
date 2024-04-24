package net.bodz.bas.t.vector;

import java.util.Iterator;

public interface Vector<T>
        extends
            Iterable<T> {

    int length();

    T get(int index);

    void set(int index, T value);

    @Override
    Iterator<T> iterator();

    byte byteAt(int index);

    short shortAt(int index);

    int intAt(int index);

    long longAt(int index);

    float floatAt(int index);

    double doubleAt(int index);

    boolean booleanAt(int index);

    char charAt(int index);

}
