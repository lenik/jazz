package net.bodz.bas.geom_f.api;

import java.io.Serializable;

import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.bas.sugar.IDecoratable;

public interface IShape2d
        extends Serializable, //
        IPointSet2d, IPointRefSet2d, IPositionAttributes2d, //
        IPickable2d, //
        IBoundingBox2d, //
        IBoundingBall2d, //
        ISnapshotable2d, //
        ITransformable2d, //
        IPolygonizable2d, //
        ICroppable2d, //
        IDecoratable<Object> {

    float EPSILON = 1e-7f;

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
    IShape2d reduce();

}
