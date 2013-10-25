package net.bodz.bas.t.iterator;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class RecIterator<T>
        extends PrefetchedIterator<T> {

    private LinkedList<Iterator<? extends T>> stack;
    private int lastElementDepth;

    public RecIterator(Iterator<? extends T> start) {
        stack = new LinkedList<>();
        stack.push(start);
    }

    public int getLastElementDepth() {
        return lastElementDepth;
    }

    @Override
    protected T fetch() {
        if (stack.isEmpty())
            return end();

        Iterator<? extends T> top = stack.peek();
        while (!top.hasNext()) {
            stack.pop();
            if (stack.isEmpty())
                return end();
            top = stack.peek();
        }

        T elm = top.next();
        lastElementDepth = stack.size();

        Iterator<? extends T> sub = expand(elm);
        if (sub != null)
            stack.push(sub);

        return elm;
    }

    protected abstract Iterator<? extends T> expand(T element);

}
