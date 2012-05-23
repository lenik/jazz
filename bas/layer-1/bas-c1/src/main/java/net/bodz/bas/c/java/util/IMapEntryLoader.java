package net.bodz.bas.c.java.util;

import net.bodz.bas.err.LazyLoadException;

public interface IMapEntryLoader<K, V> {

    V loadValue(K key)
            throws LazyLoadException;

}
