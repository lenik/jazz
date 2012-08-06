package net.bodz.bas.geom_f.base;

import net.bodz.bas.geom_f.api.IExtendable2d;
import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.api.IShapeAmount2d;

public interface ICircle2d
        extends IShape2d, IShapeAmount2d, IExtendable2d {

    // -o IShape2d

    @Override
    ICircle2d clone();

    @Override
    Circle2d snapshot();

    @Override
    Circle2d snapshotConst();

    // -o IShapeAmount2d

    //

    float getRadius();

    void setRadius(float radius);

    float getDiameter();

    void setDiameter(float diameter);

}
