package net.bodz.swt.widgets;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;

public class ViewportCanvas
        extends Canvas {

    private int width;
    private int height;
    private int x0;
    private int y0;

    private ScrollBar hbar;
    private ScrollBar vbar;

    public ViewportCanvas(Composite parent, int style) {
        super(parent, style);

    }

    public Point getViewOrig() {
        return new Point(x0, y0);
    }

    public void setViewOrig(Point orig) {
        this.x0 = orig.x;
        this.y0 = orig.y;
    }

    public Point getModelSize() {
        return new Point(width, height);
    }

    public void setModelSize(Point size) {
        this.width = size.x;
        this.height = size.y;
    }

}
