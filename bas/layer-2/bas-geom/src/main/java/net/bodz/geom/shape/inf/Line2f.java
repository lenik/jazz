package net.bodz.geom.shape.inf;

import javax.vecmath.Vector2f;

import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.base.IPointRef2d;

public interface Line2f
        extends IShape2f {

    int BIDIRECTIONAL = 0;
    int RADIAL = 1;

    int SP_BASE = 1;

    float baseX();

    float baseY();

    IPointRef2d base();

    Vector2f direction();

}
