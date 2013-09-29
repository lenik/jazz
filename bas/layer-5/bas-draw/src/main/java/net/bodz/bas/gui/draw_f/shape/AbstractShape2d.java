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
    /* _____________________________ */static section.iface __BBALL__;

    @Override
    public Circle2d getBoundingBall() {
        Rectangle2d bbox = getBoundingBox();
        return bbox.getBoundingBall();
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.ITransformable2d}. */
    /* _____________________________ */static section.iface __TRANSFORM__;

    /**
     * ⇱ Implementaton Of {@link net.bodz.bas.sugar.IToChain}.
     */
    /* _____________________________ */static section.iface __TO__;

    @Override
    public <T> T to(Class<T> clazz) {
        return new Tooling(this).getWrapper(clazz);
    }

}
