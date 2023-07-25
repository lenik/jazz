package net.bodz.bas.bean.api;

public interface IPropertyChangeEvent {

    Object getSource();

    String getPropertyName();

    Object getNewValue();

    Object getOldValue();

    Object getPropagationId();

    void setPropagationId(Object propagationId);

}