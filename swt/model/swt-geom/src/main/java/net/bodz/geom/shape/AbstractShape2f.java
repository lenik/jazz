package net.bodz.geom.shape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.shape.base.*;
import net.bodz.geom.shape.base.impl.H_PointSet2f;

public abstract class AbstractShape2f
        implements IShape2f {

    // -o PointSet

    @Override
    public float pointX(int index) {
        Point2f p = point(index);
        return p.x;
    }

    @Override
    public float pointY(int index) {
        Point2f p = point(index);
        return p.y;
    }

    @Override
    public Point2f point(int index) {
        Point2f ref = pointRef(index);
        return ref;
    }

    @Override
    public Iterator<Point2f> pointIterator() {
        return new H_PointSet2f.PointIterator(this);
    }

    @Override
    public Point2f[] toPointsArray(boolean copy) {
        int n = pointCount();
        Point2f[] points = new Point2f[n];
        if (copy)
            for (int i = 0; i < n; i++)
                points[i] = point(i).clone();
        else
            for (int i = 0; i < n; i++)
                points[i] = point(i);
        return points;
    }

    @Override
    public List<Point2f> toPointsList(boolean copy) {
        int n = pointCount();
        List<Point2f> points = new ArrayList<Point2f>(n);
        if (copy)
            for (int i = 0; i < n; i++)
                points.add(point(i).clone());
        else
            for (int i = 0; i < n; i++)
                points.add(point(i));
        return points;
    }

    @Override
    public Iterator<Point2f> pointRefIterator() {
        return new H_PointSet2f.PointRefIterator(this);
    }

    @Override
    public Point2f[] toPointRefsArray(boolean copy) {
        int n = pointCount();
        IPoint2f[] points = new IPoint2f[n];
        if (copy)
            for (int i = 0; i < n; i++)
                points[i] = pointRef(i).clone();
        else
            for (int i = 0; i < n; i++)
                points[i] = pointRef(i);
        return points;
    }

    @Override
    public List<IPoint2f> toPointRefsList(boolean copy) {
        int n = pointCount();
        List<IPoint2f> points = new ArrayList<IPoint2f>(n);
        if (copy)
            for (int i = 0; i < n; i++)
                points.add(pointRef(i).clone());
        else
            for (int i = 0; i < n; i++)
                points.add(pointRef(i));
        return points;
    }

    // -o Pick

    @Override
    public PickInfo2f pickInfo(IPoint2f point) {
        assert point != null;
        return pickInfo(point.x(), point.y());
    }

    @Override
    public abstract PickInfo2f pickInfo(float x, float y);

    @Override
    public IShape2f pick(IPoint2f point) {
        assert point != null;
        return pick(point.x(), point.y());
    }

    @Override
    public IShape2f pick(float x, float y) {
        PickInfo2f pi = pickInfo(x, y);
        if (pi.distance <= 0)
            return pi.pick;
        return null;
    }

    @Override
    public float distance(IPoint2f point) {
        assert point != null;
        return distance(point.x(), point.y());
    }

    @Override
    public abstract float distance(float x, float y);

    @Override
    public boolean contains(IPoint2f point) {
        return contains(point.x(), point.y());
    }

    @Override
    public boolean contains(float x, float y) {
        return distance(x, y) <= 0.0f;
    }

    // -o Shape

    private final class PtCenter
            extends AbstractPoint2f {

        static final long serialVersionUID = -3832621801754168642L;
        float ax;
        float ay;

        public PtCenter() {
            int n = pointCount();
            if (n == 0) {
                ax = 0.0f;
                ay = 0.0f;
            } else {
                for (int i = 0; i < n; i++) {
                    IPoint2f pt = pointRef(i);
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

    @Override
    public IPoint2f spointRef(int id) {
        switch (id) {
        case SP_CENTER:
            return new PtCenter();
        }
        return null;
    }

    @Override
    public StaticPoint2f spoint(int id) {
        return spointRef(id).snapshot();
    }

    @Override
    public float spointX(int id) {
        IPoint2f p = spointRef(id);
        if (p == null)
            return Float.NaN;
        return p.x();
    }

    @Override
    public float spointY(int id) {
        IPoint2f p = spointRef(id);
        if (p == null)
            return Float.NaN;
        return p.y();
    }

    @Override
    public void translate(float dx, float dy) {
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            IPoint2f p = pointRef(i);
            p.translate(dx, dy);
        }
    }

    @Override
    public void rotate(float angle) {
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            IPoint2f p = pointRef(i);
            p.rotate(angle);
        }
    }

    @Override
    public void scale(float kx, float ky) {
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            IPoint2f p = pointRef(i);
            p.scale(kx, ky);
        }
    }

    @Override
    public void rotateAt(float angle, float baseX, float baseY) {
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            IPoint2f p = pointRef(i);
            p.rotateAt(angle, baseX, baseY);
        }
    }

    @Override
    public void scaleAt(float kx, float ky, float baseX, float baseY) {
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            IPoint2f p = pointRef(i);
            p.scaleAt(kx, ky, baseX, baseY);
        }
    }

    @Override
    public void translate(javax.vecmath.Vector2f dv) {
        translate(dv.x, dv.y);
    }

    @Override
    public void rotateAt(float angle, Point2f basePoint) {
        rotateAt(angle, basePoint.x, basePoint.y);
    }

    @Override
    public void scaleAt(float k, float baseX, float baseY) {
        scaleAt(k, k, baseX, baseY);
    }

    @Override
    public void scaleAt(float kx, float ky, Point2f basePoint) {
        scaleAt(kx, ky, basePoint.x, basePoint.y);
    }

    @Override
    public void scaleAt(float k, Point2f basePoint) {
        scaleAt(k, k, basePoint.x, basePoint.y);
    }

    @Override
    public void scale(float k) {
        scale(k, k);
    }

    @Override
    public void scaleAt(Vector2f kv, float baseX, float baseY) {
        scaleAt(kv.x, kv.y, baseX, baseY);
    }

    @Override
    public void scaleAt(Vector2f kv, Point2f basePoint) {
        scaleAt(kv.x, kv.y, basePoint.x, basePoint.y);
    }

    @Override
    public void scale(javax.vecmath.Vector2f kv) {
        scale(kv.x, kv.y);
    }

    protected IShape2f cropConvexPolygon(IPointSet2f polygon) {
        IShape2f cropped = this;
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

    @Override
    public abstract IShape2f crop(javax.vecmath.Point2f baseHalfPlane, javax.vecmath.Vector2f normal);

    @Override
    public IShape2f crop(ILine2f directed) {
        return crop(directed.p0(), directed.getNormal());
    }

    @Override
    public IShape2f crop(IRectangle2f rectangle) {
        return cropConvexPolygon(rectangle);
    }

    @Override
    public IShape2f crop(float x0, float y0, float x1, float y1) {
        return crop(new IRectangle2f.StaticLeft(x0, y0, x1, y1));
    }

    @Override
    public IShape2f crop(ITriangle2f triangle) {
        return cropConvexPolygon(triangle);
    }

    @Override
    public IShape2f crop(float x0, float y0, float x1, float y1, float x2, float y2) {
        return cropConvexPolygon(new ITriangle2f.Static(x0, y0, x1, y1, x2, y2));
    }

    @Override
    public IShape2f crop(IPolygon2f polygon) {
        ITriangle2f[] triSet = polygon.toTriangles();
        ArrayList<IShape2f> shapes = new ArrayList<IShape2f>();
        for (int i = 0; i < triSet.length; i++) {
            IShape2f cropped = crop(triSet[i]);
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

    @Override
    public Set<Class<?>> getConvertToClasses() {
        return getShapeClassInfo().convertToMethods.keySet();
    }

    @Override
    public IShape2f convertTo(Class<?> cls) {
        ShapeClassInfo info = getShapeClassInfo();
        Method mtd = info.convertToMethods.get(cls);
        if (mtd != null) {
            try {
                Object ret = mtd.invoke(this, new Object[] {});
                return (IShape2f) ret;
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return null;
    }

    @Override
    public IRectangle2f boundingBox() {
        int n = pointCount();
        if (n == 0)
            return new IRectangle2f.StaticLeft(0, 0, 0, 0);

        IRectangle2f r = new IRectangle2f.StaticLeft(pointRef(0), new javax.vecmath.Vector2f(Float.MIN_VALUE,
                Float.MIN_VALUE));

        for (int i = 1; i < n; i++)
            ; // r.addWithScale(points.point(i));

        return r;
    }

    public ICircle2f boundingSphere() {
        int n = pointCount();
        if (n == 0)
            return new ICircle2f.Static_Cr(0, 0, 0);

        ICircle2f.Static_Cr c = new ICircle2f.Static_Cr(pointRef(0), Float.MIN_VALUE);

        for (int i = 1; i < n; i++)
            ; // c.addWithScale(points.point(i));

        return c;
    }

    @Override
    public abstract IShape2f snapshot();

    @Override
    public abstract AbstractShape2f clone();

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("<PointSet>");
        int n = pointCount();
        for (int i = 0; i < n; i++) {
            IPoint2f pt = pointRef(i);
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

    private static String PkgPrefix;
    private static HashMap<Class<?>, ShapeClassInfo> shapeClasses;

    static {
        PkgPrefix = AbstractShape2f.class.getName();
        PkgPrefix = PkgPrefix.substring(0, PkgPrefix.length() - ".AbstractShape2f".length());
        shapeClasses = new HashMap<Class<?>, ShapeClassInfo>();
    }
}
