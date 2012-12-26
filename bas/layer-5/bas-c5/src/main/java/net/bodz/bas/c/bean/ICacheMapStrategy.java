package net.bodz.bas.c.bean;

public interface ICacheMapStrategy<K, V> {

    V get(K key);

    void set(K key, V value);

}
