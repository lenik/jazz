package net.bodz.swt.layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public class LineLayout extends Layout {

    private boolean vertical;
    private int     gap = 4;

    public LineLayout() {
    }

    public LineLayout(boolean vertical) {
        this.vertical = vertical;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    @Override
    protected Point computeSize(Composite composite, int wHint, int hHint,
            boolean flushCache) {
        int lsize = 0;
        int hmax = 0;
        Control[] children = composite.getChildren();
        for (int i = 0; i < children.length; i++) {
            Control c = children[i];
            if (i != 0)
                lsize += gap;
            Point size = c.computeSize(SWT.DEFAULT, SWT.DEFAULT);
            lsize += size.x;
            if (size.y > hmax)
                hmax = size.y;
        }
        if (lsize == 0)
            lsize = SWT.DEFAULT;
        return new Point(lsize, hmax);
    }

    public static final int EXPAND = 1;
    public static final int FILL   = 2;

    @Override
    protected void layout(Composite composite, boolean flushCache) {
        Rectangle rect = composite.getClientArea();
        Control[] children = composite.getChildren();

        int wHint = rect.width, hHint = rect.height;
        Point pref = computeSize(composite, wHint, hHint, false);
        int lsize = pref.x;
        int hmax = pref.y;

        Point[] sizes = new Point[children.length];
        int used = gap * (children.length - 1);
        for (int i = 0; i < children.length; i++) {
            Control c = children[i];
            boolean expand = false;
            boolean fill = false;
            Object data = c.getLayoutData();
            if (data instanceof Integer) {
                int flags = (Integer) data;
                expand = 0 != (flags & EXPAND);
                fill = 0 != (flags & FILL);
            }
            Point size = c.computeSize(SWT.DEFAULT, SWT.DEFAULT);
            if (size.x < 0)
                size.x = 0;
            wHint -= size.x;
            assert size.y <= hmax;
            int h = fill ? hmax : size.y;
            if (expand)
                sizes[i] = new Point(-size.x, h);
            else {
                sizes[i] = new Point(size.x, h);
                used += size.x;
            }
        }
        int free = Math.max(0, rect.width - used);
        int ext = lsize - used;
        if (ext <= 0) // assert false
            ext = 1;

        int x = rect.x;
        int y = rect.y;
        for (int i = 0; i < children.length; i++) {
            Control c = children[i];
            Point size = sizes[i];
            if (i != 0)
                x += gap;
            if (size.x < 0)
                size.x = -size.x * free / ext;
            c.setBounds(x, y + (hmax - size.y) / 2, size.x, size.y);
            x += size.x;
        }
    }

}
