package net.bodz.bas.geom_f.api;

import net.bodz.bas.geom_f.base.Point2d;

public interface IExtendable2d {

    boolean include(float x, float y);

    boolean include(Point2d point);

    boolean include(IShape2d shape);

}
