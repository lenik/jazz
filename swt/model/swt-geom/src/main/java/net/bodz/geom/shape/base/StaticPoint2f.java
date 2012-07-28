package net.bodz.geom.shape.base;

import net.bodz.bas.c.javax.vecmath.Vector2f;

public class StaticPoint2f
        extends AbstractPoint2f {
    static final long serialVersionUID = -966532598582919425L;

    public float x;
    public float y;

    public StaticPoint2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public StaticPoint2f(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
    }

    public StaticPoint2f(IPoint2f point) {
        this.x = point.x();
        this.y = point.y();
    }

    public StaticPoint2f(StaticPoint2f point) {
        this.x = point.x;
        this.y = point.y;
    }

    public final float x() {
        return x;
    }

    public final float y() {
        return y;
    }

    @Override
    public final void x(float x) {
        this.x = x;
    }

    @Override
    public final void y(float y) {
        this.y = y;
    }

    @Override
    public final int pointCount() {
        return 1;
    }

    @Override
    public final IPoint2f pointRef(int index) {
        if (index == 0)
            return this;
        return null;
    }

    @Override
    public final StaticPoint2f clone() {
        return new StaticPoint2f(x, y);
    }

}