package net.bodz.bas.t.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;

/**
 * Top-last list stack.
 */
public class ArrayStack<T>
        extends ArrayList<T>
        implements Stack<T> {

    private static final long serialVersionUID = 9005024230227614006L;

    public ArrayStack() {
        super();
    }

    public ArrayStack(Collection<? extends T> c) {
        super(c);
    }

    public ArrayStack(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void push(T item) {
        add(item);
    }

    @Override
    public T pop() {
        int size = size();
        if (size == 0)
            throw new EmptyStackException();
        T top = remove(size - 1);
        return top;
    }

    @Override
    public T top() {
        int size = size();
        if (size == 0)
            throw new EmptyStackException();
        T top = get(size - 1);
        return top;
    }

    @Override
    public void top(T newTop) {
        int size = size();
        if (size == 0)
            throw new EmptyStackException();
        set(size - 1, newTop);
    }

}
