package net.bodz.bas.geom.spec0_f;

import java.io.Serializable;

import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawable2d;
import net.bodz.bas.sugar.IToChain;
import net.bodz.bas.t.object.ISnapShot;

public interface IPrimitive2d
        extends Serializable, //
        IPointSet2d, IPointRefSet2d, IPositionAttributes2d, //
        IPickable2d, //
        IBoundingBox2d, //
        IBoundingBall2d, //
        ITransformable2d, //
        IPolygonizable2d, //
        ICroppable2d, //
        ISnapShot, //
        IBaseDrawable2d, //
        IToChain {

    float EPSILON = 1e-7f;

    @Override
    public IPrimitive2d snap();

    @Override
    public IPrimitive2d shot();

    @Override
    public IPrimitive2d snapshot();

    /**
     * Test if empty, or negative sized.
     * 
     * @return <code>false</code> if the shape is empty, or negative sized.
     */
    boolean isValid();

    /**
     * Degenerate the shape if possible.
     * 
     * @return If the shape can be degenerated to a single point, return the point. Otherwise
     *         returns null.
     */
    Point2d degenerate();

    /**
     * Reduce to a simpler shape if possible.
     * 
     * @return Simpler reduced shape type. Returns <code>null</code> if this shape is empty.
     * @see #isValid()
     */
    IPrimitive2d reduce();

}
