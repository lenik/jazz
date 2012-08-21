package net.bodz.swt.draw.app;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.geom_f.api.AbstractShape2d;
import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.api.PickResult2d;
import net.bodz.bas.geom_f.api.PositiveHalfPlane;
import net.bodz.bas.geom_f.base.IPointRef2d;
import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.bas.geom_f.base.Rectangle2d;
import net.bodz.bas.gui.dev.GraphicsOperationException;
import net.bodz.bas.gui.dev.IDrawContext2d;

public class GDShapes2f
        extends AbstractShape2d {

    List<SWTShape2d> shapes;

    public GDShapes2f(List<SWTShape2d> shapes, boolean copy) {
        assert shapes != null;
        if (copy) {
            int n = shapes.size();
            List<SWTShape2d> copies = new ArrayList<SWTShape2d>(n);
            for (int i = 0; i < n; i++) {
                IShape2d shape = shapes.get(i);
                copies.set(i, new SWTShape2d(shape.clone()));
            }
            this.shapes = copies;
        } else {
            this.shapes = shapes;
        }
    }

    public GDShapes2f(List<SWTShape2d> shapes) {
        this(shapes, false);
    }

    public GDShapes2f() {
        this(new ArrayList<SWTShape2d>(), false);
    }

    // -o Pick

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

    // -o Shape

    public int getPointCount() {
        return 0;
    }

    @Override
    public IPointRef2d getPointRef(int index) {
        return null;
    }

    @Override
    public IShape2d crop(PositiveHalfPlane php, boolean detached) {
        int n = shapes.size();
        for (int i = 0; i < n;) {
            IShape2d shape = shapes.get(i);
            shape = shape.crop(php, detached);
            if (shape == null) {
                shapes.remove(i);
                n--;
            } else {
                i++;
            }
        }
        if (shapes.size() == 0)
            return null;
        return this;
    }

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

    @Override
    public GDShapes2f clone() {
        return new GDShapes2f(shapes, true);
    }

    @Override
    public GDShapes2f snapshot() {
        int n = shapes.size();
        List<SWTShape2d> shapes = new ArrayList<SWTShape2d>(n);
        for (int i = 0; i < n; i++)
            shapes.add(new SWTShape2d(shapes.get(i).snapshot()));
        return new GDShapes2f(shapes);
    }

    public void draw(IDrawContext2d context)
            throws GraphicsOperationException {
        int n = shapes.size();
        for (int i = 0; i < n; i++) {
            IShape2d shape = shapes.get(i);
            shape.draw(context);
        }
    }

}
