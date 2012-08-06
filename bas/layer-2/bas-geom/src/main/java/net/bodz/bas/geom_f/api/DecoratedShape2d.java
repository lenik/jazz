package net.bodz.bas.geom_f.api;

import java.util.Iterator;
import java.util.List;

import javax.vecmath.Vector2f;

import net.bodz.bas.geom_f.base.*;
import net.bodz.bas.model.AbstractDecorator;

public class DecoratedShape2d
        extends AbstractDecorator<IShape2d>
        implements IShape2d {

    private static final long serialVersionUID = 1L;

    public DecoratedShape2d(IShape2d _orig) {
        super(_orig);
    }

    @Override
    public IShape2d crop(PositiveHalfPlane php) {
        return getWrapped().crop(php);
    }

    @Override
    public IShape2d crop(Rectangle2d rectangle) {
        return getWrapped().crop(rectangle);
    }

    @Override
    public IShape2d crop(Triangle2d triangle) {
        return getWrapped().crop(triangle);
    }

    @Override
    public IShape2d crop(Polygon2d convexPolygon) {
        return getWrapped().crop(convexPolygon);
    }

    @Override
    public Point2d getCenterPoint() {
        return getWrapped().getCenterPoint();
    }

    @Override
    public PickResult2d _pick(float x, float y) {
        return getWrapped()._pick(x, y);
    }

    @Override
    public PickResult2d _pick(Point2d point) {
        return getWrapped()._pick(point);
    }

    @Override
    public IShape2d pick(float x, float y) {
        return getWrapped().pick(x, y);
    }

    @Override
    public IShape2d pick(Point2d point) {
        return getWrapped().pick(point);
    }

    @Override
    public float distance(float x, float y) {
        return getWrapped().distance(x, y);
    }

    @Override
    public float distance(Point2d point) {
        return getWrapped().distance(point);
    }

    @Override
    public boolean contains(float x, float y) {
        return getWrapped().contains(x, y);
    }

    @Override
    public boolean contains(Point2d point) {
        return getWrapped().contains(point);
    }

    @Override
    public void translate(float dx, float dy) {
        getWrapped().translate(dx, dy);
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
    public void rotateAt(float angle, Point2d basePoint) {
        getWrapped().rotateAt(angle, basePoint);
    }

    @Override
    public Circle2d getBoundingBall() {
        return getWrapped().getBoundingBall();
    }

    @Override
    public IPointRef2d getPointRef(int index) {
        return getWrapped().getPointRef(index);
    }

    @Override
    public Iterable<IPointRef2d> getPointRefs() {
        return getWrapped().getPointRefs();
    }

    @Override
    public Iterator<IPointRef2d> getPointRefIterator() {
        return getWrapped().getPointRefIterator();
    }

    @Override
    public IPointRef2d[] toPointRefArray() {
        return getWrapped().toPointRefArray();
    }

    @Override
    public List<IPointRef2d> toPointRefList() {
        return getWrapped().toPointRefList();
    }

    @Override
    public int getPointCount() {
        return getWrapped().getPointCount();
    }

    @Override
    public Point2d getPoint(int index) {
        return getWrapped().getPoint(index);
    }

    @Override
    public float getPointX(int index) {
        return getWrapped().getPointX(index);
    }

    @Override
    public float getPointY(int index) {
        return getWrapped().getPointY(index);
    }

    @Override
    public Iterable<Point2d> getPoints() {
        return getWrapped().getPoints();
    }

    @Override
    public Iterator<Point2d> getPointIterator() {
        return getWrapped().getPointIterator();
    }

    @Override
    public Point2d[] toPointArray(boolean copy) {
        return getWrapped().toPointArray(copy);
    }

    @Override
    public List<Point2d> toPointList(boolean copy) {
        return getWrapped().toPointList(copy);
    }

    @Override
    public Rectangle2d getBoundingBox() {
        return getWrapped().getBoundingBox();
    }

    @Override
    public <T> T decorate(Class<T> decoratedType) {
        return getWrapped().decorate(decoratedType);
    }

    @Override
    public IShape2d snapshot() {
        return getWrapped().snapshot();
    }

    @Override
    public boolean isValid() {
        return getWrapped().isValid();
    }

    @Override
    public Point2d degenerate() {
        return getWrapped().degenerate();
    }

    @Override
    public IShape2d reduce() {
        return getWrapped().reduce();
    }

    @Override
    public IShape2d clone() {
        return getWrapped().clone();
    }

    @Override
    public String toString() {
        return getWrapped().toString();
    }

}
