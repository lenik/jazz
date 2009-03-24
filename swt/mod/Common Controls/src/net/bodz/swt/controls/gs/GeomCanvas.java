package net.bodz.swt.controls.gs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
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

    private ScrollBar               hbar;
    private ScrollBar               vbar;
    int                             xoff;
    int                             yoff;
    int                             xoffMax;
    int                             yoffMax;

    public GeomCanvas(Composite parent, GeomSpace space) {
        this(parent, SWT.H_SCROLL | SWT.V_SCROLL, space);
    }

    /**
     * @see SWT#NO_BACKGROUND
     * @see SWT#NO_MERGE_PAINTS
     * @see SWT#H_SCROLL (recommend)
     * @see SWT#V_SCROLL (recommend)
     */
    public GeomCanvas(Composite parent, int style, GeomSpace space) {
        super(parent, style | SWT.NO_REDRAW_RESIZE);
        this.gspace = space;

        this.addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(ControlEvent e) {
                updateBounds();
            }
        });
        this.addPaintListener(new PaintListener() {
            boolean painting;

            @Override
            public void paintControl(PaintEvent e) {
                if (painting)
                    throw new IllegalStateException("paint no reentrant");
                painting = true;
                doPaint(e);
                painting = false;
            }
        });

        if ((hbar = getHorizontalBar()) != null) {
            hbar.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    // System.out.println("H-scroll: " + e);
                    hscroll(hbar.getSelection());
                }
            });
        }
        if ((vbar = getVerticalBar()) != null) {
            vbar.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    // System.out.println("V-scroll: " + e);
                    vscroll(vbar.getSelection());
                }
            });
        }

        // canvas doesn't have focus, while mousewheel needs it.
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseDown(MouseEvent e) {
                forceFocus();
            }
        });
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseScrolled(MouseEvent e) {
                boolean shiftDown = 0 != (e.stateMask & SWT.SHIFT);
                if (shiftDown) {
                    int newoff = xoff + e.count * getWheelScale().x;
                    if (newoff < 0)
                        newoff = 0;
                    if (newoff > xoffMax)
                        newoff = xoffMax;
                    if (hbar != null)
                        hbar.setSelection(newoff);
                    else
                        hscroll(newoff);
                } else {
                    int newoff = yoff + e.count * getWheelScale().y;
                    if (newoff < 0)
                        newoff = 0;
                    if (newoff > yoffMax)
                        newoff = yoffMax;
                    if (vbar != null)
                        vbar.setSelection(newoff);
                    else
                        vscroll(newoff);
                }
            }
        });

        updateBounds();
    }

    private static final Point noScale = new Point(1, 1);

    protected Point getWheelScale() {
        return noScale;
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
        redraw();
    }

    void updateBounds() {
        if (gspace == null)
            return;
        Rectangle bounds = gspace.getBounds();
        Point size = getSize();
        boolean useh = xoff != 0 || bounds.width > size.x;
        boolean usev = yoff != 0 || bounds.height > size.y;
        // this.setSize(bounds.width, bounds.height);
        xoffMax = bounds.width - size.x + safeAdd;
        yoffMax = bounds.height - size.y + safeAdd;
        if (hbar != null) {
            hbar.setVisible(useh);
            if (useh)
                hbar.setMaximum(xoffMax);
        }
        if (vbar != null) {
            vbar.setVisible(usev);
            if (usev)
                vbar.setMaximum(yoffMax);
        }
    }

    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
        if (gspace == null)
            return super.computeSize(wHint, hHint, changed);
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

    private static Random  random    = new Random();
    private static boolean paintStat = false;

    void doPaint(PaintEvent e) {
        if (gspace == null)
            return;
        int vx0 = e.x;
        int vy0 = e.y;
        int vx1 = vx0 + e.width;
        int vy1 = vy0 + e.height;
        Iterator<Integer> it = gspace.iterator(//
                xoff + vx0, yoff + vy0, xoff + vx1, yoff + vy1);
        List<Integer> geoms = null;
        if (paintStat)
            geoms = new ArrayList<Integer>();
        while (it.hasNext()) {
            int geomIndex = it.next();
            if (paintStat)
                geoms.add(geomIndex);
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
        if (paintStat) {
            System.out.println(e);
            System.out.print("    Pant(" + geoms.size() + "): ");
            System.out.println(geoms);
        }
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
        int dx = xoff - x;
        Point view = getSize();
        this.scroll(dx, 0, //
                0, 0, view.x, view.y, false);
        xoff = x;
    }

    protected void vscroll(int y) {
        if (y == yoff)
            return;
        int dy = yoff - y;
        Point view = getSize();
        this.scroll(0, dy, //
                0, 0, view.x, view.y, false);
        yoff = y;
    }

    public Point getIncrement() {
        int dx = hbar == null ? 0 : hbar.getIncrement();
        int dy = vbar == null ? 0 : vbar.getIncrement();
        return new Point(dx, dy);
    }

    public void setIncrement(int dx, int dy) {
        if (hbar != null)
            hbar.setIncrement(dx);
        if (vbar != null)
            vbar.setIncrement(dy);
    }

    public Point getPageIncrement() {
        int dx = hbar == null ? 0 : hbar.getPageIncrement();
        int dy = vbar == null ? 0 : vbar.getPageIncrement();
        return new Point(dx, dy);
    }

    public void setPageIncrement(int dx, int dy) {
        if (hbar != null)
            hbar.setPageIncrement(dx);
        if (vbar != null)
            vbar.setPageIncrement(dy);
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
        if (gspace == null)
            return -1;
        Point p = view2abs(viewX, viewY);
        int find = gspace.find(p.x, p.y);
        return find;
    }

}
