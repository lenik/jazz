package net.bodz.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @test {@link PictureTest}
 */
public class Picture extends Canvas {

    private Image   image;
    private boolean stretched;

    public Picture(Composite parent, int style) {
        this(parent, style, false);
    }

    public Picture(Composite parent, int style, boolean stretched) {
        super(parent, stretched ? style | SWT.NO_BACKGROUND : style);
        this.stretched = stretched;
        addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                paint(e);
            }
        });
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        if (this.image != image) {
            this.image = image;
            redraw();
        }
    }

    public boolean isStretched() {
        return stretched;
    }

    public void setStretched(boolean stretched) {
        if (this.stretched != stretched) {
            this.stretched = stretched;
            if (stretched)
                super.redraw();
        }
    }

    @Override
    public Point computeSize(int wHint, int hHint, boolean changed) {
        if (stretched || image == null)
            return super.computeSize(wHint, hHint, changed);
        Rectangle bounds = image.getBounds();
        return new Point(bounds.width, bounds.height);
    }

    protected void paint(PaintEvent e) {
        if (image == null)
            return;
        Rectangle size = image.getBounds();
        GC gc = e.gc;
        if (stretched) {
            Point resize = getSize();
            gc.drawImage(image, //
                    0, 0, size.width, size.height, //
                    0, 0, resize.x, resize.y);
        } else {
            if (e.x >= size.width || e.y >= size.height)
                return;
            int w = Math.max(0, Math.min(size.width - e.x, e.width));
            int h = Math.max(0, Math.min(size.height - e.y, e.height));
            gc.drawImage(image, e.x, e.y, w, h, e.x, e.y, w, h);
        }
    }

    boolean copyImage(Rectangle srcBounds, GC destGC, Rectangle destBounds) {
        assert destGC != null;
        assert destBounds != null;
        if (image == null)
            return false;
        if (srcBounds == null)
            srcBounds = image.getBounds();
        destGC.drawImage(image, srcBounds.x, srcBounds.y, srcBounds.width, srcBounds.height,
                destBounds.x, destBounds.y, destBounds.width, destBounds.height);
        return true;
    }

    boolean copyView(Rectangle viewBounds, GC destGC, Rectangle destBounds) {
        if (destGC == null)
            throw new NullPointerException("gc"); //$NON-NLS-1$
        if (destBounds == null)
            throw new NullPointerException("bounds"); //$NON-NLS-1$
        if (image == null)
            return false;
        if (viewBounds == null)
            return copyImage(null, destGC, destBounds);
        Rectangle bounds = image.getBounds();
        int imageWidth = bounds.width;
        int imageHeight = bounds.height;
        if (imageWidth == 0 || imageHeight == 0)
            return false;
        Point viewSize = getSize();
        int viewWidth = viewSize.x;
        int viewHeight = viewSize.y;
        if (viewWidth == 0 || viewHeight == 0)
            return false;
        int cropX = imageWidth * viewBounds.x / viewWidth;
        int cropY = imageHeight * viewBounds.y / viewHeight;
        int cropWidth = imageWidth * viewBounds.width / viewWidth;
        int copyHeight = imageHeight * viewBounds.height / viewHeight;
        return copyImage(new Rectangle(cropX, cropY, cropWidth, copyHeight), destGC, destBounds);
    }

    static Rectangle getBlockBounds(Composite outer, Control inner) {
        Point block = inner.getSize();
        int x = 0;
        int y = 0;
        Control parent = inner;
        while (parent != outer) {
            Point offset = inner.getLocation();
            x += offset.x;
            y += offset.y;
            parent = parent.getParent();
            if (parent == null)
                return null;
        }
        Rectangle bounds = new Rectangle(x, y, block.x, block.y);
        return bounds;
    }

    /**
     * @return <code>false</code> if control isn't a child of this composition.
     */
    public boolean paintThru(final Control control) {
        Rectangle blockBounds = getBlockBounds(this, control);
        if (blockBounds == null)
            return false;
        control.addPaintListener(new PaintListener() {

            @Override
            public void paintControl(PaintEvent e) {
                System.out.println("Paint"); //$NON-NLS-1$
                Picture pic = Picture.this;
                Rectangle viewBounds = getBlockBounds(pic, control);
                if (viewBounds == null)
                    return;
                // boolean picChanged = false;
                Point size = control.getSize();
                Rectangle destBounds = new Rectangle(0, 0, size.x, size.y);
                pic.copyView(viewBounds, e.gc, destBounds);
            }

        });
        return true;
    }

}
