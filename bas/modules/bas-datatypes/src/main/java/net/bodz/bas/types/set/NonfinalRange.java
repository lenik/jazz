package net.bodz.bas.types.set;

import java.util.Comparator;

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
        return new Range<T>(first, last, cmp) {

            @Override
            public Comparator<? super T> comparator() {
                return NonfinalRange.this.comparator();
            }

            @Override
            protected T predecessor(T x) {
                return NonfinalRange.this.predecessor(x);
            }

            @Override
            protected T succeed(T x) {
                return NonfinalRange.this.succeed(x);
            }

            @Override
            public Set<T> intersect(Set<T> set) {
                return NonfinalRange.this.intersect(set);
            }

            @Override
            public SetRelation test(Set<T> set) {
                return NonfinalRange.this.test(set);
            }

        };
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
