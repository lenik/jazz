package net.bodz.swt.widgets;

import java.util.LinkedList;
import java.util.Queue;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

/**
 * just double buffer
 */
public class BufferedCanvas extends Canvas {

    private Image            buffer;
    private boolean          autoRefresh = true;
    private Queue<Rectangle> queue;

    public BufferedCanvas(Composite parent, int style) {
        super(parent, style);
        this.queue = new LinkedList<Rectangle>();
        addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(ControlEvent e) {
                BufferedCanvas This = BufferedCanvas.this;
                Point size = This.getSize();
                if (buffer == null)
                    buffer = new Image(getDisplay(), size.x, size.y);
                else {
                    Rectangle bounds = buffer.getBounds();
                    int w0 = bounds.width;
                    int h0 = bounds.height;
                    if (w0 < size.x || h0 < size.y) {
                        // expand theh buffer
                        Image resize = new Image(getDisplay(), size.x, size.y);
                        GC gc = new GC(resize);
                        gc.drawImage(buffer, 0, 0); // orig at top-left
                        gc.dispose();
                        // fill This.getBackground()...
                        This.redraw(w0, 0, size.x - w0, h0, true); // R
                        This.redraw(0, h0, size.x, size.y - h0, true); // B & BR
                        buffer = resize;
                    }
                }
            }
        });
        addPaintListener(new PaintListener() {
            /** should be synchronized already. */
            @Override
            public void paintControl(PaintEvent e) {
                _paint(e.gc, e.x, e.y, e.width, e.height);
            }
        });
    }

    public synchronized void submit(Rectangle area) {
        if (area == null)
            throw new NullPointerException("area"); //$NON-NLS-1$
        queue.add(area);
    }

    public synchronized void cancel() {
        queue.clear();
    }

    public boolean isAutoRefresh() {
        return autoRefresh;
    }

    public void setAutoRefresh(boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }

    public void refresh() {
        GC gc = new GC(this);
        try {
            refresh(gc);
        } finally {
            gc.dispose();
        }
    }

    void refresh(GC gc) {
        while (!queue.isEmpty()) {
            Rectangle dirty = queue.remove();
            gc.drawImage(buffer, //
                    dirty.x, dirty.y, dirty.width, dirty.height, //
                    dirty.x, dirty.y, dirty.width, dirty.height);
        }
    }

    void _paint(GC target, int x, int y, int width, int height) {
        assert buffer != null;
        GC bufferGC = new GC(buffer);
        try {
            paint(bufferGC, x, y, width, height);
        } finally {
            bufferGC.dispose();
        }
        if (autoRefresh)
            refresh(target);
    }

    int paintStat = 0;

    /**
     * Default draw a solid box to represent the invalidated area.
     */
    protected synchronized void paint(GC gc, int x, int y, int width, int height) {
        Device device = gc.getDevice();
        gc.setForeground(new Color(device, 0, 0, 0)); // white
        gc.setBackground(new Color(device, 255, 255, 255)); // dark blue
        gc.setLineStyle(SWT.LINE_DASHDOTDOT);
        gc.drawRectangle(x, y, width - 1, height - 1);
        gc.fillRectangle(x + 1, y + 1, width - 2, height - 2);
        paintStat++;
        gc.drawText(String.valueOf(paintStat), x + width / 2, y + height / 2);
        submit(new Rectangle(x, y, width, height));
    }

    public void redraw(Rectangle area, boolean invalidate) {
        if (invalidate) {
            if (area == null)
                super.redraw();
            else
                super.redraw(area.x, area.y, area.width, area.height, true);
        } else {
            GC gc = new GC(this);
            try {
                if (area == null) {
                    Point size = getSize();
                    _paint(gc, 0, 0, size.x, size.y);
                } else
                    _paint(gc, area.x, area.y, area.width, area.height);
            } finally {
                gc.dispose();
            }
        }
    }

}
