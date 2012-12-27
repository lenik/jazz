package net.bodz.bas.gui.draw_f.dc;

import javax.vecmath.Vector2f;

import net.bodz.bas.geom.spec0_f.tr.ITransformedView2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedTransformedView2d
        extends AbstractDecorator<ITransformedView2d>
        implements ITransformedView2d {

    private static final long serialVersionUID = 1L;

    public DecoratedTransformedView2d(ITransformedView2d _orig) {
        super(_orig);
    }

    @Override
    public void invert() {
        getWrapped().invert();
    }

    @Override
    public void translate(float dx, float dy) {
        getWrapped().translate(dx, dy);
    }

    @Override
    public void transform(Vector2f vector) {
        getWrapped().transform(vector);
    }

    @Override
    public void translate(Vector2f dv) {
        getWrapped().translate(dv);
    }

    @Override
    public void scale(float k) {
        getWrapped().scale(k);
    }

    @Override
    public void scale(float kx, float ky) {
        getWrapped().scale(kx, ky);
    }

    @Override
    public void scaleAt(float k, Point2d basePoint) {
        getWrapped().scaleAt(k, basePoint);
    }

    @Override
    public void scaleAt(float kx, float ky, Point2d basePoint) {
        getWrapped().scaleAt(kx, ky, basePoint);
    }

    @Override
    public void rotate(float angle) {
        getWrapped().rotate(angle);
    }

    @Override
    public void transform(Point2d point) {
        getWrapped().transform(point);
    }

    @Override
    public void rotateAt(float angle, Point2d basePoint) {
        getWrapped().rotateAt(angle, basePoint);
    }

    @Override
    public void inverse(Vector2f vector) {
        getWrapped().inverse(vector);
    }

    @Override
    public void inverse(Point2d point) {
        getWrapped().inverse(point);
    }

}
