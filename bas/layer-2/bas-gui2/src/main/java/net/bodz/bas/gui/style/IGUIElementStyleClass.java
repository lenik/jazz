package net.bodz.bas.gui.style;

import net.bodz.bas.gui.css3.ICss3StyleClass;

public interface IGUIElementStyleClass
        extends ICss3StyleClass, IInputStyleClass {

    @Override
    IGUIElementStyleClass getParent();

    IFontType getFontType();

    void setFontType(IFontType fontType);

    IStrokeType getStrokeType();

    void setStrokeType(IStrokeType strokeType);

    IFillType getFillType();

    void setFillType(IFillType fillType);

    IImageData getImage(ImageUsage usage);

    void setImage(ImageUsage usage, IImageData imageData);

    String getViewId();

}
