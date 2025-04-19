package net.bodz.bas.t.record;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.map.ListMap;
import net.bodz.bas.t.map.SetMap;

public class BasicRecordMap<K, T>
        implements IRecordMap<K, T> {

    final IRecordType<T> recordType;

    final Map<K, T> map = SortOrder.NONE.newMap();
    final Map<IColumnType<?, ?>, ListMap<?, K>> columnIndices = SortOrder.NONE.newMap();
    final SetMap<T, K> rindex = new SetMap<>();

    public BasicRecordMap(IRecordType<T> recordType) {
        this.recordType = recordType;
        // this.rindex = columnIndex(recordType.getKeyColumn());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> ListMap<E, K> index(IColumnType<?, E> column) {
        ListMap<E, K> index = (ListMap<E, K>) columnIndices.get(column);
        if (index == null) {
            index = new ListMap<>();
            columnIndices.put(column, index);
        }
        return index;
    }

    @Override
    public void add(K key, T record) {
        if (map.containsKey(key))
            update(key, record);
        else
            insert(key, record);
    }

    @Override
    public void insert(K key, T record) {
        if (map.containsKey(key))
            throw new IllegalStateException("key duplicated: " + key);
        map.put(key, record);
        for (IColumnType<T, ?> column : recordType.getColumns()) {
            @SuppressWarnings("unchecked")
            ListMap<Object, K> index = (ListMap<Object, K>) index(column);
            Object columnValue = column.get(record);
            index.addToList(columnValue, key);
        }
        rindex.addToSet(record, key);
        bind(key, record);
    }

    @Override
    public void update(K key, T record) {
        if (!map.containsKey(key))
            throw new IllegalStateException("key missing: " + key);

        T old = map.get(key);
        if (old == record)
            throw new IllegalArgumentException("can't update with the same reference. mutable record isn't supported.");

        for (IColumnType<T, ?> column : recordType.getColumns()) {
            @SuppressWarnings("unchecked")
            ListMap<Object, K> index = (ListMap<Object, K>) index(column);
            Object columnValue = column.get(old);
            index.removeFromList(columnValue, key);
        }
        rindex.removeFromSet(old, key);
        unbind(key, old);

        map.put(key, record);

        for (IColumnType<T, ?> column : recordType.getColumns()) {
            @SuppressWarnings("unchecked")
            ListMap<Object, K> index = (ListMap<Object, K>) index(column);
            Object columnValue = column.get(record);
            index.addToList(columnValue, key);
        }
        rindex.addToSet(record, key);
        bind(key, record);
    }

    @Override
    public T remove(Object _key) {
        T record = map.remove(_key);
        if (record == null)
            return null;

        @SuppressWarnings("unchecked")
        K key = (K) _key;

        for (IColumnType<T, ?> column : recordType.getColumns()) {
            @SuppressWarnings("unchecked")
            ListMap<Object, K> index = (ListMap<Object, K>) index(column);
            Object columnValue = column.get(record);
            index.removeFromList(columnValue, key);
        }
        rindex.removeFromSet(record, key);
        unbind(key, record);
        return record;
    }

    protected void bind(K key, T record) {
    }

    protected void unbind(K key, T record) {
    }

    @Override
    public Set<K> find(T record) {
        return rindex.makeSet(record);
    }

    @Override
    public <E> Set<K> find(IColumnType<T, E> column, Predicate<E> columnValuePredicate) {
        Set<K> keys = new LinkedHashSet<>();
        for (Map.Entry<K, T> entry : map.entrySet()) {
            T record = entry.getValue();
            E columnValue = column.get(record);
            if (columnValuePredicate.test(columnValue))
                keys.add(entry.getKey());
        }
        return keys;
    }


    @Override
    public <E1, E2> Set<K> find(//
            IColumnType<T, E1> column1, Predicate<E1> column1ValuePredicate, //
            IColumnType<T, E2> column2, Predicate<E2> column2ValuePredicate) {
        Set<K> keys = new LinkedHashSet<>();
        for (Map.Entry<K, T> entry : map.entrySet()) {
            T record = entry.getValue();
            E1 column1Value = column1.get(record);
            E2 column2Value = column2.get(record);
            if (column1ValuePredicate.test(column1Value) //
                    && column2ValuePredicate.test(column2Value))
                keys.add(entry.getKey());
        }
        return keys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int i = 0;
        for (K key : map.keySet()) {
            if (i != 0)
                sb.append(", ");
            sb.append(key).append(": ");
            T record = map.get(key);
            sb.append(record);
            i++;
        }
        sb.append("}");
        return sb.toString();
    }

    //


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public T get(Object key) {
        return map.get(key);
    }

    @Override
    public T put(K key, T value) {
        return map.put(key, value);
    }

    @Override
    public void putAll(@NotNull Map<? extends K, ? extends T> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NotNull
    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @NotNull
    @Override
    public Collection<T> values() {
        return map.values();
    }

    @NotNull
    @Override
    public Set<Entry<K, T>> entrySet() {
        return map.entrySet();
    }

}
