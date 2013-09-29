package net.bodz.bas.gui.css3;

import java.io.Serializable;

import net.bodz.bas.gui.css3.property.BorderStyleMode;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public class Border
        implements IBorderAttributes, Serializable {

    private static final long serialVersionUID = 1L;

    BorderStyleMode style = BorderStyleMode.solid;
    LengthMeasure width;
    IColor color;
    boolean useCurrentColor = true;

    LengthMeasure radiusLeft;
    LengthMeasure radiusRight; // = left.

    @Override
    public BorderStyleMode getStyle() {
        return style;
    }

    @Override
    public void setStyle(BorderStyleMode style) {
        if (style == null)
            throw new NullPointerException("style");
        this.style = style;
    }

    @Override
    public LengthMeasure getWidth() {
        return width;
    }

    @Override
    public void setWidth(LengthMeasure width) {
        this.width = width;
    }

    @Override
    public IColor getColor() {
        return color;
    }

    @Override
    public void setColor(IColor color) {
        this.color = color;
    }

    @Override
    public boolean isUseCurrentColor() {
        return useCurrentColor;
    }

    @Override
    public void setUseCurrentColor(boolean useCurrentColor) {
        this.useCurrentColor = useCurrentColor;
    }

    @Override
    public LengthMeasure getRadius() {
        return getRadiusLeft();
    }

    @Override
    public void setRadius(LengthMeasure radius) {
        setRadiusLeft(radius);
        setRadiusRight(radius);
    }

    @Override
    public LengthMeasure getRadiusLeft() {
        return radiusLeft;
    }

    @Override
    public void setRadiusLeft(LengthMeasure radiusLeft) {
        this.radiusLeft = radiusLeft;
    }

    @Override
    public LengthMeasure getRadiusRight() {
        return radiusRight;
    }

    @Override
    public void setRadiusRight(LengthMeasure radiusRight) {
        this.radiusRight = radiusRight;
    }

}
