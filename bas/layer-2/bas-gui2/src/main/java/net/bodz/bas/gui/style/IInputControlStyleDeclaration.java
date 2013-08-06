package net.bodz.bas.gui.style;

public interface IInputControlStyleDeclaration {

    Boolean getEnabled();

    void setEnabled(Boolean enabled);

    Boolean getReadOnly();

    void setReadOnly(Boolean readOnly);

    Integer getTabOrder();

    void setTabOrder(Integer tabOrder);

    Integer getMaxLength();

    void setMaxLength(Integer maxLength);

}
