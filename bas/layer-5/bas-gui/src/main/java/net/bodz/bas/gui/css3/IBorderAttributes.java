package net.bodz.bas.gui.css3;

import net.bodz.bas.gui.css3.property.BorderStyleMode;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public interface IBorderAttributes {

    BorderStyleMode getStyle();

    void setStyle(BorderStyleMode style);

    LengthMeasure getWidth();

    void setWidth(LengthMeasure width);

    IColor getColor();

    void setColor(IColor color);

    boolean isUseCurrentColor();

    void setUseCurrentColor(boolean useCurrentColor);

    /**
     * Get the left radius.
     */
    LengthMeasure getRadius();

    /**
     * Set both left and right radius.
     */
    void setRadius(LengthMeasure radius);

    LengthMeasure getRadiusLeft();

    void setRadiusLeft(LengthMeasure radiusLeft);

    LengthMeasure getRadiusRight();

    void setRadiusRight(LengthMeasure radiusRight);

}
