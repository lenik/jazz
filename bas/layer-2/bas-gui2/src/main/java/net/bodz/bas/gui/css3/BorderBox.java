package net.bodz.bas.gui.css3;

import java.io.Serializable;

import net.bodz.bas.gui.css3.property.BorderStyleMode;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.i18n.unit.std.LengthMeasure;

public class BorderBox
        implements Serializable, IBorderAttributes {

    private static final long serialVersionUID = 1L;

    Border top;
    Border right;
    Border bottom;
    Border left;

    public Border getTop() {
        return top;
    }

    public void setTop(Border top) {
        this.top = top;
    }

    public Border getRight() {
        return right;
    }

    public void setRight(Border right) {
        this.right = right;
    }

    public Border getBottom() {
        return bottom;
    }

    public void setBottom(Border bottom) {
        this.bottom = bottom;
    }

    public Border getLeft() {
        return left;
    }

    public void setLeft(Border left) {
        this.left = left;
    }

    /** ⇱ Implementaton Of {@link IBorderAttributes}. */
    ;

    public Border getFirstBorder() {
        if (top != null)
            return top;
        if (right != null)
            return right;
        if (bottom != null)
            return bottom;
        if (left != null)
            return left;
        return null;
    }

    @Override
    public BorderStyleMode getStyle() {
        Border border1 = getFirstBorder();
        if (border1 == null)
            return null;
        else
            return border1.getStyle();
    }

    @Override
    public void setStyle(BorderStyleMode style) {
        if (top != null)
            top.setStyle(style);
        if (right != null)
            right.setStyle(style);
        if (bottom != null)
            bottom.setStyle(style);
        if (left != null)
            left.setStyle(style);
    }

    @Override
    public LengthMeasure getWidth() {
        Border border1 = getFirstBorder();
        if (border1 == null)
            return null;
        else
            return border1.getWidth();
    }

    @Override
    public void setWidth(LengthMeasure width) {
        if (top != null)
            top.setWidth(width);
        if (right != null)
            right.setWidth(width);
        if (bottom != null)
            bottom.setWidth(width);
        if (left != null)
            left.setWidth(width);
    }

    @Override
    public IColor getColor() {
        Border border1 = getFirstBorder();
        if (border1 == null)
            return null;
        else
            return border1.getColor();
    }

    @Override
    public void setColor(IColor color) {
        if (top != null)
            top.setColor(color);
        if (right != null)
            right.setColor(color);
        if (bottom != null)
            bottom.setColor(color);
        if (left != null)
            left.setColor(color);
    }

    @Override
    public boolean isUseCurrentColor() {
        Border border1 = getFirstBorder();
        if (border1 == null)
            return false;
        else
            return border1.isUseCurrentColor();
    }

    @Override
    public void setUseCurrentColor(boolean useCurrentColor) {
        if (top != null)
            top.setUseCurrentColor(useCurrentColor);
        if (right != null)
            right.setUseCurrentColor(useCurrentColor);
        if (bottom != null)
            bottom.setUseCurrentColor(useCurrentColor);
        if (left != null)
            left.setUseCurrentColor(useCurrentColor);
    }

    @Override
    public LengthMeasure getRadius() {
        Border border1 = getFirstBorder();
        if (border1 == null)
            return null;
        else
            return border1.getRadius();
    }

    @Override
    public void setRadius(LengthMeasure radius) {
        if (top != null)
            top.setRadius(radius);
        if (right != null)
            right.setRadius(radius);
        if (bottom != null)
            bottom.setRadius(radius);
        if (left != null)
            left.setRadius(radius);
    }

    @Override
    public LengthMeasure getRadiusLeft() {
        Border border1 = getFirstBorder();
        if (border1 == null)
            return null;
        else
            return border1.getRadiusLeft();
    }

    @Override
    public void setRadiusLeft(LengthMeasure radiusLeft) {
        if (top != null)
            top.setRadiusLeft(radiusLeft);
        if (right != null)
            right.setRadiusLeft(radiusLeft);
        if (bottom != null)
            bottom.setRadiusLeft(radiusLeft);
        if (left != null)
            left.setRadiusLeft(radiusLeft);
    }

    @Override
    public LengthMeasure getRadiusRight() {
        Border border1 = getFirstBorder();
        if (border1 == null)
            return null;
        else
            return border1.getRadiusRight();
    }

    @Override
    public void setRadiusRight(LengthMeasure radiusRight) {
        if (top != null)
            top.setRadiusRight(radiusRight);
        if (right != null)
            right.setRadiusRight(radiusRight);
        if (bottom != null)
            bottom.setRadiusRight(radiusRight);
        if (left != null)
            left.setRadiusRight(radiusRight);
    }

}
