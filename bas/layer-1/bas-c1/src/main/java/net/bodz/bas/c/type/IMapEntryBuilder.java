package net.bodz.bas.c.type;

public interface IMapEntryBuilder<K, V> {

    boolean containsEntry(K key);

    V buildEntry(K key);

}
