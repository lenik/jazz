package net.bodz.bas.t.record;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class AutoIndexRecordMap<K, T extends IColumnChangeSource>
        extends BasicRecordMap<K, T> {

    Map<K, IColumnChangeListener> listeners = new HashMap<>();

    public AutoIndexRecordMap(IRecordType<T> recordType) {
        super(recordType);
    }

    @Override
    public boolean isTrackingPropertyChange() {
        return true;
    }

    @Override
    protected synchronized void setPropertyChangeTracking(@NotNull K key, @NotNull T record) {
        IColumnChangeListener l = event -> {
            @SuppressWarnings("unchecked")
            ListMap<Object, K> index = (ListMap<Object, K>) makeIndex(event.getColumn());
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
    protected synchronized void unsetPropertyChangeTracking(@NotNull K key, @NotNull T record) {
        IColumnChangeListener l = listeners.remove(key);
        if (l != null)
            for (IColumnType<T, ?> column : recordType.getColumns())
                record.removeColumnChangeListener(column, l);
    }

}
