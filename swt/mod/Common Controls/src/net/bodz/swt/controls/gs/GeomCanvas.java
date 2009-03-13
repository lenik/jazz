package net.bodz.swt.controls.gs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;

/**
 * @TestBy GeomCanvasTest
 */
public class GeomCanvas extends Canvas {

    GeomSpace                       gspace;

    private List<PaintGeomListener> pgListeners;

    private static int              CSTYLE = SWT.NO_REDRAW_RESIZE
                                                   // | SWT.NO_BACKGROUND
                                                   // | SWT.NO_MERGE_PAINTS
                                                   | SWT.H_SCROLL
                                                   | SWT.V_SCROLL;

    private ScrollBar               hbar;
    private ScrollBar               vbar;
    int                             xoff;
    int                             yoff;

    public GeomCanvas(Composite parent, int style, GeomSpace space) {
        super(parent, style | CSTYLE);
        this.gspace = space;

        this.addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(ControlEvent e) {
                updateBounds();
            }
        });
        this.addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                doPaint(e);
            }
        });

        if ((hbar = getHorizontalBar()) != null) {
            hbar.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    hscroll(hbar.getSelection());
                }
            });
        }
        if ((vbar = getVerticalBar()) != null) {
            vbar.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    vscroll(vbar.getSelection());
                }
            });
        }
        updateBounds();
    }

    static int safeAdd;
    static {
        // scrollbar-width
        safeAdd = 40;
    }

    public GeomSpace getGeomSpace() {
        return gspace;
    }

    public void setGeomSpace(GeomSpace gspace) {
        this.gspace = gspace;
        updateBounds();
    }

    void updateBounds() {
        if (gspace == null)
            return;
        Rectangle bounds = gspace.getBounds();
        Point size = getSize();
        boolean useh = xoff != 0 || bounds.width > size.x;
        boolean usev = yoff != 0 || bounds.height > size.y;
        // this.setSize(bounds.width, bounds.height);
        if (hbar != null) {
            hbar.setVisible(useh);
            if (useh)
                hbar.setMaximum(bounds.width - size.x + safeAdd);
        }
        if (vbar != null) {
            vbar.setVisible(usev);
            if (usev)
                vbar.setMaximum(bounds.height - size.y + safeAdd);
        }
    }

    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
        Rectangle bounds = gspace.getBounds();
        return new Point(bounds.width, bounds.height);
    }

    public void addPaintGeomListener(PaintGeomListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        if (pgListeners == null)
            pgListeners = new ArrayList<PaintGeomListener>(1);
        pgListeners.add(listener);
    }

    public void removePaintGeomListener(PaintGeomListener listener) {
        pgListeners.remove(listener);
    }

    private static Random random = new Random();

    void doPaint(PaintEvent e) {
        if (gspace == null)
            return;
        int vx0 = e.x;
        int vy0 = e.y;
        int vx1 = vx0 + e.width;
        int vy1 = vy0 + e.height;
        Iterator<Integer> it = gspace.iterator(//
                xoff + vx0, yoff + vy0, xoff + vx1, yoff + vy1);
        // List<Integer> geoms = new ArrayList<Integer>();
        while (it.hasNext()) {
            int geomIndex = it.next();
            // geoms.add(geomIndex);
            PaintGeomEvent e2 = new PaintGeomEvent(e, this, geomIndex);
            if (pgListeners == null) {
                // default to show the rectangular boxes.
                GC gc = e.gc;
                Rectangle rect = gspace.getBound(geomIndex);
                rect.x -= xoff;
                rect.y -= yoff;
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);
                Color bgColor = new Color(getDisplay(), red, green, blue);
                // Color bg0 = gc.getBackground();
                gc.setBackground(bgColor);
                gc.fillRectangle(rect);
                gc.drawRectangle(rect);
                String str = String.valueOf(geomIndex);
                if (str.length() > 3)
                    str = str.substring(str.length() - 3);
                Point strExt = gc.textExtent(str);
                gc.drawText(str, //
                        rect.x + (rect.width - strExt.x) / 2, //
                        rect.y + (rect.height - strExt.y) / 2);
                // gc.setBackground(bg0);
            } else {
                for (PaintGeomListener l : pgListeners)
                    l.paintGeom(e2);
            }
        }
        // String info = "Pant(" + geoms.size() + "): " + geoms + ", " + e;
        // System.out.println(info);
    }

    public void redrawGeom(int index) {
        redrawGeom(index, false);
    }

    public void redrawGeom(int index, boolean all) {
        Rectangle b = gspace.getBound(index);
        int viewx = b.x - xoff;
        int viewy = b.y - yoff;
        redraw(viewx, viewy, b.width, b.height, all);
    }

    protected void hscroll(int x) {
        if (x == xoff) // SWT.DRAG, SWT.NONE
            return;
        Point view = getSize();
        int vieww = view.x;
        int viewh = view.y;
        if (x < xoff) {
            // scroll to left, move to right
            int dx = xoff - x;
            this.scroll(dx, 0, //
                    0, 0, vieww - dx, viewh, false);
        } else {
            // scroll to right, move to left
            int dx = x - xoff;
            this.scroll(0, 0, //
                    dx, 0, vieww - dx, viewh, false);
        }
        xoff = x;
    }

    protected void vscroll(int y) {
        if (y == yoff)
            return;
        Point view = getSize();
        int vieww = view.x;
        int viewh = view.y;
        if (y < yoff) {
            // scroll to up, move to down
            int dy = yoff - y;
            this.scroll(0, dy, //
                    0, 0, vieww, viewh - dy, false);
        } else {
            // scroll to down, move to up
            int dy = y - yoff;
            this.scroll(0, 0, //
                    0, dy, vieww, viewh - dy, false);
        }
        yoff = y;
    }

    @Override
    public Rectangle getClientArea() {
        Rectangle clientArea = super.getClientArea();
        clientArea.x += xoff;
        clientArea.y += yoff;
        System.out.println(clientArea);
        return clientArea;
    }

    public Point view2abs(int viewX, int viewY) {
        int x = xoff + viewX;
        int y = yoff + viewY;
        return new Point(x, y);
    }

    public int hittest(int viewX, int viewY) {
        Point p = view2abs(viewX, viewY);
        int find = gspace.find(p.x, p.y);
        return find;
    }

}
