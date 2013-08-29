package net.bodz.bas.c.java.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.LazyLoadException;

public class NewArrayList<K, E>
        implements IMapEntryLoader<K, List<E>> {

    @Override
    public List<E> loadValue(K key)
            throws LazyLoadException {
        return new ArrayList<E>();
    }

}
