package net.bodz.bas.collection.list;

import java.util.List;

public interface Stack<T>
        extends List<T> {

    void push(T item);

    T pop();

    T top();

    void top(T newTop);

}
