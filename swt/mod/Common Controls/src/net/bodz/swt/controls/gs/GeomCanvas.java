package net.bodz.swt.controls.gs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.types.ints.IntIterable;
import net.bodz.bas.types.ints.IntIterator;
import net.bodz.swt.nls.ControlsNLS;

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
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;

/**
 * @test GeomCanvasTest
 */
public class GeomCanvas extends Canvas {

    private GeomSpace               gspace;
    private boolean                 paintBg;

    private ScrollBar               hbar;
    private ScrollBar               vbar;
    private Point                   viewOffset;
    private int                     xoffMax;
    private int                     yoffMax;

    private List<PaintGeomListener> pgListeners;

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
        super(parent, style | SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND);
        this.gspace = space;
        this.paintBg = 0 == (style & SWT.NO_BACKGROUND);
        this.viewOffset = new Point(0, 0);

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
                    throw new IllegalStateException(ControlsNLS.getString("GeomCanvas.noReentrant")); //$NON-NLS-1$
                painting = true;
                Rectangle viewRect = new Rectangle(e.x, e.y, e.width, e.height);
                paintImpl(e.gc, viewRect, viewOffset);
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
                    int newoff = viewOffset.x + e.count * getWheelScale().x;
                    if (newoff < 0)
                        newoff = 0;
                    if (newoff > xoffMax)
                        newoff = xoffMax;
                    if (hbar != null)
                        hbar.setSelection(newoff);
                    else
                        hscroll(newoff);
                } else {
                    int newoff = viewOffset.y + e.count * getWheelScale().y;
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
        Rectangle geomBounds = gspace.getBounds();
        Point canvasSize = getSize();
        boolean useh = viewOffset.x != 0 || geomBounds.width > canvasSize.x;
        boolean usev = viewOffset.y != 0 || geomBounds.height > canvasSize.y;
        // this.setSize(bounds.width, bounds.height);
        xoffMax = geomBounds.width - canvasSize.x + safeAdd;
        yoffMax = geomBounds.height - canvasSize.y + safeAdd;
        if (xoffMax < 0)
            xoffMax = 0;
        if (yoffMax < 0)
            yoffMax = 0;
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

    /**
     * The background of concrete geom components are not included in the global
     * background, for geom with non-rectangular shapes, implementations of
     * {@link PaintGeomListener#paintGeom(PaintGeomEvent)} must take consider of
     * {@link #isPaintBg() paintBg} to decide whether to paint the geom's
     * background. For most cases, call {@link #paintBackground(GC, Rectangle)}
     * with the component's {@link GeomSpace#getBound(int) bounds} is just fine.
     */
    public void addPaintGeomListener(PaintGeomListener listener) {
        if (listener == null)
            throw new NullPointerException("listener"); //$NON-NLS-1$
        if (pgListeners == null)
            pgListeners = new ArrayList<PaintGeomListener>(1);
        pgListeners.add(listener);
    }

    public void removePaintGeomListener(PaintGeomListener listener) {
        pgListeners.remove(listener);
    }

    public boolean isPaintBg() {
        return paintBg;
    }

    public void setPaintBg(boolean paintBg) {
        if (this.paintBg != paintBg) {
            this.paintBg = paintBg;
            if (paintBg)
                redraw();
        }
    }

    /** used in default painting */
    private static Random  random    = new Random();

    private static boolean paintStat = false;

    protected GRegion createGRegion(Rectangle rect) {
        SWTGRegion region = new SWTGRegion();
        region.add(rect);
        return region;
    }

    protected void paintBackground(GC gc, Region region) {
        Rectangle bounds = region.getBounds();
        paintBackground(gc, bounds);
    }

    protected void paintBackground(GC gc, Rectangle bounds) {
        Color bgColor = getBackground();
        // bgColor = new Color(gc.getDevice(), 255, 0, 0);
        gc.setBackground(bgColor);
        gc.fillRectangle(bounds);
        bgColor.dispose();
    }

    // protected void
    private void paintImpl(GC gc, Rectangle viewRect, Point viewOffset) {
        if (gspace == null)
            return;
        Rectangle geomBounds = null;
        if (viewRect == null) {
            Rectangle bounds = gspace.getBounds();
            viewRect = new Rectangle(0, 0, bounds.height, bounds.height);
            // keep geomBounds == null, to iterate ALL geoms.
        } else {
            geomBounds = new Rectangle(//
                    viewRect.x + viewOffset.x, viewRect.y + viewOffset.y, //
                    viewRect.width, viewRect.height);
        }
        IntIterable geoms = gspace.find(geomBounds);
        IntIterator geomsIter;

        if (paintStat) {
            BCharOut buf = null;
            int n = 0;
            geomsIter = geoms.iterator();
            while (geomsIter.hasNext()) {
                int geom = geomsIter.next();
                if (buf == null)
                    buf = new BCharOut();
                else
                    buf.print(", "); //$NON-NLS-1$
                buf.print(geom);
                n++;
            }
            // System.out.println(e);
            System.out.print("    Pant(" + n + "): "); //$NON-NLS-1$ //$NON-NLS-2$
            System.out.println(buf);
        }
        if (paintBg) {
            GRegion gregion = createGRegion(viewRect);
            geomsIter = geoms.iterator();
            while (geomsIter.hasNext()) {
                int geom = geomsIter.next();
                Rectangle geomRect = gspace.getBound(geom);
                geomRect.x -= viewOffset.x;
                geomRect.y -= viewOffset.y;
                gregion.subtract(geomRect);
            }
            Region region = gregion.toRegion();
            gc.setClipping(region);
            paintBackground(gc, region);
            gc.setClipping((Region) null);
            gregion.dispose();
        }

        geomsIter = geoms.iterator();
        while (geomsIter.hasNext()) {
            int geom = geomsIter.next();
            Rectangle geomRect = gspace.getBound(geom);
            geomRect.x -= viewOffset.x;
            geomRect.y -= viewOffset.y;
            if (pgListeners == null) {
                // default to show the rectangular boxes.
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);
                Color bgColor = new Color(getDisplay(), red, green, blue);
                // Color bg0 = gc.getBackground();
                gc.setBackground(bgColor);
                gc.fillRectangle(geomRect);
                gc.drawRectangle(geomRect);
                String str = String.valueOf(geom);
                if (str.length() > 3)
                    str = str.substring(str.length() - 3);
                Point strExt = gc.textExtent(str);
                gc.drawText(str, //
                        geomRect.x + (geomRect.width - strExt.x) / 2, //
                        geomRect.y + (geomRect.height - strExt.y) / 2);
                // gc.setBackground(bg0);
            } else {
                PaintGeomEvent paintGeomEvent = new PaintGeomEvent(this, gc, geom, geomRect);
                for (PaintGeomListener l : pgListeners)
                    l.paintGeom(paintGeomEvent);
            }
        }
    }

    public void redrawGeom(int index) {
        redrawGeom(index, false);
    }

    public void redrawGeom(int index, boolean all) {
        Rectangle geomRect = gspace.getBound(index);
        int viewx = geomRect.x - viewOffset.x;
        int viewy = geomRect.y - viewOffset.y;
        redraw(viewx, viewy, geomRect.width, geomRect.height, all);
    }

    public void drawTo(GC gc) {
        drawTo(gc, null);
    }

    public void drawTo(GC gc, Rectangle clipRect) {
        paintImpl(gc, clipRect, new Point(0, 0));
    }

    protected void hscroll(int x) {
        if (x == viewOffset.x) // SWT.DRAG, SWT.NONE
            return;
        int dx = viewOffset.x - x;
        Point view = getSize();
        this.scroll(dx, 0, //
                0, 0, view.x, view.y, false);
        viewOffset.x = x;
    }

    protected void vscroll(int y) {
        if (y == viewOffset.y)
            return;
        int dy = viewOffset.y - y;
        Point view = getSize();
        this.scroll(0, dy, //
                0, 0, view.x, view.y, false);
        viewOffset.y = y;
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
        clientArea.x += viewOffset.x;
        clientArea.y += viewOffset.y;
        System.out.println(clientArea);
        return clientArea;
    }

    public Point toGlobal(int viewX, int viewY) {
        int x = viewOffset.x + viewX;
        int y = viewOffset.y + viewY;
        return new Point(x, y);
    }

    public Point toView(int x, int y) {
        int viewX = x - viewOffset.x;
        int viewY = y - viewOffset.y;
        return new Point(viewX, viewY);
    }

    public int hittest(int viewX, int viewY) {
        if (gspace == null)
            return -1;
        Point p = toGlobal(viewX, viewY);
        int find = gspace.find(p.x, p.y);
        return find;
    }

}
