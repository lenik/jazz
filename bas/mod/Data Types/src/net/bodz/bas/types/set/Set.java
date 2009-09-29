package net.bodz.bas.types.set;

import net.bodz.bas.types.util.DirectIterable;
import net.bodz.bas.types.util.DirectIterator;

public interface Set<T> extends DirectIterable<T, RuntimeException> {

    boolean isFinal();

    Set<T> toFinal();

    boolean isFinite();

    T first();

    T last();

    boolean contains(T x);

    SetRelation test(Set<T> set);

    Set<T> reduce();

    Set<T> union(Set<T> set);

    Set<T> intersect(Set<T> set);

    CutResult cut(Set<T> set);

    @Override
    DirectIterator<T, RuntimeException> iterator(boolean allowOverlap);

}
