package net.bodz.geom.transform;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.shape.base.IPointRef2d;

public abstract class AbstractTransformer2f
        implements Transformer2f {

    public abstract void mul(Transformer2f suffix);

    public abstract void mulBy(Transformer2f prefix);

    public abstract void invert();

    public abstract void transform(javax.vecmath.Vector2f vector);

    public void transform(IPointRef2d point) {
        Vector2f v = new Vector2f(point.x(), point.y());
        transform(v);
        point.x(v.x);
        point.y(v.y);
    }

    public Vector2f transformTo(javax.vecmath.Vector2f vector) {
        Vector2f v = new Vector2f(vector);
        transform(v);
        return v;
    }

    public IPointRef2d transformTo(IPointRef2d point) {
        IPointRef2d p = point.clone();
        transform(p);
        return p;
    }

    public abstract void invert(javax.vecmath.Vector2f vector);

    public void invert(IPointRef2d point) {
        Vector2f v = new Vector2f(point.x(), point.y());
        invert(v);
        point.x(v.x);
        point.y(v.y);
    }

    public Vector2f invertTo(javax.vecmath.Vector2f vector) {
        Vector2f v = new Vector2f(vector);
        invert(v);
        return v;
    }

    public IPointRef2d invertTo(IPointRef2d point) {
        IPointRef2d p = point.clone();
        invert(p);
        return p;
    }
}
