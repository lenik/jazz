package net.bodz.swt.draw.app;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.bas.geom_f.IShape2d;
import net.bodz.bas.geom_f.PickResult2d;
import net.bodz.bas.geom_f.base.ILine2d;
import net.bodz.bas.geom_f.base.IPointRef2d;
import net.bodz.bas.geom_f.base.IPolygon2d;
import net.bodz.bas.geom_f.base.IRectangle2d;
import net.bodz.bas.geom_f.base.ITriangle2d;
import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.swt.draw.dev.DrawException;
import net.bodz.swt.draw.dev.DrawTarget2f;

public class SWTShape2f
        implements IShape2d {

    protected IShape2d delegatee;

    public SWTStyle style;

    public SWTShape2f(IShape2d delegatee) {
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

    public IRectangle2d getBoundingBoxRef() {
        return delegatee.getBoundingBoxRef();
    }

    @Override
    public IShape2d clone() {
        return delegatee.clone();
    }

    public boolean contains(float x, float y) {
        return delegatee.contains(x, y);
    }

    public boolean contains(IPointRef2d point) {
        return delegatee.contains(point);
    }

    public IShape2d convertTo(Class<?> shapeClass) {
        return delegatee.convertTo(shapeClass);
    }

    public IShape2d crop(float x0, float y0, float x1, float y1, float x2, float y2) {
        return delegatee.crop(x0, y0, x1, y1, x2, y2);
    }

    public IShape2d crop(float x0, float y0, float x1, float y1) {
        return delegatee.crop(x0, y0, x1, y1);
    }

    public IShape2d crop(ILine2d directed) {
        return delegatee.crop(directed);
    }

    public IShape2d crop(IPointRef2d baseHalfPlane, Vector2f normal) {
        return delegatee.crop(baseHalfPlane, normal);
    }

    public IShape2d crop(IPolygon2d polygon) {
        return delegatee.crop(polygon);
    }

    public IShape2d crop(IRectangle2d rectangle) {
        return delegatee.crop(rectangle);
    }

    public IShape2d crop(ITriangle2d triangle) {
        return delegatee.crop(triangle);
    }

    public float getDistance(float x, float y) {
        return delegatee.getDistance(x, y);
    }

    public float distance(IPointRef2d point) {
        return delegatee.getDistance(point);
    }

    public Set<Class<?>> getConvertToClasses() {
        return delegatee.getConvertToClasses();
    }

    public IShape2d pick(float x, float y) {
        return delegatee.pick(x, y);
    }

    public IShape2d pick(IPointRef2d point) {
        return delegatee.pick(point);
    }

    public PickResult2d _pick(float x, float y) {
        return delegatee._pick(x, y);
    }

    public PickResult2d pickInfo(IPointRef2d point) {
        return delegatee._pick(point);
    }

    public Point2f getPoint(int index) {
        return delegatee.getPoint(index);
    }

    public int getPointCount() {
        return delegatee.getPointCount();
    }

    public Iterator<Point2f> getPointIterator() {
        return delegatee.getPointIterator();
    }

    public IPointRef2d getPointRef(int index) {
        return delegatee.getPointRef(index);
    }

    public Iterator<IPointRef2d> getPointRefIterator() {
        return delegatee.getPointRefIterator();
    }

    public float getPointX(int index) {
        return delegatee.getPointX(index);
    }

    public float getPointY(int index) {
        return delegatee.getPointY(index);
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

    public IShape2d snapshot() {
        return delegatee.snapshot();
    }

    public Point2d getPoint(int id) {
        return delegatee.getPoint(id);
    }

    public IPointRef2d getPointRef(int id) {
        return delegatee.getPointRef(id);
    }

    public float spointX(int id) {
        return delegatee.spointX(id);
    }

    public float spointY(int id) {
        return delegatee.spointY(id);
    }

    public IPointRef2d[] toPointRefArray(boolean copy) {
        return delegatee.toPointRefArray(copy);
    }

    public List<IPointRef2d> toPointRefList(boolean copy) {
        return delegatee.toPointRefList(copy);
    }

    public Point2f[] toPointArray(boolean copy) {
        return delegatee.toPointArray(copy);
    }

    public List<Point2d> toPointList(boolean copy) {
        return delegatee.toPointList(copy);
    }

    public void translate(float dx, float dy) {
        delegatee.translate(dx, dy);
    }

    public void translate(Vector2f dv) {
        delegatee.translate(dv);
    }

}
