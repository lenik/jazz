package net.bodz.swt.director.layers;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import net.bodz.bas.meta.optim.Pure;

public interface Overlay
        extends Paintable {

    /**
     * @return byref
     */
    @Pure
    Rectangle getDefinedRegion();

    boolean isDefined(int x, int y);

    boolean isDefined(Point point);

    /**
     * @param bounds
     *            the paint operation must be limited in the region. otherwise, a
     *            upward-invalidation must be invoked.
     * 
     *            <code>null</code> means full-region and paint without limit.
     */
    void paint(GC gc, Rectangle bounds);

    void invalidate(Rectangle region);

    boolean isInvalid(Rectangle region);

    /**
     * @return undefined if <code>isInvalid(null)</code> returns <code>false</code>.
     */
    @Pure
    Rectangle getInvalidRegion();

    void addInvalidateListener(InvalidateListener listener);

    void removeInvalidateListener(InvalidateListener listener);

    /**
     * <pre>
     *  sprite = new sprite-overlay(bg)
     *  bg.paint
     *      
     *  sprite.mark         // save the empty bg.
     *  sprite.paint(0)     // draw some curve
     *  
     *  sprite.reset        // clear that curve, only.
     *  sprite.paint(1)     // next frame...
     * </pre>
     */
    boolean isMarkSupported();

    /**
     * @return never <code>null</code>
     */
    Snapshot mark(GC gc, Rectangle bounds);

    /**
     * @throws NullPointerException
     *             if snapshot is null.
     */
    void reset(GC gc, Snapshot snapshot);

}
