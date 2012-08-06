package net.bodz.swt.draw.dev;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.bas.geom_f.base.IPointRef2d;
import net.bodz.bas.geom_f.tr.MatViewTransformer2d;
import net.bodz.bas.geom_f.tr.ITransformer2d;
import net.bodz.bas.geom_f.tr.ViewTransformer2d;

public class TransformedDrawTarget2f
        extends AbstractDrawTarget2f_Point
        implements DelegateDrawTarget2f, ViewTransformer2d {

    DrawTarget2f delegatee;
    ViewTransformer2d matTransformmer;

    public TransformedDrawTarget2f(DrawTarget2f delegant, ViewTransformer2d vt) {
        assert delegant != null;
        this.delegatee = delegant;
        this.matTransformmer = vt;
    }

    public TransformedDrawTarget2f(DrawTarget2f delegant) {
        this(delegant, new MatViewTransformer2d());
    }

    // -o DelegatingDrawTarget_f

    public DrawTarget2f getDelegant() {
        return delegatee;
    }

    public void setDelegant(DrawTarget2f delegant) {
        assert delegant != null;
        this.delegatee = delegant;
    }

    // -o Transformmer

    public void mul(ITransformer2d suffix) {
        matTransformmer.mul(suffix);
    }

    public void mulBy(ITransformer2d prefix) {
        matTransformmer.mulBy(prefix);
    }

    public void invert() {
        matTransformmer.invert();
    }

    public void transform(javax.vecmath.Vector2f vector) {
        matTransformmer.transform(vector);
    }

    public void transform(IPointRef2d point) {
        matTransformmer.transform(point);
    }

    public Vector2f transformTo(javax.vecmath.Vector2f vector) {
        return matTransformmer.transformTo(vector);
    }

    public IPointRef2d transformTo(IPointRef2d point) {
        return matTransformmer.transformTo(point);
    }

    public void invert(javax.vecmath.Vector2f vector) {
        matTransformmer.invert(vector);
    }

    public void invert(IPointRef2d point) {
        matTransformmer.invert(point);
    }

    public Vector2f invertTo(javax.vecmath.Vector2f vector) {
        return matTransformmer.invertTo(vector);
    }

    public IPointRef2d invertTo(IPointRef2d point) {
        return matTransformmer.invertTo(point);
    }

    // -o ViewTransformmer

    public void rotate(float angle) {
        matTransformmer.rotate(angle);
    }

    public void scale(javax.vecmath.Vector2f kv) {
        matTransformmer.scale(kv);
    }

    public void scale(float kx, float ky) {
        matTransformmer.scale(kx, ky);
    }

    public void scaleX(float k) {
        matTransformmer.scaleX(k);
    }

    public void scaleY(float k) {
        matTransformmer.scaleY(k);
    }

    public void translate(float x, float y) {
        matTransformmer.translate(x, y);
    }

    public void translate(javax.vecmath.Vector2f dv) {
        matTransformmer.translate(dv);
    }

    public void translateX(float x) {
        matTransformmer.translateX(x);
    }

    public void translateY(float y) {
        matTransformmer.translateY(y);
    }

    // -o DrawTarget

    @Override
    public void drawCircle(IPointRef2d center, float radiusX, float radiusY)
            throws DrawException {
        delegatee.drawCircle(transformTo(center), radiusX, radiusY);
    }

    @Override
    public void drawEllipse(IPointRef2d p0, IPointRef2d p1)
            throws DrawException {
        delegatee.drawEllipse(transformTo(p0), transformTo(p1));
    }

    @Override
    public void drawLine(IPointRef2d start, IPointRef2d end)
            throws DrawException {
        delegatee.drawLine(transformTo(start), transformTo(end));
    }

    @Override
    public void drawPixel(IPointRef2d point)
            throws DrawException {
        delegatee.drawPixel(transformTo(point));
    }

    @Override
    public void drawPolygon(IPointRef2d[] points)
            throws DrawException {
        IPointRef2d[] pts = new IPointRef2d[points.length];
        for (int i = 0; i < pts.length; i++)
            pts[i] = transformTo(points[i]);
        delegatee.drawPolygon(pts);
    }

    @Override
    public void drawRectangle(IPointRef2d p0, IPointRef2d p1)
            throws DrawException {
        delegatee.drawRectangle(transformTo(p0), transformTo(p1));
    }

    @Override
    public void drawTriangle(IPointRef2d p0, IPointRef2d p1, IPointRef2d p2)
            throws DrawException {
        delegatee.drawTriangle(transformTo(p0), transformTo(p1), transformTo(p2));
    }

    @Override
    public void fillCircle(IPointRef2d center, float radiusX, float radiusY)
            throws DrawException {
        delegatee.fillCircle(transformTo(center), radiusX, radiusY);
    }

    @Override
    public void fillEllipse(IPointRef2d p0, IPointRef2d p1)
            throws DrawException {
        delegatee.fillEllipse(transformTo(p0), transformTo(p1));
    }

    @Override
    public void fillPolygon(IPointRef2d[] points)
            throws DrawException {
        IPointRef2d[] pts = new IPointRef2d[points.length];
        for (int i = 0; i < pts.length; i++)
            pts[i] = transformTo(points[i]);
        delegatee.fillPolygon(pts);
    }

    @Override
    public void fillRectangle(IPointRef2d p0, IPointRef2d p1)
            throws DrawException {
        delegatee.fillRectangle(transformTo(p0), transformTo(p1));
    }

    @Override
    public void fillTriangle(IPointRef2d p0, IPointRef2d p1, IPointRef2d p2)
            throws DrawException {
        delegatee.fillTriangle(transformTo(p0), transformTo(p1), transformTo(p2));
    }

}
