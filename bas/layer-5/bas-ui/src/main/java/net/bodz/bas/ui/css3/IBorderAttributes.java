package net.bodz.bas.ui.css3;

import net.bodz.bas.ui.css3.property.BorderStyleMode;
import net.bodz.bas.ui.style.IColor;

public interface IBorderAttributes {

    BorderStyleMode getStyle();

    void setStyle(BorderStyleMode style);

    ICss3Length getWidth();

    void setWidth(ICss3Length width);

    IColor getColor();

    void setColor(IColor color);

    boolean isUseCurrentColor();

    void setUseCurrentColor(boolean useCurrentColor);

}
