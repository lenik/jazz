package net.bodz.bas.types.set;

public abstract class NonfinalRange<T> extends Range<T> {

    public NonfinalRange(T first, T last) {
        super(first, last);
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public Set<T> toFinal() {
        return new Range<T>(first, last, cmp);
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getLast() {
        return last;
    }

    public void setLast(T last) {
        this.last = last;
    }

}
