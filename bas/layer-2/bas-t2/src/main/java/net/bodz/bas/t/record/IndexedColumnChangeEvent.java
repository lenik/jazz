package net.bodz.bas.t.record;

import java.util.EventObject;

/**
 * @see java.beans.IndexedPropertyChangeEvent
 */
public class IndexedColumnChangeEvent
        extends ColumnChangeEvent {

    final int index;

    public IndexedColumnChangeEvent(Object source, IColumnType<?, ?> column, Object oldValue, Object newValue, int index) {
        super(source, column, oldValue, newValue);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    protected void appendTo(StringBuilder sb) {
        sb.append("; index=").append(getIndex());
    }

}
