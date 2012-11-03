package net.bodz.bas.geom.spec1_f;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.geom.spec0_f.AbstractPrimitive2d;
import net.bodz.bas.geom.spec0_f.CurveDirection;
import net.bodz.bas.geom.spec0_f.IPointSet2d;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.PickResult2d;
import net.bodz.bas.geom.spec0_f.PositiveHalfPlane2d;

public abstract class AbstractPolygon2d
        extends AbstractPrimitive2d
        implements IPolygon2d {

    private static final long serialVersionUID = 1L;

    private boolean closed;

    @Override
    public abstract AbstractPolygon2d shot();

    @Override
    public Polygon2d snapshot() {
        List<Point2d> newList = new ArrayList<Point2d>(getPointCount());
        for (Point2d p : getPoints())
            newList.add(p.snapshot());
        return new Polygon2d(newList);
    }

    @Override
    public Polygon2d snap() {
        List<Point2d> newList = new ArrayList<Point2d>(getPointCount());
        for (Point2d p : getPoints())
            newList.add(p);
        return new Polygon2d(newList);
    }

    private static boolean _fullCheck = false;

    @Override
    public boolean isValid() {
        int n = getPointCount();
        if (n <= 1)
            return false;

        if (!_fullCheck)
            return true;

        Point2d point0 = getPoint(0);
        for (int i = 1; i < n; i++) {
            Point2d point = getPoint(i);
            if (!point0.equals(point, EPSILON))
                return true;
        }
        return false;
    }

    @Override
    public Point2d degenerate() {
        int n = getPointCount();
        if (n == 0)
            return null;

        Point2d point0 = getPoint(0);
        if (n == 1)
            return point0;

        for (int i = 1; i < n; i++) {
            Point2d point = getPoint(i);
            if (!point0.equals(point, EPSILON))
                return null;
        }
        return point0;
    }

    // -o IShapeAmount2d

    @Override
    public float getLength() {
        float l = 0;
        int n = getPointCount();
        Point2d prev = getPoint(n - 1);
        for (int i = 0; i < n; i++) {
            Point2d p = getPoint(i);
            l += prev.distance(p);
            prev = p;
        }
        return l;
    }

    @Override
    public float getArea() {
        ITriangle2d[] td = toTriangleArray();
        float area = 0;
        for (int i = 0; i < td.length; i++)
            area += td[i].getArea();
        return area;
    }

    //

    @Override
    public final boolean isOpened() {
        return !closed;
    }

    @Override
    public final boolean isClosed() {
        return closed;
    }

    @Override
    public final void open() {
        closed = false;
    }

    @Override
    public final void close() {
        closed = true;
    }

    @Override
    public CurveDirection getDirection() {
        return getDirection(false);
    }

    public CurveDirection getDirection(boolean fullCheck) {
        int n = getPointCount();
        if (n <= 1)
            return null;

        Point2d prev2 = getPoint(0);
        Point2d prev1 = null;
        float prevArg;
        int i = 1;
        while (i < n) {
            Point2d point = getPoint(i++);
            if (prev2.equals(point)) // equalsEpisilon here?
                continue;
            prev1 = point;
            break;
        }
        if (prev1 == null)
            return null;
        prevArg = prev2.crossProduct(prev1); // |prev1| * |prev2| * sin(a, b)

        CurveDirection direction = null;
        while (i < n) {
            Point2d point = getPoint(i);
            if (point.equals(prev1)) // equalsEpisilon here?
                continue;

            float arg = prev1.crossProduct(point);

            prev2 = prev1;
            prev1 = point;
        }
        return direction;
    }

    @Override
    public void reverse() {
        reverse(0, getPointCount() - 1);
    }

    public void reverse(int begin, int end) {
        int n = end - begin + 1;
        for (int i = 0; i < n / 2; i++) {
            swapPoint(begin + i, end - i);
        }
    }

    @Override
    public Line2d getEdge(int index) {
        int n = getPointCount();
        index %= n;
        Point2d start = getPoint(index++);
        Point2d end = getPoint(index % n);
        return start.lineTo(end);
    }

    @Override
    public Line2d getEdge(int index1, int index2) {
        int n = getPointCount();
        index1 %= n;
        index2 %= n;
        Point2d p1 = getPoint(index1);
        Point2d p2 = getPoint(index2);
        return p1.lineTo(p2);
    }

    @Override
    public Link2d getLink(int index) {
        int n = getPointCount();
        index %= n;
        IPointRef2d start = getPointRef(index++);
        IPointRef2d end = getPointRef(index % n);
        return start.linkTo(end);
    }

    @Override
    public Link2d getLink(int index1, int index2) {
        int n = getPointCount();
        index1 %= n;
        index2 %= n;
        IPointRef2d start = getPointRef(index1);
        IPointRef2d end = getPointRef(index2);
        return start.linkTo(end);
    }

    @Override
    public Point2d getPointLinear(float index) {
        int start = (int) Math.floor(index);
        int end = (int) Math.ceil(index);
        if (start == end)
            return getPoint(start);
        Line2d edge = getEdge(start);
        return edge.getPointLinear(index - start);
    }

    @Override
    public boolean isIntersected(Line2d line) {
        return getIntersectionCount(line, 1) > 0;
    }

    public final int getIntersectionCount(Line2d line) {
        return getIntersectionCount(line, Integer.MAX_VALUE);
    }

    public int getIntersectionCount(Line2d line, int maxTest) {
        assert maxTest > 0;
        int n = getPointCount();
        int c = 0;
        for (int i = 0; i < n; i++)
            if (getEdge(i).getIntersection(line) != null)
                if (++c >= maxTest)
                    break;
        return c;
    }

    @Override
    public List<Point2d> getIntersectionLinear(Line2d line) {
        return getIntersectionLinear(line, Integer.MAX_VALUE);
    }

    // @Override
    public List<Point2d> getIntersectionLinear(Line2d line, int max) {
        List<Point2d> list = new ArrayList<Point2d>();
        int n = getPointCount();
        for (int i = 0; i < n; i++) {
            Point2d x = getEdge(i).getIntersection(line);
            if (x != null) {
                // list.add(getPoint(i));
                list.add(x);
                if (list.size() >= max)
                    break;
            }
        }
        return list;
    }

    @Override
    public List<Float> getIntersectionIndexesLinear(Line2d line) {
        return getIntersectionIndexesLinear(line, Integer.MAX_VALUE);
    }

    public List<Float> getIntersectionIndexesLinear(Line2d line, int max) {
        List<Float> list = new ArrayList<Float>();
        int n = getPointCount();
        for (int index = 0; index < n; index++) {
            Line2d edge = getEdge(index);
            Point2d s = edge.getIntersection(line);
            if (s != null) {
                float _index = edge.index(s.x, s.y);
                list.add(_index);
                if (list.size() >= max)
                    break;
            }
        }
        return list;
    }

    @Override
    public Point2d refineLinear(float index) {
        int start = (int) Math.floor(index);
        int end = (int) Math.ceil(index);

        Point2d startPoint = getPoint(start);
        if (start == end)
            return startPoint;

        Point2d endPoint = getPoint(end);
        Point2d point = startPoint.lineTo(endPoint).getPointLinear(index - start);
        addPoint(end, point);
        return point;
    }

    @Override
    public List<Point2d> refineLinear(Line2d line) {
        List<Point2d> points = new ArrayList<Point2d>();

        int n = getPointCount();
        if (n < 2)
            return points;

        int offset = isClosed() ? n - 1 : n - 2;
        Point2d ep = getPoint((offset + 1) % n);
        for (int i = offset; i >= 0; i--) {
            Point2d p = getPoint(i);
            ILine2d edge = p.lineTo(ep);
            Point2d x = edge.getIntersection(line);
            if (x == null) {
                points.add(p);
            } else {
                addPoint(i + 1, x);
                points.add(x);
            }
            ep = p;
        }
        return points;
    }

    protected int getNearestEdgeLinear(Point2d point) {
        int index = 0;
        // Line2f edge;
        float dist = Float.MAX_VALUE;

        int n = getPointCount();
        for (int i = 0; i < n; i++) {
            Line2d edge = getEdge(i);
            float d = edge.distance(point);
            if (d < dist) {
                index = i;
                // edge = e;
                dist = d;
            }
        }
        return index;
    }

    protected void swapPoint(int index1, int index2) {
        if (index1 == index2)
            return;
        Point2d a = getPoint(index1);
        Point2d b = getPoint(index2);
        setPoint(index1, b);
        setPoint(index2, a);
    }

    @SuppressWarnings("unused")
    public void planarize() {
        int n = getPointCount();
        if (isOpened())
            n--;
        if (n < 4)
            return;

        entire: while (true) {
            edge: for (int i = 2; i < n; i++) {
                findcut: for (int j = 0; j < i - 1; j++) {
                    Line2d I = getEdge(i);
                    Line2d J = getEdge(j);
                    Point2d x = I.getIntersection(J);
                    if (x == null)
                        continue;
                    assert j < i;
                    // j-+-j'-----------i-+-i'
                    // +----------------+
                    // j-x-i-----------j'-x-i'
                    addPoint(i + 1, x.shot());
                    addPoint(j + 1, x.shot());
                    // reverse [j + 2 .. i]
                    reverse(j + 2, i);
                    n += 2;
                    continue entire;
                }
            }
            break;
        }
    }

    static final class TdTempPolygon
            extends Polygon2d {

        private static final long serialVersionUID = 1L;

        List<Integer> selfCuts;

        public TdTempPolygon(List<Point2d> points) {
            super(points);
            selfCuts = selfCuts();
        }

        public TdTempPolygon(IPointSet2d points) {
            // don't copy the content of points.
            super(points.toPointList(false));
        }

        public int getIntersectionCount(int index) {
            return selfCuts.get(index);
        }

        public int getIntersectionCount(int i, int j) {
            return getIntersectionCount(getEdge(i, j));
        }

        // for planarize, only
        @Override
        public void addPoint(int index, Point2d point) {
            super.addPoint(index, point);
            selfCuts = selfCuts();
        }

        // for toTriangle, only
        @Override
        public Point2d removePoint(int index) {

            // int n = getPointCount();
            // Point2d l = getPoint((index + n - 1) % n);
            // Point2d m = getPoint(index);
            // Point2d r = getPoint((index + 1) % n);
            //
            // Line2d remove1 = l.lineTo(m);
            // Line2d remove2 = m.lineTo(r);
            // Line2d add = l.lineTo(r);
            //
            // List<Float> r1 = getIntersectionIndexesLinear(remove1);
            // List<Float> r2 = getIntersectionIndexesLinear(remove2);
            // List<Float> a = getIntersectionIndexesLinear(add1);

            Point2d removed = super.removePoint(index);
            selfCuts = selfCuts();
            return removed;
        }

        List<Integer> selfCuts() {
            int n = getPointCount();
            int[] x = new int[n];
            for (int i = 2; i < n; i++)
                for (int j = 0; j < i - 1; j++)
                    if (getEdge(i).isIntersected(getEdge(j))) {
                        x[i]++;
                        x[j]++;
                    }
            List<Integer> cuts = new ArrayList<Integer>(n);
            for (int i = 0; i < n; i++)
                cuts.add(x[i]);
            return cuts;
        }
    }

    protected int getTdTempNext(IPolygon2d temp, int tempIndex) {
        /*
         * Next temp index: star-dissection i helix-dissection i + 1 strip-dissection i - n % 2
         */
        return tempIndex;
    }

    @Override
    public Triangle2d[] toTriangleArray() {
        int n = getPointCount();
        TdTempPolygon temp = new TdTempPolygon(this);
        List<Triangle2d> ts = new ArrayList<Triangle2d>(n - 2);
        int i;
        int j = 0;
        int skips = 0;
        int ints;
        while (n > 2) {
            i = j++;
            j %= n;
            ints = temp.getIntersectionCount(i);
            if (i > 0) {
                if (++skips == n)
                    break;
                continue;
            }
            ints = temp.getIntersectionCount(j);
            if (ints > 0) {
                i = j++;
                j %= n;
                if ((skips += 2) >= n)
                    break;
                continue;
            }
            skips = 0;
            Triangle2d t = new Triangle2d(//
                    temp.getPoint(i), temp.getPoint(j), temp.getPoint((j + 1) % n));
            if (t.getArea() > 0)
                ts.add(t);
            temp.removePoint(j);
            n--;
            j = getTdTempNext(temp, i);
        }
        if (temp.getPointCount() > 3) {
            temp.planarize();
            Triangle2d[] ts2 = temp.toTriangleArray();
            for (i = 0; i < ts2.length; i++)
                ts.add(ts2[i]);
        }
        return ts.toArray(new Triangle2d[0]);
    }

    // -o IPickable2d

    @Override
    public PickResult2d _pick(Point2d point) {
        int n = getPointCount();
        if (n == 0)
            return null;

        float minDist = java.lang.Float.MAX_VALUE;
        ILine2d minEdge = null;

        for (int i = 0; i < n; i++) {
            ILine2d edge = getEdge(i);
            float dist = edge.distance(point); // always >= 0
            if (dist < minDist) {
                minDist = dist;
                minEdge = edge;
            }
        }

        assert minEdge != null;
        return new PickResult2d(minEdge, minDist);
    }

    @Override
    public float distance(Point2d point) {
        PickResult2d result = _pick(point);
        if (result == null)
            return Float.POSITIVE_INFINITY;
        else
            return result.getDistance();
    }

    // -o IPolygonizable2d

    @Override
    public Polygon2d polygonize() {
        return snapshot();
    }

    // -o ICroppable2d

    @Override
    public IPrimitive2d crop(PositiveHalfPlane2d php, boolean detached) {
        ArrayList<Point2d> points = new ArrayList<Point2d>();

        int n = getPointCount();

        int off = isOpened() ? 0 : n - 1;

        // The initial prev-point is the last point.
        Point2d prev = getPoint(off);
        boolean lastOut = prev.crop(php, detached) == null;

        for (int i = 0; i < n; i++) {
            Point2d p = getPoint(i);
            boolean out = p.crop(php, detached) == null;
            if (lastOut == out) {
                if (!out)
                    points.add(p);
            } else {
                Point2d x = prev.lineTo(p).getIntersectionExtended(php);
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
        return new Polygon2d(points);
    }

}
