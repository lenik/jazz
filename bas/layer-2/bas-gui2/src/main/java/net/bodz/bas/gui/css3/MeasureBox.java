package net.bodz.bas.gui.css3;

import java.io.Serializable;

import net.bodz.bas.i18n.unit.std.LengthMeasure;

public class MeasureBox
        implements Serializable {

    private static final long serialVersionUID = 1L;

    LengthMeasure top;
    LengthMeasure right;
    LengthMeasure bottom;
    LengthMeasure left;

    public LengthMeasure getTop() {
        return top;
    }

    public void setTop(LengthMeasure top) {
        this.top = top;
    }

    public LengthMeasure getRight() {
        return right;
    }

    public void setRight(LengthMeasure right) {
        this.right = right;
    }

    public LengthMeasure getBottom() {
        return bottom;
    }

    public void setBottom(LengthMeasure bottom) {
        this.bottom = bottom;
    }

    public LengthMeasure getLeft() {
        return left;
    }

    public void setLeft(LengthMeasure left) {
        this.left = left;
    }

}
