package net.bodz.bas.gui.css3;

import java.io.Serializable;

public class BorderBox
        implements Serializable {

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

}
