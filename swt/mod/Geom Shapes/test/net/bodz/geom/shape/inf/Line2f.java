package net.bodz.geom.shape.inf;

import net.bodz.geom.shape.Shape2f;
import net.bodz.geom.shape.base.Point2f;
import net.bodz.math.mat.Vector2f;

public interface Line2f extends Shape2f {

    int BIDIRECTIONAL = 0;
    int RADIAL        = 1;

    int SP_BASE       = 1;

    float baseX();

    float baseY();

    Point2f base();

    Vector2f direction();

}
