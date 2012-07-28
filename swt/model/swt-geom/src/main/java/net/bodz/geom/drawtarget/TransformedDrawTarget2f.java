package net.bodz.geom.drawtarget;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.shape.base.IPoint2f;
import net.bodz.geom.transform.MatViewTransformer2f;
import net.bodz.geom.transform.Transformer2f;
import net.bodz.geom.transform.ViewTransformer2f;

public class TransformedDrawTarget2f
        extends AbstractDrawTarget2f_Point
        implements DelegateDrawTarget2f, ViewTransformer2f {

    DrawTarget2f delegatee;
    ViewTransformer2f matTransformmer;

    public TransformedDrawTarget2f(DrawTarget2f delegant, ViewTransformer2f vt) {
        assert delegant != null;
        this.delegatee = delegant;
        this.matTransformmer = vt;
    }

    public TransformedDrawTarget2f(DrawTarget2f delegant) {
        this(delegant, new MatViewTransformer2f());
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

    public void mul(Transformer2f suffix) {
        matTransformmer.mul(suffix);
    }

    public void mulBy(Transformer2f prefix) {
        matTransformmer.mulBy(prefix);
    }

    public void invert() {
        matTransformmer.invert();
    }

    public void transform(javax.vecmath.Vector2f vector) {
        matTransformmer.transform(vector);
    }

    public void transform(IPoint2f point) {
        matTransformmer.transform(point);
    }

    public Vector2f transformTo(javax.vecmath.Vector2f vector) {
        return matTransformmer.transformTo(vector);
    }

    public IPoint2f transformTo(IPoint2f point) {
        return matTransformmer.transformTo(point);
    }

    public void invert(javax.vecmath.Vector2f vector) {
        matTransformmer.invert(vector);
    }

    public void invert(IPoint2f point) {
        matTransformmer.invert(point);
    }

    public Vector2f invertTo(javax.vecmath.Vector2f vector) {
        return matTransformmer.invertTo(vector);
    }

    public IPoint2f invertTo(IPoint2f point) {
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
    public void drawCircle(IPoint2f center, float radiusX, float radiusY)
            throws DrawException {
        delegatee.drawCircle(transformTo(center), radiusX, radiusY);
    }

    @Override
    public void drawEllipse(IPoint2f p0, IPoint2f p1)
            throws DrawException {
        delegatee.drawEllipse(transformTo(p0), transformTo(p1));
    }

    @Override
    public void drawLine(IPoint2f start, IPoint2f end)
            throws DrawException {
        delegatee.drawLine(transformTo(start), transformTo(end));
    }

    @Override
    public void drawPixel(IPoint2f point)
            throws DrawException {
        delegatee.drawPixel(transformTo(point));
    }

    @Override
    public void drawPolygon(IPoint2f[] points)
            throws DrawException {
        IPoint2f[] pts = new IPoint2f[points.length];
        for (int i = 0; i < pts.length; i++)
            pts[i] = transformTo(points[i]);
        delegatee.drawPolygon(pts);
    }

    @Override
    public void drawRectangle(IPoint2f p0, IPoint2f p1)
            throws DrawException {
        delegatee.drawRectangle(transformTo(p0), transformTo(p1));
    }

    @Override
    public void drawTriangle(IPoint2f p0, IPoint2f p1, IPoint2f p2)
            throws DrawException {
        delegatee.drawTriangle(transformTo(p0), transformTo(p1), transformTo(p2));
    }

    @Override
    public void fillCircle(IPoint2f center, float radiusX, float radiusY)
            throws DrawException {
        delegatee.fillCircle(transformTo(center), radiusX, radiusY);
    }

    @Override
    public void fillEllipse(IPoint2f p0, IPoint2f p1)
            throws DrawException {
        delegatee.fillEllipse(transformTo(p0), transformTo(p1));
    }

    @Override
    public void fillPolygon(IPoint2f[] points)
            throws DrawException {
        IPoint2f[] pts = new IPoint2f[points.length];
        for (int i = 0; i < pts.length; i++)
            pts[i] = transformTo(points[i]);
        delegatee.fillPolygon(pts);
    }

    @Override
    public void fillRectangle(IPoint2f p0, IPoint2f p1)
            throws DrawException {
        delegatee.fillRectangle(transformTo(p0), transformTo(p1));
    }

    @Override
    public void fillTriangle(IPoint2f p0, IPoint2f p1, IPoint2f p2)
            throws DrawException {
        delegatee.fillTriangle(transformTo(p0), transformTo(p1), transformTo(p2));
    }

}
