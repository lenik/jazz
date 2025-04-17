package net.bodz.bas.t.record;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.map.ListMap;
import net.bodz.bas.t.map.SetMap;

public class RecordMap<K, T>
        implements IRecordMap<K, T> {

    final IRecordType<T> recordType;

    final Map<K, T> map = SortOrder.NONE.newMap();
    final Map<IColumnType<?, ?>, ListMap<?, K>> columnIndices = SortOrder.NONE.newMap();
    final SetMap<T, K> rindex = new SetMap<>();

    public RecordMap(IRecordType<T> recordType) {
        this.recordType = recordType;
        // this.rindex = columnIndex(recordType.getKeyColumn());
    }

    @SuppressWarnings("unchecked")
    <E> ListMap<E, K> columnIndex(IColumnType<?, ?> column) {
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
            ListMap<Object, K> index = columnIndex(column);
            Object columnValue = column.get(record);
            index.addToList(columnValue, key);
        }
        rindex.addToSet(record, key);
    }

    @Override
    public void update(K key, T record) {
        if (!map.containsKey(key))
            throw new IllegalStateException("key missing: " + key);

        T old = map.get(key);
        if (old == record)
            throw new IllegalArgumentException("can't update with the same reference. mutable record isn't supported.");

        for (IColumnType<T, ?> column : recordType.getColumns()) {
            ListMap<Object, K> index = columnIndex(column);
            Object columnValue = column.get(old);
            index.removeFromList(columnValue, key);
        }
        rindex.removeFromSet(old, key);

        map.put(key, record);

        for (IColumnType<T, ?> column : recordType.getColumns()) {
            ListMap<Object, K> index = columnIndex(column);
            Object columnValue = column.get(record);
            index.addToList(columnValue, key);
        }
        rindex.addToSet(record, key);
    }

    @Override
    public T get(K key) {
        return map.get(key);
    }

    @Override
    public Set<K> find(T record) {
        return rindex.set(record);
    }

    @Override
    public <E> Set<K> findEquals(IColumnType<T, E> column, E columnValueToMatch) {
        Set<K> keys = new LinkedHashSet<>();
        for (Map.Entry<K, T> entry : map.entrySet()) {
            T record = entry.getValue();
            E columnValue = column.get(record);
            if (Objects.equals(columnValue, columnValueToMatch))
                keys.add(entry.getKey());
        }
        return keys;
    }

    @Override
    public boolean remove(K key) {
        T record = map.remove(key);
        if (record == null)
            return false;

        for (IColumnType<T, ?> column : recordType.getColumns()) {
            ListMap<Object, K> index = columnIndex(column);
            Object columnValue = column.get(record);
            index.removeFromList(columnValue, key);
        }
        rindex.removeFromSet(record, key);
        return true;
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

}
