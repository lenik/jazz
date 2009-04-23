package net.bodz.swt.presents.layers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class _Overlay implements Overlay {

    private boolean                  invalid;
    private Rectangle                invalidRegion;
    private List<InvalidateListener> invalidateListeners;

    public _Overlay() {
        invalid = true;
        invalidRegion = null;
    }

    @Override
    public boolean isDefined(int x, int y) {
        return getDefinedRegion().contains(x, y);
    }

    @Override
    public boolean isDefined(Point point) {
        return isDefined(point.x, point.y);
    }

    @Override
    public void paint(GC gc) {
        paint(gc, null);
    }

    @Override
    public void paint(GC gc, Rectangle region) {
        try {
            _paint(gc, region);
        } catch (InterruptedException e) {
            return;
        }
        invalidRegion = null;
    }

    /**
     * @throws InterruptedException
     *             可能是系统引起的，或者是因为设计的某个状态变量因为后来的 _paint 调用而取消先前的调用。
     */
    protected abstract void _paint(GC gc, Rectangle region) throws InterruptedException;

    @Override
    public void invalidate(Rectangle region) {
        if (region != null && !region.isEmpty())
            return;
        invalid = true;
        if (invalidRegion == null)
            invalidRegion = region;
        else
            invalidRegion.add(region);
        if (invalidateListeners != null && !invalidateListeners.isEmpty()) {
            InvalidateEvent event = new InvalidateEvent(this, region);
            for (InvalidateListener listener : invalidateListeners)
                listener.becameInvalid(event);
        }
    }

    @Override
    public boolean isInvalid(Rectangle region) {
        if (invalid) {
            if (invalidRegion == null)
                return true;
            if (invalidRegion.intersects(region))
                return true;
            return false;
        }
        return false;
    }

    @Override
    public Rectangle getInvalidRegion() {
        return invalidRegion;
    }

    @Override
    public synchronized void addInvalidateListener(InvalidateListener listener) {
        if (invalidateListeners == null)
            invalidateListeners = new ArrayList<InvalidateListener>();
        invalidateListeners.add(listener);
    }

    @Override
    public void removeInvalidateListener(InvalidateListener listener) {
        if (invalidateListeners != null) {
            invalidateListeners.remove(listener);
            if (invalidateListeners.isEmpty())
                invalidateListeners = null;
        }
    }

    /**
     * The default mark functionality is implemented by block-copy, so only
     * limited-region are supported.
     */
    @Override
    public boolean isMarkSupported() {
        Rectangle region = getDefinedRegion();
        return region != null;
    }

    @Override
    public Snapshot mark(GC gc, Rectangle bounds) {
        Rectangle defined = getDefinedRegion();
        assert defined != null;
        if (bounds == null)
            bounds = defined;
        else
            bounds = bounds.intersection(defined);
        Image saved = null;
        if (!bounds.isEmpty()) {
            int x = bounds.x;
            int y = bounds.y;
            int width = bounds.width;
            int height = bounds.height;
            saved = new Image(gc.getDevice(), width, height);
            gc.copyArea(saved, x, y);
        }
        return new Snapshot(bounds, saved);
    }

    @Override
    public void reset(GC gc, Snapshot snapshot) {
        if (snapshot == null)
            throw new NullPointerException("snapshot");
        if (snapshot.data == null)
            return;
        Rectangle bounds = snapshot.bounds;
        if (bounds.isEmpty())
            return;
        int x = bounds.x;
        int y = bounds.y;
        Image image = (Image) snapshot.data;
        gc.drawImage(image, x, y);
    }

}
