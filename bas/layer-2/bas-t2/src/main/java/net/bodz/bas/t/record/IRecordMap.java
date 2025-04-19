package net.bodz.bas.t.record;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public interface IRecordMap<K, T>
        extends Map<K, T> {

    boolean isTrackingPropertyChange();

    boolean isKeyIndexed(K key);

    boolean isNullValueIndexed();

    void reIndex();

    <E> ListMap<E, K> makeIndex(IColumnType<?, E> column);

    /**
     * @return true if added or updated, false if nothing done.
     */
    boolean add(@NotNull K key, @NotNull T record);

    void insert(@NotNull K key, @NotNull T record);

    /**
     * @return true if any change updated, false if nothing done.
     */
    boolean update(@NotNull K key, @NotNull T record);

    @Override
    T put(K key, T record);

    @Override
    T remove(@NotNull Object key);

    @Override
    void clear();

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