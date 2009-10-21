package net.bodz.bas.types.set;

import java.util.Comparator;

public abstract class Range<T> extends _OrderedSet<T> {

    T first;
    T last;

    public Range(T first, T last) {
        this(first, last, null);
    }

    public Range(T first, T last, Comparator<? super T> cmp) {
        super(cmp);
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean isFinal() {
        return true;
    }

    @Override
    public Set<T> toFinal() {
        return this;
    }

    @Override
    public boolean isFinite() {
        return true;
    }

    @Override
    public T first() {
        return first;
    }

    @Override
    public T last() {
        return last;
    }

    @Override
    public boolean contains(T x) {
        if (cmp.compare(x, first) < 0)
            return false;
        if (cmp.compare(x, last) > 0)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        if (first != null)
            hash ^= first.hashCode();
        if (last != null)
            hash ^= last.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "(" + first + " .. " + last + ")";
    }

}
