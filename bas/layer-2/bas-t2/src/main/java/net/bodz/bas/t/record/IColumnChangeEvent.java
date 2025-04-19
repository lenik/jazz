package net.bodz.bas.t.record;

/**
 * @see net.bodz.bas.bean.api.IPropertyChangeEvent
 */
public interface IColumnChangeEvent {

    Object getSource();

    IColumnType<?, ?> getColumn();

    Object getNewValue();

    Object getOldValue();

    Object getPropagationId();

    void setPropagationId(Object propagationId);

}
