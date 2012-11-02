package net.bodz.bas.geom_f.base;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.geom_f.api.AbstractShape2d;
import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.api.PickResult2d;
import net.bodz.bas.geom_f.api.PositiveHalfPlane;

public class CompositeShape2d
        extends AbstractShape2d {

    private static final long serialVersionUID = 1L;

    List<IShape2d> shapes;

    public CompositeShape2d() {
        this(new ArrayList<IShape2d>());
    }

    public CompositeShape2d(List<IShape2d> shapes) {
        assert shapes != null;
        this.shapes = shapes;
    }

    @Override
    public CompositeShape2d shot() {
        List<IShape2d> copy = new ArrayList<IShape2d>(shapes.size());
        for (IShape2d shape : shapes) {
            IShape2d shotdShape = shape.shot();
            copy.add(shotdShape);
        }
        return new CompositeShape2d(copy);
    }

    @Override
    public CompositeShape2d snapshot() {
        List<IShape2d> copy = new ArrayList<IShape2d>(shapes.size());
        for (IShape2d shape : shapes) {
            IShape2d shotdShape = shape.snapshot();
            copy.add(shotdShape);
        }
        return new CompositeShape2d(copy);
    }

    @Override
    public CompositeShape2d snap() {
        List<IShape2d> copy = new ArrayList<IShape2d>(shapes.size());
        for (IShape2d shape : shapes) {
            IShape2d shotdShape = shape.snap();
            copy.add(shotdShape);
        }
        return new CompositeShape2d(copy);
    }

    @Override
    public Point2d degenerate() {
        Point2d single = null;
        for (IShape2d shape : shapes) {
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
        int n = shapes.size();
        if (n == 0)
            return null;

        Rectangle2d bbox = shapes.get(0).getBoundingBox();

        for (int i = 1; i < n; i++) {
            IShape2d shape = shapes.get(i);
            Rectangle2d b = shape.getBoundingBox();
            bbox.include(b);
        }
        return bbox; // super.getBoundingBox();
    }

    // -o IPickable2d

    public static class CompPickInfo2f
            extends PickResult2d {
        public IShape2d component;

        public CompPickInfo2f(IShape2d pick, float distance, IShape2d component) {
            super(pick, distance);
            this.component = component;
        }
    }

    @Override
    public PickResult2d _pick(Point2d point) {
        float minDistance = Float.MAX_VALUE;
        PickResult2d result = null;
        int n = shapes.size();
        IShape2d component = null;

        for (int i = 0; i < n; i++) {
            IShape2d sh = shapes.get(i);
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

    public IShape2d find(float x, float y) {
        int n = shapes.size();
        for (int i = 0; i < n; i++) {
            IShape2d shape = shapes.get(i);
            if (shape.contains(x, y))
                return shape;
        }
        return null;
    }

    @Override
    public float distance(Point2d point) {
        float minDistance = Float.MAX_VALUE;
        int n = shapes.size();
        for (int i = 0; i < n; i++) {
            IShape2d shape = shapes.get(i);
            float dist = shape.distance(point);
            if (dist < minDistance) {
                minDistance = dist;
            }
        }
        return minDistance;
    }

    // -o ICroppable2d

    @Override
    public IShape2d crop(PositiveHalfPlane php, boolean detached) {
        int shapeCount = shapes.size();
        for (int index = 0; index < shapeCount;) {
            IShape2d shape = shapes.get(index);
            shape = shape.crop(php, detached);
            if (shape == null) {
                shapes.remove(index);
                shapeCount--;
            } else {
                index++;
            }
        }
        if (shapes.isEmpty())
            return null;
        else
            return this;
    }

    @Override
    public Polygon2d polygonize() {
        Polygon2d union = null;
        for (IShape2d shape : shapes) {
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
