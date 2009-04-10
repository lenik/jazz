package net.bodz.bas.types.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.types.LinkedStack;
import net.bodz.bas.types.Stack;

/**
 * @see NestedIterator
 * @test StackedIteratorTest
 */
public class StackedIterator<T> implements Iterator<T> {

    private Stack<Iterator<T>> stack;
    private Iterator<T>        currentIterator;
    private Iterator<T>        lastIterator;

    public StackedIterator() {
        stack = new LinkedStack<Iterator<T>>();
    }

    public StackedIterator(Iterable<Iterator<T>> iterators) {
        this();
        for (Iterator<T> iter : iterators)
            stack.push(iter);
        nextIter();
    }

    public StackedIterator(Iterator<T> start) {
        this();
        if (start.hasNext())
            currentIterator = start;
    }

    void nextIter() {
        while (!stack.isEmpty()) {
            Iterator<T> iter = stack.pop();
            if (iter.hasNext()) {
                currentIterator = iter;
                return;
            }
        }
        currentIterator = null;
    }

    public void push(Iterator<T> iterator) {
        if (!iterator.hasNext())
            return;
        if (currentIterator != null)
            if (currentIterator.hasNext())
                stack.push(currentIterator);
        currentIterator = iterator;
    }

    @Override
    public boolean hasNext() {
        if (currentIterator == null)
            return false;
        return true;
    }

    @Override
    public T next() {
        if (currentIterator == null)
            throw new NoSuchElementException();
        T x = currentIterator.next();
        if (!currentIterator.hasNext())
            nextIter();
        return x;
    }

    @Override
    public void remove() {
        if (lastIterator == null)
            throw new IllegalStateException();
        lastIterator.remove();
    }

}
