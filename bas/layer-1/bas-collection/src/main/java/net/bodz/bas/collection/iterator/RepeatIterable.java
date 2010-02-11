package net.bodz.bas.collection.iterator;

import java.util.Iterator;

import net.bodz.bas.lang.Nullables;

public class RepeatIterable<T>
        implements Iterable<T> {

    private final T object;
    private final int count;

    public RepeatIterable(T object, int count) {
        this.object = object;
        this.count = count;
    }

    @Override
    public Iterator<T> iterator() {
        return new RepeatIterator<T>(object, count);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RepeatIterable<?>))
            return false;
        RepeatIterable<?> o = (RepeatIterable<?>) obj;
        if (count != o.count)
            return false;
        if (!Nullables.equals(object, o.object))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0x6a234b7d;
        hash += count * 37;
        if (object != null)
            hash += object.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "{ " + count + " x " + object + " }";
    }

}
