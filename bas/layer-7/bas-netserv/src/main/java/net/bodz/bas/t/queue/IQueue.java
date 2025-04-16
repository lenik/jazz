package net.bodz.bas.t.queue;

import java.util.Iterator;

import net.bodz.bas.meta.decl.NotNull;

public interface IQueue<T>
        extends Iterable<T> {

    default boolean isEmpty() {
        return size() == 0;
    }

    default boolean isNotEmpty() {
        return !isEmpty();
    }

    int size();

    void clear();

    @NotNull
    @Override
    Iterator<T> iterator();

    T take();

}
