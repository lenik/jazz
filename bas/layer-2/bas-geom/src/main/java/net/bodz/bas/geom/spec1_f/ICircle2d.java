package net.bodz.bas.geom.spec1_f;

import net.bodz.bas.geom.spec0_f.IExtendable2d;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.IShapeAmount2d;

public interface ICircle2d
        extends IPrimitive2d, IShapeAmount2d, IExtendable2d {

    // -o IShape2d

    @Override
    ICircle2d shot();

    @Override
    Circle2d snapshot();

    @Override
    Circle2d snap();

    // -o IShapeAmount2d

    //

    float getRadius();

    void setRadius(float radius);

    float getDiameter();

    void setDiameter(float diameter);

}
