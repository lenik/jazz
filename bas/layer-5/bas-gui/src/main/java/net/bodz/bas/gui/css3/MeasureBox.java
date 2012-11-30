package net.bodz.bas.gui.css3;

import java.io.Serializable;

import net.bodz.bas.i18n.unit.Measure;

public class MeasureBox
        implements Serializable {

    private static final long serialVersionUID = 1L;

    Measure top;
    Measure right;
    Measure bottom;
    Measure left;

    public Measure getTop() {
        return top;
    }

    public void setTop(Measure top) {
        this.top = top;
    }

    public Measure getRight() {
        return right;
    }

    public void setRight(Measure right) {
        this.right = right;
    }

    public Measure getBottom() {
        return bottom;
    }

    public void setBottom(Measure bottom) {
        this.bottom = bottom;
    }

    public Measure getLeft() {
        return left;
    }

    public void setLeft(Measure left) {
        this.left = left;
    }

}
