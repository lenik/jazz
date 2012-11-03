package net.bodz.bas.geom.spec0_f;

import net.bodz.bas.geom.spec1_f.Point2d;

public interface IExtendable2d {

    boolean include(float x, float y);

    boolean include(Point2d point);

    boolean include(IPrimitive2d shape);

}
