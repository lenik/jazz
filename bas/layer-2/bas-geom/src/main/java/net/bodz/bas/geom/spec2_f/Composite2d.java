package net.bodz.bas.geom.spec2_f;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.geom.spec0_f.AbstractPrimitive2d;
import net.bodz.bas.geom.spec0_f.IPolygonizable2d;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.PickResult2d;
import net.bodz.bas.geom.spec0_f.PositiveHalfPlane2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.geom.spec1_f.Polygon2d;
import net.bodz.bas.geom.spec1_f.Rectangle2d;
import net.bodz.bas.gui.draw_f.dc.DrawException;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawContext2d;
import net.bodz.bas.gui.draw_f.dc.IBaseDrawable2d;
import net.bodz.bas.t.object.ISnapShot;

public class Composite2d
        extends AbstractPrimitive2d {

    private static final long serialVersionUID = 1L;

    private List<IPrimitive2d> elements;

    public Composite2d() {
        this(new ArrayList<IPrimitive2d>());
    }

    public Composite2d(List<IPrimitive2d> primitives) {
        assert primitives != null;
        this.elements = primitives;
    }

    /** ⇱ Implementation Of {@link IPrimitive2d}. */
    /* _____________________________ */static section.iface __BASE__;

    @Override
    public Point2d degenerate() {
        Point2d single = null;
        for (IPrimitive2d element : elements) {
            Point2d point = element.degenerate();
            if (point == null)
                return null;
            if (single == null)
                single = point;
            else if (!single.equals(point)) // within epsilon = ...
                return null;
        }
        return single;
    }

    /** ⇱ Implementation Of {@link ISnapShot}. */
    /* _____________________________ */static section.iface __SNAPSHOT__;

    @Override
    public Composite2d snap() {
        List<IPrimitive2d> copy = new ArrayList<IPrimitive2d>(elements.size());
        for (IPrimitive2d primitive : elements) {
            IPrimitive2d shotdShape = primitive.snap();
            copy.add(shotdShape);
        }
        return new Composite2d(copy);
    }

    @Override
    public Composite2d shot() {
        List<IPrimitive2d> copy = new ArrayList<IPrimitive2d>(elements.size());
        for (IPrimitive2d primitive : elements) {
            IPrimitive2d shotdShape = primitive.shot();
            copy.add(shotdShape);
        }
        return new Composite2d(copy);
    }

    @Override
    public Composite2d snapshot() {
        List<IPrimitive2d> copy = new ArrayList<IPrimitive2d>(elements.size());
        for (IPrimitive2d primitive : elements) {
            IPrimitive2d snapshot = primitive.snapshot();
            copy.add(snapshot);
        }
        return new Composite2d(copy);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPointSet2d}. */
    /* _____________________________ */static section.iface __POINTS__;

    public int getPointCount() {
        return 0;
    }

    @Override
    public Point2d getPoint(int index) {
        return getBadPoint(index);
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IBoundingBox2d}. */
    /* _____________________________ */static section.iface __BBOX__;

    @Override
    public Rectangle2d getBoundingBox() {
        int n = elements.size();
        if (n == 0)
            return null;

        Rectangle2d bbox = elements.get(0).getBoundingBox();

        for (int i = 1; i < n; i++) {
            IPrimitive2d element = elements.get(i);
            Rectangle2d b = element.getBoundingBox();
            bbox.include(b);
        }
        return bbox; // super.getBoundingBox();
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.IPickable2d}. */
    /* _____________________________ */static section.iface __PICK__;

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
        int n = elements.size();
        IPrimitive2d component = null;

        for (int i = 0; i < n; i++) {
            IPrimitive2d elm = elements.get(i);
            PickResult2d r = elm._pick(point);
            if (r.getDistance() < minDistance) {
                minDistance = r.getDistance();
                result = r;
                component = elm;
            }
        }
        if (result == null)
            return null;
        return new CompPickInfo2f(result.getShape(), result.getDistance(), component);
    }

    public IPrimitive2d find(float x, float y) {
        int n = elements.size();
        for (int i = 0; i < n; i++) {
            IPrimitive2d element = elements.get(i);
            if (element.contains(x, y))
                return element;
        }
        return null;
    }

    @Override
    public float distance(Point2d point) {
        float minDistance = Float.MAX_VALUE;
        int n = elements.size();
        for (int i = 0; i < n; i++) {
            IPrimitive2d element = elements.get(i);
            float dist = element.distance(point);
            if (dist < minDistance) {
                minDistance = dist;
            }
        }
        return minDistance;
    }

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.ICroppable2d}. */
    /* _____________________________ */static section.iface __CROP__;

    @Override
    public IPrimitive2d crop(PositiveHalfPlane2d php, boolean detached) {
        int shapeCount = elements.size();
        for (int index = 0; index < shapeCount;) {
            IPrimitive2d element = elements.get(index);
            element = element.crop(php, detached);
            if (element == null) {
                elements.remove(index);
                shapeCount--;
            } else {
                index++;
            }
        }
        if (elements.isEmpty())
            return null;
        else
            return this;
    }

    /** ⇱ Implementation Of {@link IPolygonizable2d}. */
    /* _____________________________ */static section.iface __POLYGONIZE__;

    @Override
    public Polygon2d polygonize() {
        Polygon2d union = null;
        for (IPrimitive2d element : elements) {
            Polygon2d poly = element.polygonize();
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

    /** ⇱ Implementation Of {@link IBaseDrawable2d}. */
    /* _____________________________ */static section.iface __DRAW__;

    @Override
    public void draw(IBaseDrawContext2d ctx)
            throws DrawException {
        for (IPrimitive2d primitive : elements)
            primitive.draw(ctx);
    }

}
