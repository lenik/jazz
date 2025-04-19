package net.bodz.bas.t.record;

import net.bodz.bas.meta.decl.EventSource;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.event.IPropertyChangeListener;

/**
 * @see net.bodz.bas.t.event.IPropertyChangeSource
 */
public interface IColumnChangeSource
        extends EventSource {

    void addColumnChangeListener(@NotNull IColumnChangeListener listener);

    void addColumnChangeListener(IColumnType<?, ?> column, @NotNull IColumnChangeListener listener);

    void removeColumnChangeListener(@NotNull IColumnChangeListener listener);

    void removeColumnChangeListener(IColumnType<?, ?> column, @NotNull IColumnChangeListener listener);

}
