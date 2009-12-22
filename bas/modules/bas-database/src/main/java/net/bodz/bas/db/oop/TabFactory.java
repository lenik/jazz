package net.bodz.bas.db.oop;

import net.bodz.bas.commons.iterators.XIterator;

public interface TabFactory<T> {

    T get(TabKey key) throws TabException;

    void put(TabKey key, T obj) throws TabException;

    void add(TabKey key, T obj) throws TabException;

    XIterator<T, TabException> iterator();

    Iterable<T> getAll();

    boolean remove(TabKey key);

}
