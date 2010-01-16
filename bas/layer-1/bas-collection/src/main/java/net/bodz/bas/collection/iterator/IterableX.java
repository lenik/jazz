package net.bodz.bas.collection.iterator;

public interface IterableX<T, X extends Exception>
        extends Iterable<T> {

    IteratorX<T, X> iterator();

}
