package net.bodz.bas.gui.spec0_f;

import net.bodz.bas.geom.spec1_f.Circle2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.sugar.Tooling;

public abstract class AbstractShape2dComponent
        implements IShape2dComponent {

    private static final long serialVersionUID = 1L;

    @Override
    public abstract AbstractShape2dComponent clone();

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
