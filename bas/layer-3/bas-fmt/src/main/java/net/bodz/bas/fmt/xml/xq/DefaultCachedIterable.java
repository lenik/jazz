package net.bodz.bas.fmt.xml.xq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultCachedIterable<T>
        implements
            ICachedIterable<T> {

    Iterable<T> orig;
    List<T> list = new ArrayList<>();
    boolean everRun;

    public DefaultCachedIterable(Iterable<T> orig) {
        if (orig == null)
            throw new NullPointerException("orig");
        this.orig = orig;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> origIterator = orig.iterator();
        return new CachedIterator<T>(origIterator, this);
    }

    @SuppressWarnings("unused")
    @Override
    public int size() {
        if (!everRun) {
            for (T item : this)
                ;
            assert everRun;
        }
        return list.size();
    }

    @Override
    public T get(int index) {
        if (index < list.size())
            return list.get(index);
        int n = size();
        if (index < 0 || index >= n)
            throw new IndexOutOfBoundsException("index: " + index);
        return list.get(index);
    }

}

class CachedIterator<T>
        implements
            Iterator<T> {

    DefaultCachedIterable<T> cache;
    Iterator<T> orig;

    public CachedIterator(Iterator<T> orig, DefaultCachedIterable<T> cache) {
        this.orig = orig;
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = orig.hasNext();
        if (!hasNext)
            cache.everRun = true;
        return hasNext;
    }

    @Override
    public T next() {
        T item = orig.next();
        cache.list.add(item);
        return item;
    }

}