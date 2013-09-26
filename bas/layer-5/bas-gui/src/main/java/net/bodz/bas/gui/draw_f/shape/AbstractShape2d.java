package net.bodz.bas.gui.draw_f.shape;

import net.bodz.bas.geom.spec1_f.Circle2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.sugar.Tooling;

public abstract class AbstractShape2d
        implements IShape2d {

    private static final long serialVersionUID = 1L;

    @Override
    public abstract AbstractShape2d clone();

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IBoundingBall2d}. */
    ;

    @Override
    public Circle2d getBoundingBall() {
        Rectangle2d bbox = getBoundingBox();
        return bbox.getBoundingBall();
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.ITransformable2d}. */
    ;

    /**
     * ⇱ Implementaton Of {@link net.bodz.bas.sugar.IToChain}.
     */
    ;

    @Override
    public <T> T to(Class<T> clazz) {
        return new Tooling(this).getWrapper(clazz);
    }

}
