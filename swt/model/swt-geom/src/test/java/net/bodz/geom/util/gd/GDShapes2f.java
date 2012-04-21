package net.bodz.geom.util.gd;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector2f;

import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.AbstractShape2f;
import net.bodz.geom.shape.Shape2f;
import net.bodz.geom.shape.base.AbstractRectangle2f;
import net.bodz.geom.shape.base.Point2f;
import net.bodz.geom.shape.base.Rectangle2f;

public class GDShapes2f
        extends AbstractShape2f {

    List<SWTShape2f> shapes;

    public GDShapes2f(List<SWTShape2f> shapes, boolean copy) {
        assert shapes != null;
        if (copy) {
            int n = shapes.size();
            List<SWTShape2f> copies = new ArrayList<SWTShape2f>(n);
            for (int i = 0; i < n; i++) {
                Shape2f shape = shapes.get(i);
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
            extends PickInfo2f {
        public Shape2f component;

        public CompPickInfo2f(Shape2f pick, float distance, Shape2f component) {
            super(pick, distance);
            this.component = component;
        }
    }

    @Override
    public PickInfo2f pickInfo(float x, float y) {
        float minDistance = Float.MAX_VALUE;
        PickInfo2f minPI = null;
        int n = shapes.size();
        Shape2f component = null;

        for (int i = 0; i < n; i++) {
            Shape2f shape = shapes.get(i);
            PickInfo2f pi = shape.pickInfo(x, y);
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

    public Shape2f find(float x, float y) {
        int n = shapes.size();
        for (int i = 0; i < n; i++) {
            Shape2f shape = shapes.get(i);
            if (shape.contains(x, y))
                return shape;
        }
        return null;
    }

    @Override
    public float distance(float x, float y) {
        float minDistance = Float.MAX_VALUE;
        int n = shapes.size();
        for (int i = 0; i < n; i++) {
            Shape2f shape = shapes.get(i);
            float dist = shape.distance(x, y);
            if (dist < minDistance) {
                minDistance = dist;
            }
        }
        return minDistance;
    }

    // -o Shape

    public int pointCount() {
        return 0;
    }

    public Point2f pointRef(int index) {
        return null;
    }

    @Override
    public Shape2f crop(Point2f baseHalfPlane, Vector2f normal) {
        int n = shapes.size();
        for (int i = 0; i < n;) {
            Shape2f shape = shapes.get(i);
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
    public Rectangle2f boundingBox() {
        AbstractRectangle2f b = new Rectangle2f.LeftPositive(Float.MAX_VALUE, Float.MAX_VALUE, -Float.MAX_VALUE,
                -Float.MAX_VALUE);
        int n = shapes.size();
        for (int i = 0; i < n; i++) {
            Shape2f shape = shapes.get(i);
            Rectangle2f r = shape.boundingBox();
            b.addWithScale(r);
        }
        return super.boundingBox();
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
            Shape2f shape = shapes.get(i);
            shape.draw(target);
        }
    }

}
