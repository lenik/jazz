package net.bodz.geom.util.gd;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.vecmath.Vector2f;

import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.Shape2f;
import net.bodz.geom.shape.base.Line2f;
import net.bodz.geom.shape.base.Point2f;
import net.bodz.geom.shape.base.Point2f.Static;
import net.bodz.geom.shape.base.Polygon2f;
import net.bodz.geom.shape.base.Rectangle2f;
import net.bodz.geom.shape.base.Triangle2f;

public class SWTShape2f
        implements Shape2f {

    protected Shape2f delegatee;

    public SWTStyle style;

    public SWTShape2f(Shape2f delegatee) {
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

    public Rectangle2f boundingBox() {
        return delegatee.boundingBox();
    }

    @Override
    public Shape2f clone() {
        return delegatee.clone();
    }

    public boolean contains(float x, float y) {
        return delegatee.contains(x, y);
    }

    public boolean contains(Point2f point) {
        return delegatee.contains(point);
    }

    public Shape2f convertTo(Class<?> shapeClass) {
        return delegatee.convertTo(shapeClass);
    }

    public Shape2f crop(float x0, float y0, float x1, float y1, float x2, float y2) {
        return delegatee.crop(x0, y0, x1, y1, x2, y2);
    }

    public Shape2f crop(float x0, float y0, float x1, float y1) {
        return delegatee.crop(x0, y0, x1, y1);
    }

    public Shape2f crop(Line2f directed) {
        return delegatee.crop(directed);
    }

    public Shape2f crop(Point2f baseHalfPlane, Vector2f normal) {
        return delegatee.crop(baseHalfPlane, normal);
    }

    public Shape2f crop(Polygon2f polygon) {
        return delegatee.crop(polygon);
    }

    public Shape2f crop(Rectangle2f rectangle) {
        return delegatee.crop(rectangle);
    }

    public Shape2f crop(Triangle2f triangle) {
        return delegatee.crop(triangle);
    }

    public float distance(float x, float y) {
        return delegatee.distance(x, y);
    }

    public float distance(Point2f point) {
        return delegatee.distance(point);
    }

    public Set<Class<?>> getConvertToClasses() {
        return delegatee.getConvertToClasses();
    }

    public Shape2f pick(float x, float y) {
        return delegatee.pick(x, y);
    }

    public Shape2f pick(Point2f point) {
        return delegatee.pick(point);
    }

    public PickInfo2f pickInfo(float x, float y) {
        return delegatee.pickInfo(x, y);
    }

    public PickInfo2f pickInfo(Point2f point) {
        return delegatee.pickInfo(point);
    }

    public Static point(int index) {
        return delegatee.point(index);
    }

    public int pointCount() {
        return delegatee.pointCount();
    }

    public Iterator<Static> pointIterator() {
        return delegatee.pointIterator();
    }

    public Point2f pointRef(int index) {
        return delegatee.pointRef(index);
    }

    public Iterator<Point2f> pointRefIterator() {
        return delegatee.pointRefIterator();
    }

    public float pointX(int index) {
        return delegatee.pointX(index);
    }

    public float pointY(int index) {
        return delegatee.pointY(index);
    }

    public void rotate(float angle, float baseX, float baseY) {
        delegatee.rotate(angle, baseX, baseY);
    }

    public void rotate(float angle, Point2f basePoint) {
        delegatee.rotate(angle, basePoint);
    }

    public void rotate(float angle) {
        delegatee.rotate(angle);
    }

    public void scale(float kx, float ky, float baseX, float baseY) {
        delegatee.scale(kx, ky, baseX, baseY);
    }

    public void scale(float k, float baseX, float baseY) {
        delegatee.scale(k, baseX, baseY);
    }

    public void scale(float kx, float ky, Point2f basePoint) {
        delegatee.scale(kx, ky, basePoint);
    }

    public void scale(float kx, float ky) {
        delegatee.scale(kx, ky);
    }

    public void scale(float k, Point2f basePoint) {
        delegatee.scale(k, basePoint);
    }

    public void scale(float k) {
        delegatee.scale(k);
    }

    public void scale(Vector2f kv, float baseX, float baseY) {
        delegatee.scale(kv, baseX, baseY);
    }

    public void scale(Vector2f kv, Point2f basePoint) {
        delegatee.scale(kv, basePoint);
    }

    public void scale(Vector2f kv) {
        delegatee.scale(kv);
    }

    public Shape2f snapshot() {
        return delegatee.snapshot();
    }

    public Static spoint(int id) {
        return delegatee.spoint(id);
    }

    public Point2f spointRef(int id) {
        return delegatee.spointRef(id);
    }

    public float spointX(int id) {
        return delegatee.spointX(id);
    }

    public float spointY(int id) {
        return delegatee.spointY(id);
    }

    public Point2f[] toPointRefsArray(boolean copy) {
        return delegatee.toPointRefsArray(copy);
    }

    public List<Point2f> toPointRefsList(boolean copy) {
        return delegatee.toPointRefsList(copy);
    }

    public Static[] toPointsArray(boolean copy) {
        return delegatee.toPointsArray(copy);
    }

    public List<Static> toPointsList(boolean copy) {
        return delegatee.toPointsList(copy);
    }

    public void translate(float dx, float dy) {
        delegatee.translate(dx, dy);
    }

    public void translate(Vector2f dv) {
        delegatee.translate(dv);
    }

}
