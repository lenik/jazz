package net.bodz.bas.t.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ReversedListIterator<T>
        implements
            Iterator<T> {

    List<T> list;
    int pos;

    public ReversedListIterator(List<T> list) {
        this.list = list;
        pos = list.size();
    }

    @Override
    public boolean hasNext() {
        return pos > 0;
    }

    @Override
    public T next() {
        if (pos <= 0)
            throw new NoSuchElementException();

        pos--;
        return list.get(pos);
    }

}
