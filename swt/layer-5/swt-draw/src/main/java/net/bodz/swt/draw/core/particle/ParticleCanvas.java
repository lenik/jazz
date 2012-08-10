package net.bodz.swt.draw.core.particle;

import static net.bodz.swt.nls.ControlsNLS.ControlsNLS;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;

import net.bodz.bas.geom_f.base.Rectangle2d;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.util.ints.IntIterable;
import net.bodz.bas.util.ints.IntIterator;
import net.bodz.swt.draw.geom_i.IParticleBounds2i;
import net.bodz.swt.gui.dev.SWTRegion;
import net.bodz.swt.gui.geom.SWTShapes;

public class ParticleCanvas
        extends Canvas {

    private IParticleBounds2i bounds;
    private boolean paintBg;

    private ScrollBar hbar;
    private ScrollBar vbar;
    private Point viewOffset;
    private int xoffMax;
    private int yoffMax;

    private List<ParticlePaintListener> particlePaintListeners;

    public ParticleCanvas(Composite parent, IParticleBounds2i space) {
        this(parent, SWT.H_SCROLL | SWT.V_SCROLL, space);
    }

    /**
     * @see SWT#NO_BACKGROUND
     * @see SWT#NO_MERGE_PAINTS
     * @see SWT#H_SCROLL (recommend)
     * @see SWT#V_SCROLL (recommend)
     */
    public ParticleCanvas(Composite parent, int style, IParticleBounds2i space) {
        super(parent, style | SWT.NO_REDRAW_RESIZE | SWT.NO_BACKGROUND);
        this.bounds = space;
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
                    throw new IllegalStateException(ControlsNLS.getString("GeomCanvas.noReentrant"));
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

    public IParticleBounds2i getParticleBounds() {
        return bounds;
    }

    public void setParticleBounds(IParticleBounds2i bounds) {
        this.bounds = bounds;
        updateBounds();
        redraw();
    }

    void updateBounds() {
        if (bounds == null)
            return;

        Rectangle bbox = bounds.getBoundingBox();

        Point canvasSize = getSize();
        boolean useh = viewOffset.x != 0 || bbox.width > canvasSize.x;
        boolean usev = viewOffset.y != 0 || bbox.height > canvasSize.y;
        // this.setSize(bounds.width, bounds.height);
        xoffMax = bbox.width - canvasSize.x + safeAdd;
        yoffMax = bbox.height - canvasSize.y + safeAdd;
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
        if (bounds == null)
            return super.computeSize(wHint, hHint, changed);
        Rectangle bbox = bounds.getBoundingBox();
        return new Point(bbox.width, bbox.height);
    }

    /**
     * The background of concrete geom components are not included in the global background, for
     * geom with non-rectangular shapes, implementations of
     * {@link ParticlePaintListener#paint(ParticlePaintEvent)} must take consider of
     * {@link #isPaintBg() paintBg} to decide whether to paint the geom's background. For most
     * cases, call {@link #paintBackground(GC, Rectangle)} with the component's
     * {@link IParticleBounds2i#getBoundingBox(int) bounds} is just fine.
     */
    public void addPaintGeomListener(ParticlePaintListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        if (particlePaintListeners == null)
            particlePaintListeners = new ArrayList<ParticlePaintListener>(1);
        particlePaintListeners.add(listener);
    }

    public void removePaintGeomListener(ParticlePaintListener listener) {
        particlePaintListeners.remove(listener);
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
    private static Random random = new Random();

    private static boolean paintStat = false;

    protected SWTRegion createRegion(Rectangle2d rect) {
        SWTRegion region = new SWTRegion();
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
        if (bounds == null)
            return;
        Rectangle qbox = null;
        if (viewRect == null) {
            Rectangle bbox = bounds.getBoundingBox();
            viewRect = new Rectangle(0, 0, bbox.height, bbox.height);
            // keep geomBounds == null, to iterate ALL geoms.
        } else {
            qbox = new Rectangle(//
                    viewRect.x + viewOffset.x, viewRect.y + viewOffset.y, //
                    viewRect.width, viewRect.height);
        }

        IntIterable geoms = bounds.getParticleIndexes(qbox);
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
                    buf.print(", ");
                buf.print(geom);
                n++;
            }
            // System.out.println(e);
            System.out.print("    Pant(" + n + "): ");
            System.out.println(buf);
        }
        if (paintBg) {
            SWTRegion region = createRegion(SWTShapes.convert(viewRect));
            geomsIter = geoms.iterator();
            while (geomsIter.hasNext()) {
                int geom = geomsIter.next();
                Rectangle geomRect = bounds.getBoundingBox(geom);
                // geomRect.translate(-viewOffset.x, -viewOffset.y);
                geomRect.x -= viewOffset.x;
                geomRect.y -= viewOffset.y;
                region.remove(SWTShapes.convert(geomRect));
            }
            Region _region = region.getRegion();
            gc.setClipping(_region);
            paintBackground(gc, _region);
            gc.setClipping((Region) null);
            region.dispose();
        }

        geomsIter = geoms.iterator();
        while (geomsIter.hasNext()) {
            int geom = geomsIter.next();
            Rectangle bbox = bounds.getBoundingBox(geom);
            // _bbox.translate(-viewOffset.x, -viewOffset.y);
            bbox.x -= viewOffset.x;
            bbox.y -= viewOffset.y;
            if (particlePaintListeners == null) {
                // default to show the rectangular boxes.
                int red = random.nextInt(256);
                int green = random.nextInt(256);
                int blue = random.nextInt(256);
                Color bgColor = new Color(getDisplay(), red, green, blue);
                // Color bg0 = gc.getBackground();
                gc.setBackground(bgColor);
                gc.fillRectangle(bbox);
                gc.drawRectangle(bbox);
                String str = String.valueOf(geom);
                if (str.length() > 3)
                    str = str.substring(str.length() - 3);
                Point strExt = gc.textExtent(str);
                gc.drawText(str, //
                        bbox.x + (bbox.width - strExt.x) / 2, //
                        bbox.y + (bbox.height - strExt.y) / 2);
                // gc.setBackground(bg0);
            } else {
                ParticlePaintEvent paintGeomEvent = new ParticlePaintEvent(this, gc, geom, bbox);
                for (ParticlePaintListener l : particlePaintListeners)
                    l.paint(paintGeomEvent);
            }
        }
    }

    public void redrawGeom(int index) {
        redrawGeom(index, false);
    }

    public void redrawGeom(int index, boolean all) {
        Rectangle geomRect = bounds.getBoundingBox(index);
        int viewx = geomRect.x - viewOffset.x;
        int viewy = geomRect.y - viewOffset.y;
        int width = geomRect.width;
        int height = geomRect.height;
        redraw(viewx, viewy, width, height, all);
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

    public int hitTest(int viewX, int viewY) {
        if (bounds == null)
            return -1;
        Point p = toGlobal(viewX, viewY);
        int find = bounds.getParticleIndexAt(new Point(p.x, p.y));
        return find;
    }

}
