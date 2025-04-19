package net.bodz.bas.t.record;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public interface IRecordMap<K, T>
        extends Map<K, T> {

    <E> ListMap<E, K> index(IColumnType<?, E> column);

    void add(K key, T record);

    void insert(K key, T record);

    void update(K key, T record);

    Set<K> find(T record);

    <E> Set<K> find(IColumnType<T, E> column, Predicate<E> columnValuePredicate);

    <E1, E2> Set<K> find(IColumnType<T, E1> column1, Predicate<E1> column1ValuePredicate, //
            IColumnType<T, E2> column2, Predicate<E2> column2ValuePredicate);

    default int removeAll(Set<K> keys) {
        int count = 0;
        for (K key : keys)
            if (containsKey(key)) {
                remove(key);
                count++;
            }
        return count;
    }

    default int removeAll(T record) {
        return removeAll(find(record));
    }

}