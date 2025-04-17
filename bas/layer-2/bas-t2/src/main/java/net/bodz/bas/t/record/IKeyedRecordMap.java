package net.bodz.bas.t.record;

public interface IKeyedRecordMap<K, T extends IKeyed<K>>
        extends IRecordMap<K, T> {

    default void add(T record) {
        K key = record.getKey();
        add(key, record);
    }

    default void insert(T record) {
        K key = record.getKey();
        insert(key, record);
    }

    default void update(T record) {
        K key = record.getKey();
        update(key, record);
    }

}
