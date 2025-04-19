package net.bodz.bas.t.record;

import java.beans.PropertyChangeListener;
import java.util.EventListenerProxy;

import net.bodz.bas.meta.decl.NotNull;

/**
 * @see java.beans.PropertyChangeListenerProxy
 */
public class ColumnChangeListenerProxy
        extends EventListenerProxy<IColumnChangeListener>
        implements IColumnChangeListener {

    final IColumnType<?, ?> column;

    public ColumnChangeListenerProxy(@NotNull IColumnType<?, ?> column, @NotNull IColumnChangeListener listener) {
        super(listener);
        this.column = column;
    }

    @Override
    public void columnChange(IColumnChangeEvent event) {
        getListener().columnChange(event);
    }

    @Override
    public boolean isProxy() {
        return true;
    }

}
