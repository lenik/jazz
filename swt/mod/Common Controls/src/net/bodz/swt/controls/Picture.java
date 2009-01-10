package net.bodz.swt.controls;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class Picture extends Canvas {

    private Image image;

    // private Image backBuffer;

    public Picture(Composite parent, int style) {
        super(parent, style);
        addPaintListener(new PaintListener() {
            @Override
            public void paintControl(PaintEvent e) {
                paint(e.gc, e.x, e.y, e.width, e.height, e);
            }
        });
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
        redraw();
    }

    protected void paint(GC gc, int x, int y, int width, int height,
            PaintEvent event) {
        if (image == null)
            return;
        Rectangle size = image.getBounds();
        Point resize = getSize();
        gc.drawImage(image, //
                0, 0, size.width, size.height, //
                0, 0, resize.x, resize.y);
    }

    boolean copyImage(Rectangle srcBounds, GC destGC, Rectangle destBounds) {
        assert destGC != null;
        assert destBounds != null;
        if (image == null)
            return false;
        if (srcBounds == null)
            srcBounds = image.getBounds();
        destGC.drawImage(image, srcBounds.x, srcBounds.y, srcBounds.width,
                srcBounds.height, destBounds.x, destBounds.y, destBounds.width,
                destBounds.height);
        return true;
    }

    boolean copyView(Rectangle viewBounds, GC destGC, Rectangle destBounds) {
        if (destGC == null)
            throw new NullPointerException("gc");
        if (destBounds == null)
            throw new NullPointerException("bounds");
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
        return copyImage(new Rectangle(cropX, cropY, cropWidth, copyHeight),
                destGC, destBounds);
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
                System.out.println("Paint");
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
