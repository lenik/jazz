package net.bodz.bas.t.list;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.LinkedList;

import net.bodz.bas.t.iterator.Iterables;

/**
 * Top-first list stack.
 */
public class LinkedStack<T>
        extends LinkedList<T>
        implements
            IStack<T> {

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
        return removeLast();
    }

    @Override
    public void push(T item) {
        addLast(item);
    }

    @Override
    public T top() {
        if (isEmpty())
            throw new EmptyStackException();
        return getLast();
    }

    @Override
    public void replaceTop(T newTop) {
        if (isEmpty())
            throw new EmptyStackException();
        // int lastIndex = size() - 1;
        // set(lastIndex, newTop);
        removeLast();
        addLast(newTop);
    }

    @Override
    public Iterable<T> topFirst() {
        return Iterables.reversed(this);
    }

}
