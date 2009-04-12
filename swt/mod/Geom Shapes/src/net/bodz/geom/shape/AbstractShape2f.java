package net.bodz.geom.shape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.shape.base.AbstractPoint2f;
import net.bodz.geom.shape.base.Circle2f;
import net.bodz.geom.shape.base.Line2f;
import net.bodz.geom.shape.base.Point2f;
import net.bodz.geom.shape.base.Polygon2f;
import net.bodz.geom.shape.base.Rectangle2f;
import net.bodz.geom.shape.base.Triangle2f;
import net.bodz.geom.shape.base.impl.H_PointSet2f;
import net.bodz.math.mat.Vector2f;

public abstract class AbstractShape2f implements Shape2f {

    // -o PointSet

    public float pointX(int index) {
        assert index >= 0 && index < pointCount();
        Point2f p = point(index);
        assert p != null;
        return p.x();
    }

    public float pointY(int index) {
        assert index >= 0 && index < pointCount();
        Point2f p = point(index);
        assert p != null;
        return p.y();
    }

    public Point2f.Static point(int index) {
        assert index >= 0 && index < pointCount();
        Point2f ref = pointRef(index);
        return new Point2f.Static(ref.x(), ref.y());
    }

    public Iterator<Point2f.Static> pointIterator() {
        return new H_PointSet2f.PointIterator(this);
    }

    public Point2f.Static[] toPointsArray(boolean copy) {
        int n = pointCount();
        Point2f.Static[] points = new Point2f.Static[n];
        if (copy)
            for (int i = 0; i < n; i++)
                points[i] = point(i).clone();
        else
            for (int i = 0; i < n; i++)
                points[i] = point(i);
        return points;
    }

    public List<Point2f.Static> toPointsList(boolean copy) {
        int n = pointCount();
        List<Point2f.Static> points = new ArrayList<Point2f.Static>(n);
        if (copy)
            for (int i = 0; i < n; i++)
                points.add(point(i).clone());
        else
            for (int i = 0; i < n; i++)
                points.add(point(i));
        return points;
    }

    public Iterator<Point2f> pointRefIterator() {
        return new H_PointSet2f.PointRefIterator(this);
    }

    public Point2f[] toPointRefsArray(boolean copy) {
        int n = pointCount();
        Point2f[] points = new Point2f[n];
        if (copy)
            for (int i = 0; i < n; i++)
                points[i] = pointRef(i).clone();
        else
            for (int i = 0; i < n; i++)
                points[i] = pointRef(i);
        return points;
    }

    public List<Point2f> toPointRefsList(boolean copy) {
        int n = pointCount();
        List<Point2f> points = new ArrayList<Point2f>(n);
        if (copy)
            for (int i = 0; i < n; i++)
                points.add(pointRef(i).clone());
        else
            for (int i = 0; i < n; i++)
                points.add(pointRef(i));
        return points;
    }

    // -o Pick

    public PickInfo2f pickInfo(Point2f point) {
        assert point != null;
        return pickInfo(point.x(), point.y());
    }

    public abstract PickInfo2f pickInfo(float x, float y);

    public Shape2f pick(Point2f point) {
        assert point != null;
        return pick(point.x(), point.y());
    }

    public Shape2f pick(float x, float y) {
        PickInfo2f pi = pickInfo(x, y);
        if (pi.distance <= 0)
            return pi.pick;
        return null;
    }

    public float distance(Point2f point) {
        assert point != null;
        return distance(point.x(), point.y());
    }

    public abstract float distance(float x, float y);

    public boolean contains(Point2f point) {
        return contains(point.x(), point.y());
    }

    public boolean contains(float x, float y) {
        return distance(x, y) <= 0.0f;
    }

    // -o Shape

    private final class PtCenter extends AbstractPoint2f {

        static final long serialVersionUID = -3832621801754168642L;
        float             ax;
        float             ay;

        public PtCenter() {
            int n = pointCount();
            if (n == 0) {
                ax = 0.0f;
                ay = 0.0f;
            } else {
                for (int i = 0; i < n; i++) {
                    Point2f pt = pointRef(i);
                    ax += pt.x();
                    ay += pt.y();
                }
                ax = ax / n;
                ay = ay / n;
            }
        }

        @Override
        public PtCenter clone() {
            return new PtCenter();
        }

        public float x() {
            return ax;
        }

        public float y() {
            return ay;
        }
    }

    public Point2f spointRef(int id) {
        switch (id) {
        case SP_CENTER:
            return new PtCenter();
        }
        return null;
    }

    public Point2f.Static spoint(int id) {
        return spointRef(id).snapshot();
    }

    public float spointX(int id) {
        Point2f p = spointRef(id);
        if (p == null)
            return Float.NaN;
        return p.x();
    }

    public float spointY(int id) {
        Point2f p = spointRef(id);
        if (p == null)
            return Float.NaN;
        return p.y();
    }

    public void translate(float dx, float dy) {
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            Point2f p = pointRef(i);
            p.translate(dx, dy);
        }
    }

    public void rotate(float angle) {
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            Point2f p = pointRef(i);
            p.rotate(angle);
        }
    }

    public void scale(float kx, float ky) {
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            Point2f p = pointRef(i);
            p.scale(kx, ky);
        }
    }

    public void rotate(float angle, float baseX, float baseY) {
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            Point2f p = pointRef(i);
            p.rotate(angle, baseX, baseY);
        }
    }

    public void scale(float kx, float ky, float baseX, float baseY) {
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            Point2f p = pointRef(i);
            p.scale(kx, ky, baseX, baseY);
        }
    }

    public void translate(Vector2f dv) {
        translate(dv.x, dv.y);
    }

    public void rotate(float angle, Point2f basePoint) {
        rotate(angle, basePoint.x(), basePoint.y());
    }

    public void scale(float k, float baseX, float baseY) {
        scale(k, k, baseX, baseY);
    }

    public void scale(float kx, float ky, Point2f basePoint) {
        scale(kx, ky, basePoint.x(), basePoint.y());
    }

    public void scale(float k, Point2f basePoint) {
        scale(k, k, basePoint.x(), basePoint.y());
    }

    public void scale(float k) {
        scale(k, k);
    }

    public void scale(Vector2f kv, float baseX, float baseY) {
        scale(kv.x, kv.y, baseX, baseY);
    }

    public void scale(Vector2f kv, Point2f basePoint) {
        scale(kv.x, kv.y, basePoint.x(), basePoint.y());
    }

    public void scale(Vector2f kv) {
        scale(kv.x, kv.y);
    }

    protected Shape2f cropConvexPolygon(PointSet2f polygon) {
        Shape2f cropped = this;
        int n = polygon.pointCount();
        Point2f prev = polygon.pointRef(n - 1);
        for (int i = 0; i < n; i++) {
            Point2f pt = polygon.pointRef(i);
            cropped = cropped.crop(prev, prev.vectorTo(pt).getNormal());
            if (cropped == null)
                return null;
            prev = pt;
        }
        return cropped;
    }

    public abstract Shape2f crop(Point2f baseHalfPlane, Vector2f normal);

    public Shape2f crop(Line2f directed) {
        return crop(directed.p0(), directed.getNormal());
    }

    public Shape2f crop(Rectangle2f rectangle) {
        return cropConvexPolygon(rectangle);
    }

    public Shape2f crop(float x0, float y0, float x1, float y1) {
        return crop(new Rectangle2f.StaticLeft(x0, y0, x1, y1));
    }

    public Shape2f crop(Triangle2f triangle) {
        return cropConvexPolygon(triangle);
    }

    public Shape2f crop(float x0, float y0, float x1, float y1, float x2,
            float y2) {
        return cropConvexPolygon(new Triangle2f.Static(x0, y0, x1, y1, x2, y2));
    }

    public Shape2f crop(Polygon2f polygon) {
        Triangle2f[] triSet = polygon.toTriangles();
        ArrayList<Shape2f> shapes = new ArrayList<Shape2f>();
        for (int i = 0; i < triSet.length; i++) {
            Shape2f cropped = crop(triSet[i]);
            if (cropped != null)
                shapes.add(cropped);
        }
        // TODO add ShapesSet shape type.
        // ShapeSet2f combined = new ShapeSet2f(shapes);
        // return combined;
        return null;
    }

    private ShapeClassInfo getShapeClassInfo() {
        Class<?> shapeClass = this.getClass();
        ShapeClassInfo info = shapeClasses.get(shapeClass);
        if (info == null) {
            info = new ShapeClassInfo(shapeClass);
            shapeClasses.put(shapeClass, info);
        }
        return info;
    }

    public Set<Class<?>> getConvertToClasses() {
        return getShapeClassInfo().convertToMethods.keySet();
    }

    public Shape2f convertTo(Class<?> cls) {
        ShapeClassInfo info = getShapeClassInfo();
        Method mtd = info.convertToMethods.get(cls);
        if (mtd != null) {
            try {
                Object ret = mtd.invoke(this, new Object[] {});
                return (Shape2f) ret;
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return null;
    }

    public Rectangle2f boundingBox() {
        int n = pointCount();
        if (n == 0)
            return new Rectangle2f.StaticLeft(0, 0, 0, 0);

        Rectangle2f r = new Rectangle2f.StaticLeft(pointRef(0), new Vector2f(
                Float.MIN_VALUE, Float.MIN_VALUE));

        for (int i = 1; i < n; i++)
            ; // r.addWithScale(points.point(i));

        return r;
    }

    public Circle2f boundingSphere() {
        int n = pointCount();
        if (n == 0)
            return new Circle2f.Static_Cr(0, 0, 0);

        Circle2f.Static_Cr c = new Circle2f.Static_Cr(pointRef(0),
                Float.MIN_VALUE);

        for (int i = 1; i < n; i++)
            ; // c.addWithScale(points.point(i));

        return c;
    }

    public abstract Shape2f snapshot();

    @Override
    public abstract AbstractShape2f clone();

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("<PointSet>");
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            Point2f pt = pointRef(i);
            buf.append(pt.toString());
        }
        buf.append("</PointSet>");
        return buf.toString();
    }

    private static class ShapeClassInfo {
        public ShapeClassInfo(Class<?> shapeClass) {
            assert shapeClass != null;
            Method[] mtds = shapeClass.getMethods();
            for (int i = 0; i < mtds.length; i++) {
                Method mtd = mtds[i];
                String mtdName = mtd.getName();
                if (mtdName.startsWith("convertTo")) {
                    if ((mtd.getModifiers() & Member.PUBLIC) != 0) {
                        Class<?> clazz;
                        String clsName = mtdName.substring(9);
                        if (clsName.length() > 0) {
                            clsName = PkgPrefix + clsName;
                            try {
                                clazz = Class.forName(clsName);
                                convertToMethods.put(clazz, mtd);
                            } catch (ClassNotFoundException e) {
                                // convertToNAME where NAME is not existed.
                                assert false;
                            }
                        }
                    }
                }
            }
        }

        public HashMap<Class<?>, Method> convertToMethods;
    }

    private static String                            PkgPrefix;
    private static HashMap<Class<?>, ShapeClassInfo> shapeClasses;

    static {
        PkgPrefix = AbstractShape2f.class.getName();
        PkgPrefix = PkgPrefix.substring(0, PkgPrefix.length()
                - ".AbstractShape2f".length());
        shapeClasses = new HashMap<Class<?>, ShapeClassInfo>();
    }
}
