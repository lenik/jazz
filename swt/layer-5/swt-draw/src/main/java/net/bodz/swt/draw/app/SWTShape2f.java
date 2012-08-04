package net.bodz.swt.draw.app;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.base.ILineRef2f;
import net.bodz.geom.shape.base.IPointRef2d;
import net.bodz.geom.shape.base.IPolygon2f;
import net.bodz.geom.shape.base.IRectangle2f;
import net.bodz.geom.shape.base.ITriangle2f;
import net.bodz.geom.shape.base.StaticPoint2f;
import net.bodz.swt.draw.dev.DrawException;
import net.bodz.swt.draw.dev.DrawTarget2f;

public class SWTShape2f
        implements IShape2f {

    protected IShape2f delegatee;

    public SWTStyle style;

    public SWTShape2f(IShape2f delegatee) {
        assert delegatee != null;
        this.delegatee = delegatee;
    }

    public void draw(DrawTarget2f target)
            throws DrawException {
        if (style != null) {
            if (style.storke != null)
                target.setStroke(style.storke);
            if (style.color != null)
                target.setColor(style.color);
            if (style.fillColor != null)
                target.setFillColor(style.fillColor);
            if (style.fontColor != null)
                target.setFontColor(style.fontColor);
            if (style.pattern != null)
                target.setPattern(style.pattern);
            if (style.fillPattern != null)
                target.setFillPattern(style.fillPattern);
            if (style.font != null)
                target.setFont(style.font);
        }
        delegatee.draw(target);
    }

    // -o Shape Delegated

    public IRectangle2f boundingBox() {
        return delegatee.boundingBox();
    }

    @Override
    public IShape2f clone() {
        return delegatee.clone();
    }

    public boolean contains(float x, float y) {
        return delegatee.contains(x, y);
    }

    public boolean contains(IPointRef2d point) {
        return delegatee.contains(point);
    }

    public IShape2f convertTo(Class<?> shapeClass) {
        return delegatee.convertTo(shapeClass);
    }

    public IShape2f crop(float x0, float y0, float x1, float y1, float x2, float y2) {
        return delegatee.crop(x0, y0, x1, y1, x2, y2);
    }

    public IShape2f crop(float x0, float y0, float x1, float y1) {
        return delegatee.crop(x0, y0, x1, y1);
    }

    public IShape2f crop(ILineRef2f directed) {
        return delegatee.crop(directed);
    }

    public IShape2f crop(IPointRef2d baseHalfPlane, Vector2f normal) {
        return delegatee.crop(baseHalfPlane, normal);
    }

    public IShape2f crop(IPolygon2f polygon) {
        return delegatee.crop(polygon);
    }

    public IShape2f crop(IRectangle2f rectangle) {
        return delegatee.crop(rectangle);
    }

    public IShape2f crop(ITriangle2f triangle) {
        return delegatee.crop(triangle);
    }

    public float distance(float x, float y) {
        return delegatee.distance(x, y);
    }

    public float distance(IPointRef2d point) {
        return delegatee.distance(point);
    }

    public Set<Class<?>> getConvertToClasses() {
        return delegatee.getConvertToClasses();
    }

    public IShape2f pick(float x, float y) {
        return delegatee.pick(x, y);
    }

    public IShape2f pick(IPointRef2d point) {
        return delegatee.pick(point);
    }

    public PickInfo2f pickInfo(float x, float y) {
        return delegatee.pickInfo(x, y);
    }

    public PickInfo2f pickInfo(IPointRef2d point) {
        return delegatee.pickInfo(point);
    }

    public Point2f point(int index) {
        return delegatee.point(index);
    }

    public int pointCount() {
        return delegatee.pointCount();
    }

    public Iterator<Point2f> pointIterator() {
        return delegatee.pointIterator();
    }

    public IPointRef2d pointRef(int index) {
        return delegatee.pointRef(index);
    }

    public Iterator<IPointRef2d> pointRefIterator() {
        return delegatee.pointRefIterator();
    }

    public float pointX(int index) {
        return delegatee.pointX(index);
    }

    public float pointY(int index) {
        return delegatee.pointY(index);
    }

    public void rotateAt(float angle, float baseX, float baseY) {
        delegatee.rotateAt(angle, baseX, baseY);
    }

    public void rotate(float angle, IPointRef2d basePoint) {
        delegatee.rotateAt(angle, basePoint);
    }

    public void rotate(float angle) {
        delegatee.rotate(angle);
    }

    public void scaleAt(float kx, float ky, float baseX, float baseY) {
        delegatee.scaleAt(kx, ky, baseX, baseY);
    }

    public void scaleAt(float k, float baseX, float baseY) {
        delegatee.scaleAt(k, baseX, baseY);
    }

    public void scale(float kx, float ky, IPointRef2d basePoint) {
        delegatee.scaleAt(kx, ky, basePoint);
    }

    public void scale(float kx, float ky) {
        delegatee.scale(kx, ky);
    }

    public void scale(float k, IPointRef2d basePoint) {
        delegatee.scaleAt(k, basePoint);
    }

    public void scale(float k) {
        delegatee.scale(k);
    }

    public void scaleAt(Vector2f kv, float baseX, float baseY) {
        delegatee.scaleAt(kv, baseX, baseY);
    }

    public void scale(Vector2f kv, IPointRef2d basePoint) {
        delegatee.scaleAt(kv, basePoint);
    }

    public void scale(Vector2f kv) {
        delegatee.scale(kv);
    }

    public IShape2f snapshot() {
        return delegatee.snapshot();
    }

    public StaticPoint2f spoint(int id) {
        return delegatee.spoint(id);
    }

    public IPointRef2d spointRef(int id) {
        return delegatee.spointRef(id);
    }

    public float spointX(int id) {
        return delegatee.spointX(id);
    }

    public float spointY(int id) {
        return delegatee.spointY(id);
    }

    public IPointRef2d[] toPointRefsArray(boolean copy) {
        return delegatee.toPointRefsArray(copy);
    }

    public List<IPointRef2d> toPointRefsList(boolean copy) {
        return delegatee.toPointRefsList(copy);
    }

    public Point2f[] toPointsArray(boolean copy) {
        return delegatee.toPointsArray(copy);
    }

    public List<StaticPoint2f> toPointsList(boolean copy) {
        return delegatee.toPointsList(copy);
    }

    public void translate(float dx, float dy) {
        delegatee.translate(dx, dy);
    }

    public void translate(Vector2f dv) {
        delegatee.translate(dv);
    }

}
