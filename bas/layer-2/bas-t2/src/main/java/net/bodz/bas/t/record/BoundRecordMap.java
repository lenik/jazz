package net.bodz.bas.t.record;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.t.map.ListMap;
import net.bodz.bas.t.map.SetMap;

public class BoundRecordMap<K, T extends IColumnChangeSource>
        extends BasicRecordMap<K, T> {

    Map<K, IColumnChangeListener> listeners = new HashMap<>();

    public BoundRecordMap(IRecordType<T> recordType) {
        super(recordType);
    }

    @Override
    protected synchronized void bind(K key, T record) {
        IColumnChangeListener l = event -> {
            @SuppressWarnings("unchecked")
            ListMap<Object, K> index = (ListMap<Object, K>) index(event.getColumn());
            Object oldValue = event.getOldValue();
            Object newValue = event.getNewValue();
            index.removeFromList(oldValue, key);
            index.addToList(newValue, key);
        };
        for (IColumnType<T, ?> column : recordType.getColumns())
            record.addColumnChangeListener(column, l);
        listeners.put(key, l);
    }

    @Override
    protected synchronized void unbind(K key, T record) {
        IColumnChangeListener l = listeners.remove(key);
        if (l != null)
            for (IColumnType<T, ?> column : recordType.getColumns())
                record.removeColumnChangeListener(column, l);
    }

}
