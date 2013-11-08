package net.bodz.swt.c.control;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Monitor;

public class Mover {

    static final int PRECISION = 10000;

    private final Control control;
    private final Rectangle bounds;
    private int xOffset;
    private int yOffset;

    public Mover(Control control, Control parent) {
        this.control = control;

        if (parent == null || parent == control) {
            Monitor monitor = control.getDisplay().getPrimaryMonitor();
            bounds = monitor.getBounds();
        } else {
            bounds = parent.getBounds();
        }
    }

    public static Mover bind(Control control) {
        return new Mover(control, control.getParent());
    }

    public static Mover bind(Control control, Control parent) {
        return new Mover(control, parent);
    }

    public Mover offset(int x, int y) {
        this.xOffset = x;
        this.yOffset = y;
        return this;
    }

    public Mover offset(double u, double v) {
        this.xOffset = (int) (u * bounds.width);
        this.yOffset = (int) (v * bounds.height);
        return this;
    }

    public void center() {
        Point size = control.getSize();
        int x = bounds.x + (bounds.width - size.x) / 2 + xOffset;
        int y = bounds.y + (bounds.height - size.y) / 2 + yOffset;
        control.setLocation(x, y);
    }

    public void topLeft() {
        int x = bounds.x + xOffset;
        int y = bounds.y + yOffset;
        control.setLocation(x, y);
    }

    public void topRight() {
        Point size = control.getSize();
        int x = bounds.x + (bounds.width - size.x) + xOffset;
        int y = bounds.y + yOffset;
        control.setLocation(x, y);
    }

    public void bottomLeft() {
        Point size = control.getSize();
        int x = bounds.x + xOffset;
        int y = bounds.y + (bounds.height - size.y) + yOffset;
        control.setLocation(x, y);
    }

    public void bottomRight() {
        Point size = control.getSize();
        int x = bounds.x + (bounds.width - size.x) + xOffset;
        int y = bounds.y + (bounds.height - size.y) + yOffset;
        control.setLocation(x, y);
    }

    public void topLeftToCenter() {
        Point size = control.getSize();
        int x = bounds.x + bounds.width / 2 - size.x + xOffset;
        int y = bounds.y + bounds.height / 2 - size.y + yOffset;
        control.setLocation(x, y);
    }

    public void topRightToCenter() {
        Point size = control.getSize();
        int x = bounds.x + bounds.width / 2 + xOffset;
        int y = bounds.y + bounds.height / 2 - size.y + yOffset;
        control.setLocation(x, y);
    }

    public void bottomLeftToCenter() {
        Point size = control.getSize();
        int x = bounds.x + bounds.width / 2 - size.x + xOffset;
        int y = bounds.y + bounds.height / 2 + yOffset;
        control.setLocation(x, y);
    }

    public void bottomRightToCenter() {
        int x = bounds.x + bounds.width / 2 + xOffset;
        int y = bounds.y + bounds.height / 2 + yOffset;
        control.setLocation(x, y);
    }

}
