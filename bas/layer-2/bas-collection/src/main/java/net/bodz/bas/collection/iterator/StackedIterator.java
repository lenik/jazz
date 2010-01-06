package net.bodz.bas.collection.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.bas.collection.list.LinkedStack;
import net.bodz.bas.collection.list.Stack;

/**
 * @see NestedIterator
 * @test StackedIteratorTest
 */
public class StackedIterator<T> implements Iterator<T> {

    private Stack<Iterator<T>> stack;
    private Iterator<T> currentIterator;
    private Iterator<T> lastIterator;

    public StackedIterator() {
        stack = new LinkedStack<Iterator<T>>();
    }

    public StackedIterator(Iterable<Iterator<T>> iterators) {
        this();
        for (Iterator<T> iter : iterators)
            push(iter);
    }

    public StackedIterator(Iterator<T> start) {
        this();
        push(start);
    }

    public int size() {
        return stack.size();
    }

    public void push(Iterator<T> iterator) {
        if (currentIterator != null)
            stack.push(currentIterator);
        currentIterator = iterator;
        join();
    }

    void join() {
        // P.hasNext -> P.fetch -> S.push(expand in-place)
        if (currentIterator.hasNext()) { // volatile: currentIterator, stack
            // -> v0.end, v1.end, ..., vn, currentIterator'.valid
            // remove(v0...vn) next time join.
        } else {
            // -> v0.end, v1.end, ..., vn, currentIterator'.end
            // remove(v0...vn) immediately.
            while (!stack.isEmpty()) {
                Iterator<T> top_ = stack.top();
                if (top_.hasNext()) { // volatile: currentIterator, stack
                    // now maybe top_ != stack.top()
                    currentIterator = stack.pop();
                    // assert currentIterator.hasNext();
                    return;
                } else
                    // drop
                    stack.pop();
            }
            currentIterator = null;
        }
    }

    @Override
    public boolean hasNext() {
        if (currentIterator == null)
            return false;
        return true;
    }

    static boolean looseMode = true;

    // protected final Object discard = new Object();

    @Override
    public T next() {
        if (currentIterator == null)
            throw new NoSuchElementException();
        lastIterator = currentIterator;
        T x = currentIterator.next(); // volatile: currentIterator, stack
        join();
        return x;
    }

    @Override
    public void remove() {
        if (lastIterator == null)
            throw new IllegalStateException();
        lastIterator.remove();
    }

    @Override
    public String toString() {
        if (currentIterator == null)
            return "empty stack"; 
        StringBuffer buf = new StringBuffer((1 + stack.size()) * 100);
        buf.append("Iterators in stack: \n"); 
        buf.append("  * " + currentIterator.getClass().getSimpleName() + ": "  
                + currentIterator + "\n"); 
        for (Iterator<T> iter : stack) {
            buf.append("    "); 
            buf.append(iter.getClass().getSimpleName());
            buf.append(": "); 
            buf.append(iter);
            buf.append('\n');
        }
        return buf.toString();
    }

}
