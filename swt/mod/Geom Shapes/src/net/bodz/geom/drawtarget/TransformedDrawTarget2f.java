package net.bodz.geom.drawtarget;

import javax.vecmath.Vector2f;

import net.bodz.geom.shape.base.Point2f;
import net.bodz.geom.transform.MatViewTransformer2f;
import net.bodz.geom.transform.Transformer2f;
import net.bodz.geom.transform.ViewTransformer2f;

public class TransformedDrawTarget2f extends AbstractDrawTarget2f_Point implements
        DelegateDrawTarget2f, ViewTransformer2f {

    DrawTarget2f      delegatee;
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

    public void transform(Vector2f vector) {
        matTransformmer.transform(vector);
    }

    public void transform(Point2f point) {
        matTransformmer.transform(point);
    }

    public net.bodz.math.mat.Vector2f transformTo(Vector2f vector) {
        return matTransformmer.transformTo(vector);
    }

    public Point2f transformTo(Point2f point) {
        return matTransformmer.transformTo(point);
    }

    public void invert(Vector2f vector) {
        matTransformmer.invert(vector);
    }

    public void invert(Point2f point) {
        matTransformmer.invert(point);
    }

    public net.bodz.math.mat.Vector2f invertTo(Vector2f vector) {
        return matTransformmer.invertTo(vector);
    }

    public Point2f invertTo(Point2f point) {
        return matTransformmer.invertTo(point);
    }

    // -o ViewTransformmer

    public void rotate(float angle) {
        matTransformmer.rotate(angle);
    }

    public void scale(Vector2f kv) {
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

    public void translate(Vector2f dv) {
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
    public void drawCircle(Point2f center, float radiusX, float radiusY) throws DrawException {
        delegatee.drawCircle(transformTo(center), radiusX, radiusY);
    }

    @Override
    public void drawEllipse(Point2f p0, Point2f p1) throws DrawException {
        delegatee.drawEllipse(transformTo(p0), transformTo(p1));
    }

    @Override
    public void drawLine(Point2f start, Point2f end) throws DrawException {
        delegatee.drawLine(transformTo(start), transformTo(end));
    }

    @Override
    public void drawPixel(Point2f point) throws DrawException {
        delegatee.drawPixel(transformTo(point));
    }

    @Override
    public void drawPolygon(Point2f[] points) throws DrawException {
        Point2f[] pts = new Point2f[points.length];
        for (int i = 0; i < pts.length; i++)
            pts[i] = transformTo(points[i]);
        delegatee.drawPolygon(pts);
    }

    @Override
    public void drawRectangle(Point2f p0, Point2f p1) throws DrawException {
        delegatee.drawRectangle(transformTo(p0), transformTo(p1));
    }

    @Override
    public void drawTriangle(Point2f p0, Point2f p1, Point2f p2) throws DrawException {
        delegatee.drawTriangle(transformTo(p0), transformTo(p1), transformTo(p2));
    }

    @Override
    public void fillCircle(Point2f center, float radiusX, float radiusY) throws DrawException {
        delegatee.fillCircle(transformTo(center), radiusX, radiusY);
    }

    @Override
    public void fillEllipse(Point2f p0, Point2f p1) throws DrawException {
        delegatee.fillEllipse(transformTo(p0), transformTo(p1));
    }

    @Override
    public void fillPolygon(Point2f[] points) throws DrawException {
        Point2f[] pts = new Point2f[points.length];
        for (int i = 0; i < pts.length; i++)
            pts[i] = transformTo(points[i]);
        delegatee.fillPolygon(pts);
    }

    @Override
    public void fillRectangle(Point2f p0, Point2f p1) throws DrawException {
        delegatee.fillRectangle(transformTo(p0), transformTo(p1));
    }

    @Override
    public void fillTriangle(Point2f p0, Point2f p1, Point2f p2) throws DrawException {
        delegatee.fillTriangle(transformTo(p0), transformTo(p1), transformTo(p2));
    }

}
