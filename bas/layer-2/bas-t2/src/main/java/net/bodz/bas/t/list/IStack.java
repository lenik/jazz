package net.bodz.bas.t.list;

import java.util.EmptyStackException;
import java.util.List;

public interface IStack<T>
        extends
            List<T> {

    void push(T item);

    T pop()
            throws EmptyStackException;

    T top()
            throws EmptyStackException;

    void replaceTop(T newTop);

    default T peek()
            throws EmptyStackException {
        return top();
    }

    default T peek(int lookahead)
            throws EmptyStackException {
        int pos = size() - 1 - lookahead;
        return get(pos);
    }

    default T peek(int lookahead, T fallback) {
        int n = size();
        int pos = n - 1 - lookahead;
        if (pos < 0 || pos >= n)
            return fallback;
        else
            return get(pos);
    }

    default Iterable<T> topLast() {
        return () -> iterator();
    }

    Iterable<T> topFirst();

}
