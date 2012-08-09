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

    List<SWTShape2f> shapes;

    public GDShapes2f(List<SWTShape2f> shapes, boolean copy) {
        assert shapes != null;
        if (copy) {
            int n = shapes.size();
            List<SWTShape2f> copies = new ArrayList<SWTShape2f>(n);
            for (int i = 0; i < n; i++) {
                IShape2d shape = shapes.get(i);
                copies.set(i, new SWTShape2f(shape.clone()));
            }
            this.shapes = copies;
        } else {
            this.shapes = shapes;
        }
    }

    public GDShapes2f(List<SWTShape2f> shapes) {
        this(shapes, false);
    }

    public GDShapes2f() {
        this(new ArrayList<SWTShape2f>(), false);
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
    public PickResult2d _pick(float x, float y) {
        float minDistance = Float.MAX_VALUE;
        PickResult2d minPI = null;
        int n = shapes.size();
        IShape2d component = null;

        for (int i = 0; i < n; i++) {
            IShape2d shape = shapes.get(i);
            PickResult2d pi = shape._pick(x, y);
            if (pi.distance < minDistance) {
                minDistance = pi.distance;
                minPI = pi;
                component = shape;
            }
        }
        if (minPI == null)
            return null;
        return new CompPickInfo2f(minPI.pick, minPI.distance, component);
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

    public IPointRef2d getPointRef(int index) {
        return null;
    }

    @Override
    public IShape2d crop(PositiveHalfPlane hp) {
        int n = shapes.size();
        for (int i = 0; i < n;) {
            IShape2d shape = shapes.get(i);
            shape = shape.crop(baseHalfPlane, normal);
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
        for (int i = 0; i < n; i++) {
            IShape2d shape = shapes.get(i);
            IRectangle2d r = shape.getBoundingBoxRef();
            b.include(r);
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
        List<SWTShape2f> shapes = new ArrayList<SWTShape2f>(n);
        for (int i = 0; i < n; i++)
            shapes.add(new SWTShape2f(shapes.get(i).snapshot()));
        return new GDShapes2f(shapes);
    }

    @Override
    public GDShapes2f clone() {
        return new GDShapes2f(shapes, true);
    }

    public void draw(DrawTarget2f target)
            throws DrawException {
        int n = shapes.size();
        for (int i = 0; i < n; i++) {
            IShape2d shape = shapes.get(i);
            shape.draw(target);
        }
    }

}
