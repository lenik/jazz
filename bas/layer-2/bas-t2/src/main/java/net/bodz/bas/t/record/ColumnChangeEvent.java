package net.bodz.bas.t.record;

import java.util.EventObject;

/**
 * @see java.beans.PropertyChangeEvent
 */
public class ColumnChangeEvent
        extends EventObject
        implements IColumnChangeEvent {

    private static final long serialVersionUID = 1L;

    final IColumnType<?, ?> column;
    final Object oldValue;
    Object newValue;
    private Object propagationId;

    public ColumnChangeEvent(Object source, IColumnType<?, ?> column, Object oldValue, Object newValue) {
        super(source);
        this.column = column;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public IColumnType<?, ?> getColumn() {
        return column;
    }

    @Override
    public Object getOldValue() {
        return oldValue;
    }

    @Override
    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }

    @Override
    public Object getPropagationId() {
        return propagationId;
    }

    @Override
    public void setPropagationId(Object propagationId) {
        this.propagationId = propagationId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getName());
        sb.append("[column=").append(getColumn().getName());
        appendTo(sb);
        sb.append("; oldValue=").append(getOldValue());
        sb.append("; newValue=").append(getNewValue());
        sb.append("; propagationId=").append(getPropagationId());
        sb.append("; source=").append(getSource());
        sb.append("]");
        return sb.toString();
    }

    protected void appendTo(StringBuilder sb) {
    }

}
