package net.bodz.bas.t.record;

import java.util.Set;

public interface IRecordMap<K, T> {

    void add(K key, T record);

    void insert(K key, T record);

    void update(K key, T record);

    T get(K key);

    Set<K> find(T record);

    <E> Set<K> findEquals(IColumnType<T, E> column, E columnValueToMatch);

    boolean remove(K key);

    default int removeAll(Set<K> keys) {
        int count = 0;
        for (K key : keys)
            if (remove(key))
                count++;
        return count;
    }

    default int removeAll(T record) {
        int count = 0;
        for (K key : find(record)) {
            if (remove(key))
                count++;
        }
        return count;
    }


}
