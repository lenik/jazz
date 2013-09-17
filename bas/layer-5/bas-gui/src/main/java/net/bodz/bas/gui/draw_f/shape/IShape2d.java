package net.bodz.bas.gui.draw_f.shape;

import java.io.Serializable;

import net.bodz.bas.geom.spec0_f.IBoundingBall2d;
import net.bodz.bas.geom.spec0_f.IBoundingBox2d;
import net.bodz.bas.geom.spec0_f.IPositionAttributes2d;
import net.bodz.bas.geom.spec0_f.ITransformable2d;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawable2d;
import net.bodz.bas.sugar.IDecoratable;
import net.bodz.bas.t.object.ICloneable;

public interface IShape2d
        extends Serializable, //
        ICloneable, //
        IPositionAttributes2d, //
        // IPickable2d, //
        IBoundingBox2d, //
        IBoundingBall2d, //
        ITransformable2d, //
        // ICroppable2d, //
        IBaseDrawable2d, //
        IDecoratable<Object> {

    @Override
    IShape2d clone();

}
