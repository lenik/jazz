package net.bodz.bas.gui.css3;

import java.io.Serializable;

import net.bodz.bas.gui.css3.property.BorderCollapseMode;

public class BorderBox
        implements Serializable {

    private static final long serialVersionUID = 1L;

    Border top;
    Border right;
    Border bottom;
    Border left;
    BorderCollapseMode collapse;

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

    public BorderCollapseMode getCollapse() {
        return collapse;
    }

    public void setCollapse(BorderCollapseMode collapse) {
        this.collapse = collapse;
    }

}
