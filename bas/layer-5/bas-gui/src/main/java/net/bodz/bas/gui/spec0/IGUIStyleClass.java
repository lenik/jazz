package net.bodz.bas.gui.spec0;

import net.bodz.bas.gui.css3.ICss3StyleClass;

public interface IGUIStyleClass
        extends ICss3StyleClass {

    @Override
    IGUIStyleClass getParent();

    Boolean getEnabled();

    void setEnabled(Boolean enabled);

    Boolean getReadOnly();

    void setReadOnly(Boolean readOnly);

    Integer getTabOrder();

    void setTabOrder(Integer tabOrder);

    IFontType getFontType();

    void setFontType(IFontType fontType);

    IStrokeType getStrokeType();

    void setStrokeType(IStrokeType strokeType);

    IFillType getFillType();

    void setFillType(IFillType fillType);

}
