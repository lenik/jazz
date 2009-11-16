package net.bodz.bas.types;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * Top-first list stack.
 */
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

    /**
     * @param index
     *            0 at the top
     */
    @Override
    public T get(int index) {
        return super.get(index);
    }

    /**
     * @param index
     *            0 at the top
     */
    @Override
    public T set(int index, T element) {
        return super.set(index, element);
    }

}
