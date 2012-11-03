package net.bodz.bas.geom.spec0_f.tr;

import javax.vecmath.Vector2f;

import net.bodz.bas.geom.spec0_f.ITransformable2d;
import net.bodz.bas.geom.spec1_f.Point2d;

public abstract class AbstractTransformable2d
        implements ITransformable2d {

    @Override
    public void translate(Vector2f dv) {
        translate(dv.x, dv.y);
    }

    @Override
    public final void scale(float k) {
        scale(k, k);
    }

    @Override
    public final void scaleAt(float k, Point2d basePoint) {
        scaleAt(k, k, basePoint);
    }

}
