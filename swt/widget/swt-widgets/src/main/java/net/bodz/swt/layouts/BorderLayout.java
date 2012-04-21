package net.bodz.swt.layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

/**
 * Modified version of swing2swt.layout.BorderLayout, fixed to fit the width of North/South, and the
 * height of West/East.
 */
public class BorderLayout
        extends Layout {

    public final static String CENTER = "Center"; //$NON-NLS-1$
    public final static String EAST = "East"; //$NON-NLS-1$
    public final static String NORTH = "North"; //$NON-NLS-1$
    public final static String SOUTH = "South"; //$NON-NLS-1$
    public final static String WEST = "West"; //$NON-NLS-1$

    private int hgap;
    private int vgap;

    private Control northChild;
    private Control southChild;
    private Control eastChild;
    private Control westChild;
    private Control centerChild;

    public BorderLayout() {
    }

    public BorderLayout(int hgap, int vgap) {
        this.hgap = hgap;
        this.vgap = vgap;
    }

    @Override
    protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
        readLayoutData(composite);
        Point size = new Point(0, 0);

        int maxWidth = 0;
        int maxHeight_WEC = 0; // max height about west/east/center

        Point pref;
        if (northChild != null) {
            pref = northChild.computeSize(wHint, SWT.DEFAULT, flushCache);
            maxWidth = Math.max(maxWidth, pref.x);
            size.y += pref.y + vgap;
        }
        if (southChild != null) {
            pref = southChild.computeSize(wHint, SWT.DEFAULT, flushCache);
            maxWidth = Math.max(maxWidth, pref.x);
            size.y += pref.y + vgap;
        }
        if (westChild != null) {
            pref = westChild.computeSize(SWT.DEFAULT, hHint, flushCache);
            size.x += pref.x + hgap;
            maxHeight_WEC = Math.max(maxHeight_WEC, pref.y);
        }
        if (eastChild != null) {
            pref = eastChild.computeSize(SWT.DEFAULT, hHint, flushCache);
            size.x += pref.x + hgap;
            maxHeight_WEC = Math.max(maxHeight_WEC, pref.y);
        }
        if (centerChild != null) {
            pref = centerChild.computeSize(wHint, hHint, flushCache);
            size.x += pref.x;
            maxHeight_WEC = Math.max(maxHeight_WEC, pref.y);
        }
        size.x = Math.max(size.x, maxWidth);
        size.y += maxHeight_WEC;
        return size;
    }

    @Override
    protected void layout(Composite composite, boolean flushCache) {
        readLayoutData(composite);
        Rectangle clientArea = composite.getClientArea();
        int top = clientArea.y;
        int bottom = clientArea.y + clientArea.height;
        int left = clientArea.x;
        int right = clientArea.x + clientArea.width;

        Point pref;
        if (northChild != null) {
            pref = northChild.computeSize(clientArea.width, SWT.DEFAULT, flushCache);
            northChild.setBounds(left, top, right - left, pref.y);
            top += pref.y + vgap;
        }
        if (southChild != null) {
            pref = southChild.computeSize(clientArea.width, SWT.DEFAULT, flushCache);
            southChild.setBounds(left, bottom - pref.y, right - left, pref.y);
            bottom -= pref.y + vgap;
        }
        if (westChild != null) {
            pref = westChild.computeSize(SWT.DEFAULT, bottom - top, flushCache);
            westChild.setBounds(left, top, pref.x, bottom - top);
            left += pref.x + hgap;
        }
        if (eastChild != null) {
            pref = eastChild.computeSize(SWT.DEFAULT, bottom - top, flushCache);
            eastChild.setBounds(right - pref.x, top, pref.x, bottom - top);
            right -= pref.x + hgap;
        }
        if (centerChild != null) {
            centerChild.setBounds(left, top, right - left, bottom - top);
        }
    }

    /**
     * Read the layout data of the children of a composite.
     * 
     * @param composite
     *            the parent composite
     */
    private void readLayoutData(Composite composite) {
        northChild = southChild = eastChild = westChild = centerChild = null;
        Control[] children = composite.getChildren();
        for (int i = 0; i < children.length; i++) {
            // if (!children[i].isVisible())
            // continue;
            Object layoutData = children[i].getLayoutData();
            if (NORTH.equals(layoutData))
                northChild = children[i];
            else if (SOUTH.equals(layoutData))
                southChild = children[i];
            else if (EAST.equals(layoutData))
                eastChild = children[i];
            else if (WEST.equals(layoutData))
                westChild = children[i];
            else
                centerChild = children[i];
        }
    }

    public int getHgap() {
        return hgap;
    }

    public void setHgap(int hgap) {
        this.hgap = hgap;
    }

    public int getVgap() {
        return vgap;
    }

    public void setVgap(int vgap) {
        this.vgap = vgap;
    }

}
