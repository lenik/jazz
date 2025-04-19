package net.bodz.bas.t.record;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.map.ListMap;
import net.bodz.bas.t.map.SetMap;

public class BasicRecordMap<K, T>
        extends AbstractMap<K, T>
        implements IRecordMap<K, T> {

    final IRecordType<T> recordType;

    final Map<K, T> map = SortOrder.NONE.newMap();
    final Map<IColumnType<?, ?>, ListMap<?, K>> columnIndices = SortOrder.NONE.newMap();
    final SetMap<T, K> rindex = new SetMap<>();

    Set<String> hiddenKeys = new HashSet<>();
    boolean nullKeyIndexed = true;
    boolean nullValueIndexed = false;

    public BasicRecordMap(IRecordType<T> recordType) {
        this.recordType = recordType;
        // this.rindex = columnIndex(recordType.getKeyColumn());
    }

    @Override
    public boolean isKeyIndexed(K key) {
        if (key == null && !nullKeyIndexed)
            return false;
        if (hiddenKeys.contains(key))
            return false;
        return true;
    }

    @Override
    public boolean isNullValueIndexed() {
        return nullValueIndexed;
    }

    public void setNullValueIndexed(boolean nullValueIndexed) {
        this.nullValueIndexed = nullValueIndexed;
    }

    protected <E> boolean shouldIndex(IColumnType<?, E> column, Object value) {
        return value != null || nullValueIndexed;
    }

    protected <E> boolean shouldRemoveIndex(IColumnType<?, E> column, Object value) {
        return true;
    }

    @Override
    public void reIndex() {
        columnIndices.clear();
        rindex.clear();
        for (Entry<K, T> entry : map.entrySet()) {
            K key = entry.getKey();
            T record = entry.getValue();
            if (!isKeyIndexed(key))
                continue;

            for (IColumnType<T, ?> column : recordType.getColumns()) {
                @SuppressWarnings("unchecked")
                ListMap<Object, K> index = (ListMap<Object, K>) makeIndex(column);
                Object columnValue = column.get(record);
                if (shouldIndex(column, columnValue))
                    index.addToList(columnValue, key);
            }

            rindex.addToSet(record, key);
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public <E> ListMap<E, K> makeIndex(IColumnType<?, E> column) {
        ListMap<E, K> index = (ListMap<E, K>) columnIndices.get(column);
        if (index == null) {
            index = new ListMap<>();
            columnIndices.put(column, index);
        }
        return index;
    }

    @Override
    public boolean add(@NotNull K key, @NotNull T record) {
        if (map.containsKey(key))
            return update(key, record);
        else {
            insert(key, record);
            return true;
        }
    }

    @Override
    public void insert(@NotNull K key, @NotNull T record) {
        if (map.containsKey(key))
            throw new IllegalStateException("key duplicated: " + key);
        map.put(key, record);
        for (IColumnType<T, ?> column : recordType.getColumns()) {
            @SuppressWarnings("unchecked")
            ListMap<Object, K> index = (ListMap<Object, K>) makeIndex(column);
            Object columnValue = column.get(record);
            if (shouldIndex(column, columnValue))
                index.addToList(columnValue, key);
        }
        rindex.addToSet(record, key);
        setPropertyChangeTracking(key, record);
    }

    @Override
    public boolean update(@NotNull K key, @NotNull T record) {
        if (!map.containsKey(key))
            throw new IllegalStateException("invalid key: " + key);

        T old = map.get(key);
        if (old == record) {
            if (isTrackingPropertyChange())
                return false;
            else {
                remove(key);
                insert(key, record);
                return true;
            }
        }

        for (IColumnType<T, ?> column : recordType.getColumns()) {
            Object columnValue = column.get(old);
            if (shouldRemoveIndex(column, columnValue)) {
                @SuppressWarnings("unchecked")
                ListMap<Object, K> index = (ListMap<Object, K>) makeIndex(column);
                index.removeFromList(columnValue, key);
            }
        }
        rindex.removeFromSet(old, key);

        map.put(key, record);

        for (IColumnType<T, ?> column : recordType.getColumns()) {
            Object columnValue = column.get(record);
            if (shouldIndex(column, columnValue)) {
                @SuppressWarnings("unchecked")
                ListMap<Object, K> index = (ListMap<Object, K>) makeIndex(column);
                index.addToList(columnValue, key);
            }
        }
        rindex.addToSet(record, key);

        if (isTrackingPropertyChange()) {
            unsetPropertyChangeTracking(key, old);
            setPropertyChangeTracking(key, record);
        }
        return true;
    }

    @Override
    public T remove(@NotNull Object _key) {
        T record = map.remove(_key);
        if (record == null)
            return null;

        @SuppressWarnings("unchecked")
        K key = (K) _key;

        for (IColumnType<T, ?> column : recordType.getColumns()) {
            Object columnValue = column.get(record);
            if (shouldRemoveIndex(column, columnValue)) {
                @SuppressWarnings("unchecked")
                ListMap<Object, K> index = (ListMap<Object, K>) makeIndex(column);
                index.removeFromList(columnValue, key);
            }
        }
        rindex.removeFromSet(record, key);

        if (isTrackingPropertyChange())
            unsetPropertyChangeTracking(key, record);
        return record;
    }

    @Override
    public boolean isTrackingPropertyChange() {
        return false;
    }

    protected void setPropertyChangeTracking(@NotNull K key, @NotNull T record) {
    }

    protected void unsetPropertyChangeTracking(@NotNull K key, @NotNull T record) {
    }

    @Override
    public Set<K> find(@NotNull T record) {
        return rindex.makeSet(record);
    }

    @Override
    public <E> Set<K> find(IColumnType<T, E> column, Predicate<E> columnValuePredicate) {
        Set<K> keys = new LinkedHashSet<>();
        for (Map.Entry<K, T> entry : map.entrySet()) {
            K key = entry.getKey();
            if (!isKeyIndexed(key))
                continue;
            T record = entry.getValue();
            E columnValue = column.get(record);
            if (columnValuePredicate.test(columnValue))
                keys.add(key);
        }
        return keys;
    }

    @Override
    public <E1, E2> Set<K> find(//
            IColumnType<T, E1> column1, Predicate<E1> column1ValuePredicate, //
            IColumnType<T, E2> column2, Predicate<E2> column2ValuePredicate) {
        Set<K> keys = new LinkedHashSet<>();
        for (Map.Entry<K, T> entry : map.entrySet()) {
            K key = entry.getKey();
            if (!isKeyIndexed(key))
                continue;
            T record = entry.getValue();
            E1 column1Value = column1.get(record);
            E2 column2Value = column2.get(record);
            if (column1ValuePredicate.test(column1Value) //
                    && column2ValuePredicate.test(column2Value))
                keys.add(key);
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
    public T put(K key, T record) {
        if (record == null) {
            return remove(key);
        } else {
            add(key, record);
            return null;
        }
    }

    @Override
    public synchronized void clear() {
        if (isTrackingPropertyChange())
            for (Entry<K, T> entry : map.entrySet())
                unsetPropertyChangeTracking(entry.getKey(), entry.getValue());
        rindex.clear();
        columnIndices.clear();
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
