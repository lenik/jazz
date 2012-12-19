package net.bodz.bas.geom.spec2_f;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.geom.spec0_f.AbstractPrimitive2d;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.PickResult2d;
import net.bodz.bas.geom.spec0_f.PositiveHalfPlane2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;

public class Composite2d
        extends AbstractPrimitive2d {

    private static final long serialVersionUID = 1L;

    List<IPrimitive2d> primitives;

    public Composite2d() {
        this(new ArrayList<IPrimitive2d>());
    }

    public Composite2d(List<IPrimitive2d> shapes) {
        assert shapes != null;
        this.primitives = shapes;
    }

    @Override
    public Composite2d shot() {
        List<IPrimitive2d> copy = new ArrayList<IPrimitive2d>(primitives.size());
        for (IPrimitive2d primitive : primitives) {
            IPrimitive2d shotdShape = primitive.shot();
            copy.add(shotdShape);
        }
        return new Composite2d(copy);
    }

    @Override
    public Composite2d snapshot() {
        List<IPrimitive2d> copy = new ArrayList<IPrimitive2d>(primitives.size());
        for (IPrimitive2d primitive : primitives) {
            IPrimitive2d shotdShape = primitive.snapshot();
            copy.add(shotdShape);
        }
        return new Composite2d(copy);
    }

    @Override
    public Composite2d snap() {
        List<IPrimitive2d> copy = new ArrayList<IPrimitive2d>(primitives.size());
        for (IPrimitive2d primitive : primitives) {
            IPrimitive2d shotdShape = primitive.snap();
            copy.add(shotdShape);
        }
        return new Composite2d(copy);
    }

    @Override
    public Point2d degenerate() {
        Point2d single = null;
        for (IPrimitive2d shape : primitives) {
            Point2d point = shape.degenerate();
            if (point == null)
                return null;
            if (single == null)
                single = point;
            else if (!single.equals(point)) // within epsilon = ...
                return null;
        }
        return single;
    }

    // -o IPointSet2d

    public int getPointCount() {
        return 0;
    }

    @Override
    public Point2d getPoint(int index) {
        return getBadPoint(index);
    }

    // -o IBoundingBox2d

    @Override
    public Rectangle2d getBoundingBox() {
        int n = primitives.size();
        if (n == 0)
            return null;

        Rectangle2d bbox = primitives.get(0).getBoundingBox();

        for (int i = 1; i < n; i++) {
            IPrimitive2d shape = primitives.get(i);
            Rectangle2d b = shape.getBoundingBox();
            bbox.include(b);
        }
        return bbox; // super.getBoundingBox();
    }

    // -o IPickable2d

    public static class CompPickInfo2f
            extends PickResult2d {
        public IPrimitive2d component;

        public CompPickInfo2f(IPrimitive2d pick, float distance, IPrimitive2d component) {
            super(pick, distance);
            this.component = component;
        }
    }

    @Override
    public PickResult2d _pick(Point2d point) {
        float minDistance = Float.MAX_VALUE;
        PickResult2d result = null;
        int n = primitives.size();
        IPrimitive2d component = null;

        for (int i = 0; i < n; i++) {
            IPrimitive2d sh = primitives.get(i);
            PickResult2d r = sh._pick(point);
            if (r.getDistance() < minDistance) {
                minDistance = r.getDistance();
                result = r;
                component = sh;
            }
        }
        if (result == null)
            return null;
        return new CompPickInfo2f(result.getShape(), result.getDistance(), component);
    }

    public IPrimitive2d find(float x, float y) {
        int n = primitives.size();
        for (int i = 0; i < n; i++) {
            IPrimitive2d shape = primitives.get(i);
            if (shape.contains(x, y))
                return shape;
        }
        return null;
    }

    @Override
    public float distance(Point2d point) {
        float minDistance = Float.MAX_VALUE;
        int n = primitives.size();
        for (int i = 0; i < n; i++) {
            IPrimitive2d shape = primitives.get(i);
            float dist = shape.distance(point);
            if (dist < minDistance) {
                minDistance = dist;
            }
        }
        return minDistance;
    }

    // -o ICroppable2d

    @Override
    public IPrimitive2d crop(PositiveHalfPlane2d php, boolean detached) {
        int shapeCount = primitives.size();
        for (int index = 0; index < shapeCount;) {
            IPrimitive2d shape = primitives.get(index);
            shape = shape.crop(php, detached);
            if (shape == null) {
                primitives.remove(index);
                shapeCount--;
            } else {
                index++;
            }
        }
        if (primitives.isEmpty())
            return null;
        else
            return this;
    }

    @Override
    public Polygon2d polygonize() {
        Polygon2d union = null;
        for (IPrimitive2d shape : primitives) {
            Polygon2d poly = shape.polygonize();
            if (poly == null)
                return null;
            if (union == null)
                union = poly;
            else {
                // 测试什么情况下两个 polygon 可以融合为一个，如果不能融合则立即失败并返回。
                if (!union.equals(poly)) // within epsilon = ...?
                    return null;
            }
        }
        return union;
    }

}
