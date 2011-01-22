package net.bodz.geom.shape.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.AbstractShape2f;
import net.bodz.geom.shape.Shape2f;
import net.bodz.math.mat.Vector2f;

public abstract class AbstractPolygon2f extends AbstractShape2f implements Polygon2f, Cloneable,
        Serializable {

    static final long serialVersionUID = 2318246123146564098L;

    protected final class PtCenter extends AbstractPoint2f {

        static final long serialVersionUID = -6609812231313943384L;

        public PtCenter() {
        }

        @Override
        public PtCenter clone() {
            return new PtCenter();
        }

        public float x() {
            return centerX();
        }

        public float y() {
            return centerY();
        }

    }

    @Override
    public abstract AbstractPolygon2f clone();

    private boolean opened;

    public Point2f.Static point(float index) {
        int start = (int) Math.floor(index);
        int end = (int) Math.ceil(index);
        if (start == end)
            return point(start);
        return point(start).lineTo(point(end)).point(index - start);
    }

    public float pointX(float index) {
        int start = (int) Math.floor(index);
        int end = (int) Math.ceil(index);
        if (start == end)
            return pointRef(start).x();
        return pointRef(start).lineTo(pointRef(end)).pointX(index - start);
    }

    public float pointY(float index) {
        int start = (int) Math.floor(index);
        int end = (int) Math.ceil(index);
        if (start == end)
            return pointRef(start).y();
        return pointRef(start).lineTo(pointRef(end)).pointY(index - start);
    }

    public float index(float x, float y) {
        int i = nearestEdge(x, y);
        Line2f edge = edge(i);
        return i + edge.index(x, y);
    }

    public float index(Point2f point) {
        return index(point.x(), point.y());
    }

    public Point2f.Static center() {
        return new Point2f.Static(centerX(), centerY());
    }

    public float centerX() {
        int n = pointCount();
        if (n == 0)
            return 0;
        float ax = 0;
        for (int i = 0; i < n; i++) {
            ax += pointRef(i).x();
        }
        return ax / n;
    }

    public float centerY() {
        int n = pointCount();
        if (n == 0)
            return 0;
        float ay = 0;
        for (int i = 0; i < n; i++) {
            ay += pointRef(i).y();
        }
        return ay / n;
    }

    public Line2f edgeRef(int index) {
        int n = pointCount();
        Point2f start = pointRef(index++);
        Point2f end = pointRef(index % n);
        return start.lineTo(end);
    }

    public Line2f edgeRef(int index1, int index2) {
        int n = pointCount();
        if (index2 == (index1 + 1) % n)
            return edge(index1);
        Point2f p1 = pointRef(index1);
        Point2f p2 = pointRef(index2);
        return p1.lineTo(p2);
    }

    public Line2f.Static edge(int index) {
        int n = pointCount();
        // x()
        Point2f start = point(index++);
        Point2f end = point(index % n);
        return start.lineTo(end);
    }

    public Line2f.Static edge(int index1, int index2) {
        int n = pointCount();
        if (index2 == (index1 + 1) % n)
            return edge(index1);
        Point2f p1 = point(index1);
        Point2f p2 = point(index2);
        return p1.lineTo(p2);
    }

    protected int nearestEdge(float x, float y) {
        int index = 0;
        // Line2f edge;
        float dist = Float.MAX_VALUE;

        int n = pointCount();
        for (int i = 0; i < n; i++) {
            Line2f e = edge(i);
            float d = e.distance(x, y);
            if (d < dist) {
                index = i;
                // edge = e;
                dist = d;
            }
        }
        return index;
    }

    public final boolean isOpened() {
        return !opened;
    }

    public final boolean isClosed() {
        return !opened;
    }

    public final void open() {
        opened = true;
    }

    public final void close() {
        opened = false;
    }

    public void addIndex(float index) {
        int start = (int) Math.floor(index);
        int end = (int) Math.ceil(index);
        if (start == end)
            return;
        Point2f point = pointRef(start).lineTo(pointRef(end)).point(index - start);
        addPoint(end, point);
    }

    public void addCross(Line2f line) {
        int n = pointCount();
        if (n < 2)
            return;
        int offset = isClosed() ? n - 1 : n - 2;
        Point2f ep = pointRef((offset + 1) % n);
        for (int i = offset; i >= 0; i--) {
            Point2f p = pointRef(i);
            Line2f edge = p.lineTo(ep);
            Point2f x = edge.intersectsAt(line);
            if (x != null)
                addPoint(i + 1, x);
            ep = p;
        }
    }

    public List<Point2f.Static> intersectsAt(Line2f line, int max) {
        List<Point2f.Static> list = new ArrayList<Point2f.Static>();
        int n = pointCount();
        for (int i = 0; i < n; i++)
            if (edge(i).intersectsAt(line) != null) {
                list.add(point(i));
                if (list.size() >= max)
                    break;
            }
        return list;
    }

    public List<Integer> intersectsAtIndex(Line2f line, int max) {
        List<Integer> list = new ArrayList<Integer>();
        int n = pointCount();
        for (int i = 0; i < n; i++)
            if (edge(i).intersectsAt(line) != null) {
                list.add(i);
                if (list.size() >= max)
                    break;
            }
        return list;
    }

    public List<Point2f.Static> intersectsAt(Line2f line) {
        return intersectsAt(line, Integer.MAX_VALUE);
    }

    public List<Integer> intersectsAtIndex(Line2f line) {
        return intersectsAtIndex(line, Integer.MAX_VALUE);
    }

    public int intersectsCount(Line2f line, int maxTest) {
        assert maxTest > 0;
        int n = pointCount();
        int c = 0;
        for (int i = 0; i < n; i++)
            if (edge(i).intersectsAt(line) != null)
                if (++c >= maxTest)
                    break;
        return c;
    }

    public final int intersectsCount(Line2f line) {
        return intersectsCount(line, Integer.MAX_VALUE);
    }

    public boolean intersects(Line2f line) {
        return intersectsCount(line, 1) == 1;
    }

    public int direction() {
        int npoints = pointCount();
        if (npoints < 2)
            return N_A;

        float tsum = 0;
        Vector2f vPre = pointRef(npoints - 1).vectorTo(pointRef(0));

        for (int i = 0; i < npoints; i++) {
            Vector2f v = pointRef(i).vectorTo(pointRef((i + 1) % npoints));
            tsum += vPre.angle(v);
            vPre = v;
        }

        if (tsum == 2 * Math.PI)
            return CCW;
        return CW;
    }

    protected void swapPoint(int indexA, int indexB) {
        Point2f a = pointRef(indexA);
        Point2f b = pointRef(indexB);
        setPoint(indexA, b);
        setPoint(indexB, a);
    }

    public void reverse(int begin, int end) {
        int n = end - begin + 1;
        for (int i = 0; i < n / 2; i++) {
            swapPoint(begin + i, end - i);
        }
    }

    public void reverse() {
        reverse(0, pointCount() - 1);
    }

    @SuppressWarnings("unused")
    public void planarize() {
        int n = pointCount();
        if (isOpened())
            n--;
        if (n < 4)
            return;

        entire: while (true) {
            edge: for (int i = 2; i < n; i++) {
                findcut: for (int j = 0; j < i - 1; j++) {
                    Line2f I = edge(i);
                    Line2f J = edge(j);
                    Point2f x = I.intersectsAt(J);
                    if (x == null)
                        continue;
                    assert j < i;
                    // j-+-j'-----------i-+-i'
                    // +----------------+
                    // j-x-i-----------j'-x-i'
                    addPoint(i + 1, x.clone());
                    addPoint(j + 1, x.clone());
                    // reverse [j + 2 .. i]
                    reverse(j + 2, i);
                    n += 2;
                    continue entire;
                }
            }
            break;
        }
    }

    static final class TdTempPolygon extends Polygon2f.Standard {

        static final long serialVersionUID = 3071647986056763128L;

        List<Integer>     selfCuts;

        public TdTempPolygon(List<Point2f> points) {
            super(points);
            selfCuts = selfCuts();
        }

        public TdTempPolygon(Polygon2f polygon) {
            // don't copy the content of points.
            this(polygon.toPointRefsList(false));
        }

        public int intersectsCount(int index) {
            return selfCuts.get(index);
        }

        public int intersectsCount(int i, int j) {
            return intersectsCount(edge(i, j));
        }

        // for planarize, only
        @Override
        public void addPoint(int index, Point2f point) {
            super.addPoint(index, point);
            selfCuts = selfCuts();
        }

        @Override
        public void addPoint(int index, float x, float y) {
            addPoint(index, new Point2f.Static(x, y));
        }

        // for toTriangle, only
        @Override
        public void removePoint(int index) {
            /*
             * int n = pointCount(); Point2f l = point((index + n - 1) % n);
             * Point2f m = point(index); Point2f r = point((index + 1) % n);
             * 
             * Line2f remove1 = l.lineTo(m); Line2f remove2 = m.lineTo(r);
             * Line2f add = l.lineTo(r);
             * 
             * List<Integer> r1 = intersectsAtIndex(remove1); List<Integer> r2 =
             * intersectsAtIndex(remove2); List<Integer> a =
             * intersectsAtIndex(add1);
             */
            super.removePoint(index);
            selfCuts = selfCuts();
        }

        List<Integer> selfCuts() {
            int n = pointCount();
            int[] x = new int[n];
            for (int i = 2; i < n; i++)
                for (int j = 0; j < i - 1; j++)
                    if (edge(i).intersects(edge(j))) {
                        x[i]++;
                        x[j]++;
                    }
            List<Integer> cuts = new ArrayList<Integer>(n);
            for (int i = 0; i < n; i++)
                cuts.add(x[i]);
            return cuts;
        }
    }

    protected int getTdTempNext(Polygon2f temp, int tempIndex) {
        /*
         * Next temp index: star-dissection i helix-dissection i + 1
         * strip-dissection i - n % 2
         */
        return tempIndex;
    }

    public Triangle2f[] toTriangles() {
        int n = pointCount();
        TdTempPolygon temp = new TdTempPolygon(this);
        List<Triangle2f> ts = new ArrayList<Triangle2f>(n - 2);
        int i;
        int j = 0;
        int skips = 0;
        int ints;
        while (n > 2) {
            i = j++;
            j %= n;
            ints = temp.intersectsCount(i);
            if (i > 0) {
                if (++skips == n)
                    break;
                continue;
            }
            ints = temp.intersectsCount(j);
            if (ints > 0) {
                i = j++;
                j %= n;
                if ((skips += 2) >= n)
                    break;
                continue;
            }
            skips = 0;
            Triangle2f t = new Triangle2f.Static(temp.pointRef(i), temp.pointRef(j), temp
                    .pointRef((j + 1) % n));
            if (t.area() > 0)
                ts.add(t);
            temp.removePoint(j);
            n--;
            j = getTdTempNext(temp, i);
        }
        if (temp.pointCount() > 3) {
            temp.planarize();
            Triangle2f[] ts2 = temp.toTriangles();
            for (i = 0; i < ts2.length; i++)
                ts.add(ts2[i]);
        }
        return ts.toArray(new Triangle2f[0]);
    }

    // -o EditablePointSet

    public void setPoint(int index, Point2f point) {
        removePoint(index);
        addPoint(index, point);
    }

    public void setPoint(int index, float x, float y) {
        removePoint(index);
        addPoint(index, x, y);
    }

    public abstract void addPoint(int index, float x, float y);

    public void addPoint(int index, Point2f point) {
        addPoint(index, point.x(), point.y());
    }

    public final void addPoint(float x, float y) {
        int n = pointCount();
        addPoint(n, x, y);
    }

    public final void addPoint(Point2f point) {
        int n = pointCount();
        addPoint(n, point);
    }

    public abstract void removePoint(int index);

    // -o Pick

    @Override
    public PickInfo2f pickInfo(float x, float y) {
        int n = pointCount();
        if (n == 0)
            return null;

        float minDist = java.lang.Float.MAX_VALUE;
        Line2f minEdge = null;

        for (int i = 0; i < n; i++) {
            Line2f edge = edge(i);
            float dist = edge.distance(x, y); // always >= 0
            if (dist < minDist) {
                minDist = dist;
                minEdge = edge;
            }
        }

        assert minEdge != null;
        return new PickInfo2f(minEdge, minDist);
    }

    @Override
    public float distance(float x, float y) {
        PickInfo2f pi = pickInfo(x, y);
        if (pi == null)
            return Float.MAX_VALUE;
        return pi.distance;
    }

    // -o Shape

    @Override
    public Point2f spointRef(int id) {
        switch (id) {
        case SP_CENTER:
            return new PtCenter();
        }
        return super.spointRef(id);
    }

    @Override
    public Shape2f crop(Point2f baseHalfPlane, Vector2f normal) {
        ArrayList<Point2f> points = new ArrayList<Point2f>();

        int n = pointCount();

        int off = isOpened() ? 0 : n - 1;
        Point2f pre = pointRef(off);
        boolean lastOut = pre.crop(baseHalfPlane, normal) == null;

        Line2f cut = baseHalfPlane.halfPlane(normal);

        for (int i = 0; i < n; i++) {
            Point2f p = pointRef(i);
            boolean out = p.crop(baseHalfPlane, normal) == null;
            if (lastOut == out) {
                if (!out)
                    points.add(p);
            } else {
                Point2f x = pre.lineTo(p)._intersectsAt(cut);
                assert x != null;
                if (out) { // in -> out
                    points.add(x);
                } else { // out -> in
                    points.add(x);
                    points.add(p);
                }
                lastOut = out;
            }
        }

        switch (points.size()) {
        case 0:
            return null;
        case 1:
            return points.get(0);
        case 2:
            return points.get(0).lineTo(points.get(1));
        case 3:
            // return new Triangle2f.Basic(points.get(0), points.get(1),
            // points.get(2));
        }
        return new Polygon2f.Standard(points);
    }

    public void draw(DrawTarget2f target) throws DrawException {
        target.drawPolygon(this);
    }

    // -o ShapeAmount

    public float length() {
        float l = 0;
        int n = pointCount();
        Point2f prev = pointRef(n - 1);
        for (int i = 0; i < n; i++) {
            Point2f p = pointRef(i);
            l += prev.distance(p);
            prev = p;
        }
        return l;
    }

    public float area() {
        Triangle2f[] td = toTriangles();
        float area = 0;
        for (int i = 0; i < td.length; i++)
            area += td[i].area();
        return area;
    }

}
