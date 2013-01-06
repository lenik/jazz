package net.bodz.bas.gui.style;

import net.bodz.bas.gui.css3.ICss3StyleClass;

public interface IGUIElementStyleClass
        extends ICss3StyleClass {

    @Override
    IGUIElementStyleClass getParent();

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

    IImageData getImage(ImageUsage usage);

    String getViewId();

}
