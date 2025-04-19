package net.bodz.bas.t.record;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

/**
 * @see java.beans.PropertyChangeSupport
 */
public class ColumnChangeSupport
        implements IColumnChangeSupport {

    Object source;
    ListMap<IColumnType<?, ?>, IColumnChangeListener> map = new ListMap<>();

    public ColumnChangeSupport(Object sourceBean) {
        if (sourceBean == null) {
            throw new NullPointerException();
        }
        source = sourceBean;
    }

    @Override
    public void addColumnChangeListener(IColumnChangeListener listener) {
        if (listener == null)
            return;
        map.addToList(null, listener);
    }

    @Override
    public void removeColumnChangeListener(IColumnChangeListener listener) {
        if (listener == null)
            return;
        map.removeFromList(null, listener);
    }

    /**
     * @see PropertyChangeSupport#getPropertyChangeListeners()
     */
    @Override
    public IColumnChangeListener[] getColumnChangeListeners() {
        List<IColumnChangeListener> common = map.get(null);
        List<IColumnChangeListener> list = new ArrayList<>(common);

        for (IColumnType<?, ?> column : map.keySet()) {
            if (column != null)
                for (IColumnChangeListener specific : map.get(column)) {
                    ColumnChangeListenerProxy proxy = new ColumnChangeListenerProxy(column, specific);
                    list.add(proxy);
                }
        }
        return list.toArray(new IColumnChangeListener[0]);
    }

    @Override
    public void addColumnChangeListener(IColumnType<?, ?> column, IColumnChangeListener listener) {
        if (listener == null)
            return;
        map.addToList(column, listener);
    }

    @Override
    public void removeColumnChangeListener(IColumnType<?, ?> column, IColumnChangeListener listener) {
        if (listener == null)
            return;
        map.removeFromList(column, listener);
    }

    @Override
    public IColumnChangeListener[] getColumnChangeListeners(IColumnType<?, ?> column) {
        return map.makeList(column).toArray(new IColumnChangeListener[0]);
    }

    @Override
    public void fireColumnChange(@NotNull IColumnType<?, ?> column, Object oldValue, Object newValue) {
        if (oldValue != newValue)
            fireColumnChange(new ColumnChangeEvent(source, column, oldValue, newValue));
    }

    @Override
    public void fireColumnChange(@NotNull IColumnType<?, ?> column, int oldValue, int newValue) {
        if (oldValue != newValue)
            fireColumnChange(new ColumnChangeEvent(source, column, oldValue, newValue));
    }

    @Override
    public void fireColumnChange(@NotNull IColumnType<?, ?> column, boolean oldValue, boolean newValue) {
        if (oldValue != newValue)
            fireColumnChange(new ColumnChangeEvent(source, column, oldValue, newValue));
    }

    public void fireColumnChange(@NotNull ColumnChangeEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (oldValue != newValue) {
            IColumnType<?, ?> column = event.getColumn();

            List<IColumnChangeListener> common = map.get(null);
            if (common != null)
                for (IColumnChangeListener listener : common)
                    listener.columnChange(event);

            if (column != null) {
                List<IColumnChangeListener> specific = map.get(column);
                if (specific != null)
                    for (IColumnChangeListener listener : specific)
                        listener.columnChange(event);
            }
        }
    }

    @Override
    public void fireIndexedColumnChange(@NotNull IColumnType<?, ?> column, int index, Object oldValue, Object newValue) {
        if (oldValue != newValue)
            fireColumnChange(new IndexedColumnChangeEvent(source, column, oldValue, newValue, index));
    }

    @Override
    public void fireIndexedColumnChange(@NotNull IColumnType<?, ?> column, int index, int oldValue, int newValue) {
        if (oldValue != newValue) {
            fireColumnChange(new IndexedColumnChangeEvent(source, column, oldValue, newValue, index));
        }
    }

    @Override
    public void fireIndexedColumnChange(@NotNull IColumnType<?, ?> column, int index, boolean oldValue, boolean newValue) {
        if (oldValue != newValue) {
            fireColumnChange(new IndexedColumnChangeEvent(source, column, oldValue, newValue, index));
        }
    }

    @Override
    public boolean hasListeners(IColumnType<?, ?> column) {
        return !map.isListEmpty(column);
    }

}
