package net.bodz.bas.gui.draw_f.shape;

import net.bodz.bas.geom.spec1_f.Circle2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.sugar.Tooling;

public abstract class AbstractShape2d
        implements IShape2d {

    private static final long serialVersionUID = 1L;

    @Override
    public abstract AbstractShape2d clone();

    // -o IBoundingBall2d
    @Override
    public Circle2d getBoundingBall() {
        Rectangle2d bbox = getBoundingBox();
        return bbox.getBoundingBall();
    }

    // -o ITransformable2d

    // IDecoratable

    @Override
    public <T> T decorate(Class<T> decoratedType) {
        return new Tooling(this).getWrapper(decoratedType);
    }

}
