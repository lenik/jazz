package net.bodz.bas.net.io;

import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface IMatchedEntryVisitor<K, V> {

    void matchedEntry(@NotNull K key, V value);

}
