package net.bodz.bas.gui.spec0;

import net.bodz.bas.gui.css3.ICss3StyleClass;

public interface IControlStyleClass
        extends ICss3StyleClass {

    @Override
    IControlStyleClass getParent();

    Boolean getEnabled();

    void setEnabled(Boolean enabled);

    Boolean getReadOnly();

    void setReadOnly(Boolean readOnly);

    Integer getTabOrder();

    void setTabOrder(Integer tabOrder);

    String getTooltip();

    void setTooltip(String tooltip);

}
