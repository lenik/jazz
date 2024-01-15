package net.bodz.bas.t.iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class ReversedLinkedListIterator<T>
        implements
            Iterator<T> {

    ListIterator<T> iterator;

    public ReversedLinkedListIterator(LinkedList<T> list) {
        this.iterator = list.listIterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasPrevious();
    }

    @Override
    public T next() {
        return iterator.previous();
    }

}
