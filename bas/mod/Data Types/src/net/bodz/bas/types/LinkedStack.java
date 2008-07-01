package net.bodz.bas.types;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.LinkedList;

public class LinkedStack<T> extends LinkedList<T> implements Stack<T> {

    static final long serialVersionUID = 9193050893163832214L;

    public LinkedStack() {
        super();
    }

    public LinkedStack(Collection<? extends T> c) {
        super(c);
    }

    @Override
    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return removeFirst();
    }

    @Override
    public void push(T item) {
        addFirst(item);
    }

    public T top() {
        if (isEmpty())
            throw new EmptyStackException();
        return getFirst();
    }

    public void top(T newTop) {
        if (isEmpty())
            throw new EmptyStackException();
        set(0, newTop);
    }

}
