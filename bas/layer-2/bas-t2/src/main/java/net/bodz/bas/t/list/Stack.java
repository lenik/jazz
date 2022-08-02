package net.bodz.bas.t.list;

import java.util.EmptyStackException;
import java.util.List;

public interface Stack<T>
        extends
            List<T> {

    void push(T item);

    T pop()
            throws EmptyStackException;

    T top()
            throws EmptyStackException;

    void top(T newTop);

}
