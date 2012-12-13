package net.bodz.bas.geom.spec0_f;

import java.util.Iterator;
import java.util.List;

import javax.vecmath.Vector2f;

import net.bodz.bas.geom.spec1_f.Circle2d;
import net.bodz.bas.geom.spec1_f.IPointRef2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.geom.spec1_f.Triangle2d;
import net.bodz.bas.t.model.AbstractDecorator;

public class DecoratedPrimitive2d
        extends AbstractDecorator<IPrimitive2d>
        implements IPrimitive2d {

    private static final long serialVersionUID = 1L;

    public DecoratedPrimitive2d(IPrimitive2d _orig) {
        super(_orig);
    }

    @Override
    public IPrimitive2d shot() {
        return getWrapped().shot();
    }

    @Override
    public IPrimitive2d snapshot() {
        return getWrapped().snapshot();
    }

    @Override
    public IPrimitive2d snap() {
        return getWrapped().snap();
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
    public IPrimitive2d reduce() {
        return getWrapped().reduce();
    }

    // -o IPointSet2d

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

    // -o IPointRefSet2d

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

    // -o IPositionAttributes2d

    @Override
    public Point2d getCenterPoint() {
        return getWrapped().getCenterPoint();
    }

    // -o ITransformable2d

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

    // -o IBoundingBox2d

    @Override
    public Rectangle2d getBoundingBox() {
        return getWrapped().getBoundingBox();
    }

    // -o IBoundingBall2d

    @Override
    public Circle2d getBoundingBall() {
        return getWrapped().getBoundingBall();
    }

    // -o IPickable2d

    @Override
    public PickResult2d _pick(float x, float y) {
        return getWrapped()._pick(x, y);
    }

    @Override
    public PickResult2d _pick(Point2d point) {
        return getWrapped()._pick(point);
    }

    @Override
    public IPrimitive2d pick(float x, float y) {
        return getWrapped().pick(x, y);
    }

    @Override
    public IPrimitive2d pick(Point2d point) {
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

    // -o IPolygonizable2d

    @Override
    public Polygon2d polygonize() {
        return getWrapped().polygonize();
    }

    @Override
    public Polygon2d polygonize(int minSegments, Float maxSegmentLength) {
        return getWrapped().polygonize(minSegments, maxSegmentLength);
    }

    // -o ICroppable2d

    @Override
    public IPrimitive2d crop(PositiveHalfPlane2d php, boolean detached) {
        return getWrapped().crop(php, detached);
    }

    @Override
    public IPrimitive2d crop(Rectangle2d rectangle, boolean detached) {
        return getWrapped().crop(rectangle, detached);
    }

    @Override
    public IPrimitive2d crop(Triangle2d triangle, boolean detached) {
        return getWrapped().crop(triangle, detached);
    }

    @Override
    public IPrimitive2d crop(Polygon2d convexPolygon, boolean detached) {
        return getWrapped().crop(convexPolygon, detached);
    }

    // -o IDecoratable

    @Override
    public <T> T decorate(Class<T> decoratedType) {
        return getWrapped().decorate(decoratedType);
    }

    @Override
    public String toString() {
        return getWrapped().toString();
    }

}
