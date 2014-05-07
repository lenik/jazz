package net.bodz.bas.ui.style;

public interface IInputBehaviorDeclaration {

    Boolean getEnabled();

    void setEnabled(Boolean enabled);

    Boolean getReadOnly();

    void setReadOnly(Boolean readOnly);

    Integer getTabOrder();

    void setTabOrder(Integer tabOrder);

    Integer getMaxLength();

    void setMaxLength(Integer maxLength);

    Character getEchoChar();

    void setEchoChar(Character echoChar);

}
