package net.bodz.geom.shape.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.base.PickInfo2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.AbstractShape2f;
import net.bodz.geom.shape.IShape2f;

public abstract class AbstractPolygon2f
        extends AbstractShape2f
        implements IPolygon2f, Cloneable, Serializable {

    static final long serialVersionUID = 2318246123146564098L;

    protected final class PtCenter
            extends AbstractPoint2f {

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

    public StaticPoint2f point(float index) {
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
        ILine2f edge = edge(i);
        return i + edge.index(x, y);
    }

    public float index(IPoint2f point) {
        return index(point.x(), point.y());
    }

    public StaticPoint2f center() {
        return new StaticPoint2f(centerX(), centerY());
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

    public ILine2f edgeRef(int index) {
        int n = pointCount();
        IPoint2f start = pointRef(index++);
        IPoint2f end = pointRef(index % n);
        return start.lineTo(end);
    }

    public ILine2f edgeRef(int index1, int index2) {
        int n = pointCount();
        if (index2 == (index1 + 1) % n)
            return edge(index1);
        IPoint2f p1 = pointRef(index1);
        IPoint2f p2 = pointRef(index2);
        return p1.lineTo(p2);
    }

    public ILine2f.Static edge(int index) {
        int n = pointCount();
        // x()
        IPoint2f start = point(index++);
        IPoint2f end = point(index % n);
        return start.lineTo(end);
    }

    public ILine2f.Static edge(int index1, int index2) {
        int n = pointCount();
        if (index2 == (index1 + 1) % n)
            return edge(index1);
        IPoint2f p1 = point(index1);
        IPoint2f p2 = point(index2);
        return p1.lineTo(p2);
    }

    protected int nearestEdge(float x, float y) {
        int index = 0;
        // Line2f edge;
        float dist = Float.MAX_VALUE;

        int n = pointCount();
        for (int i = 0; i < n; i++) {
            ILine2f e = edge(i);
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
        IPoint2f point = pointRef(start).lineTo(pointRef(end)).point(index - start);
        addPoint(end, point);
    }

    public void addCross(ILine2f line) {
        int n = pointCount();
        if (n < 2)
            return;
        int offset = isClosed() ? n - 1 : n - 2;
        IPoint2f ep = pointRef((offset + 1) % n);
        for (int i = offset; i >= 0; i--) {
            IPoint2f p = pointRef(i);
            ILine2f edge = p.lineTo(ep);
            IPoint2f x = edge.intersectsAt(line);
            if (x != null)
                addPoint(i + 1, x);
            ep = p;
        }
    }

    public List<StaticPoint2f> intersectsAt(ILine2f line, int max) {
        List<StaticPoint2f> list = new ArrayList<StaticPoint2f>();
        int n = pointCount();
        for (int i = 0; i < n; i++)
            if (edge(i).intersectsAt(line) != null) {
                list.add(point(i));
                if (list.size() >= max)
                    break;
            }
        return list;
    }

    public List<Integer> intersectsAtIndex(ILine2f line, int max) {
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

    public List<StaticPoint2f> intersectsAt(ILine2f line) {
        return intersectsAt(line, Integer.MAX_VALUE);
    }

    public List<Integer> intersectsAtIndex(ILine2f line) {
        return intersectsAtIndex(line, Integer.MAX_VALUE);
    }

    public int intersectsCount(ILine2f line, int maxTest) {
        assert maxTest > 0;
        int n = pointCount();
        int c = 0;
        for (int i = 0; i < n; i++)
            if (edge(i).intersectsAt(line) != null)
                if (++c >= maxTest)
                    break;
        return c;
    }

    public final int intersectsCount(ILine2f line) {
        return intersectsCount(line, Integer.MAX_VALUE);
    }

    public boolean intersects(ILine2f line) {
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
        IPoint2f a = pointRef(indexA);
        IPoint2f b = pointRef(indexB);
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
                    ILine2f I = edge(i);
                    ILine2f J = edge(j);
                    IPoint2f x = I.intersectsAt(J);
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

    static final class TdTempPolygon
            extends IPolygon2f.Standard {

        static final long serialVersionUID = 3071647986056763128L;

        List<Integer> selfCuts;

        public TdTempPolygon(List<IPoint2f> points) {
            super(points);
            selfCuts = selfCuts();
        }

        public TdTempPolygon(IPolygon2f polygon) {
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
        public void addPoint(int index, IPoint2f point) {
            super.addPoint(index, point);
            selfCuts = selfCuts();
        }

        @Override
        public void addPoint(int index, float x, float y) {
            addPoint(index, new StaticPoint2f(x, y));
        }

        // for toTriangle, only
        @Override
        public void removePoint(int index) {
            /*
             * int n = pointCount(); Point2f l = point((index + n - 1) % n); Point2f m =
             * point(index); Point2f r = point((index + 1) % n);
             * 
             * Line2f remove1 = l.lineTo(m); Line2f remove2 = m.lineTo(r); Line2f add = l.lineTo(r);
             * 
             * List<Integer> r1 = intersectsAtIndex(remove1); List<Integer> r2 =
             * intersectsAtIndex(remove2); List<Integer> a = intersectsAtIndex(add1);
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

    protected int getTdTempNext(IPolygon2f temp, int tempIndex) {
        /*
         * Next temp index: star-dissection i helix-dissection i + 1 strip-dissection i - n % 2
         */
        return tempIndex;
    }

    public ITriangle2f[] toTriangles() {
        int n = pointCount();
        TdTempPolygon temp = new TdTempPolygon(this);
        List<ITriangle2f> ts = new ArrayList<ITriangle2f>(n - 2);
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
            ITriangle2f t = new ITriangle2f.Static(temp.pointRef(i), temp.pointRef(j), temp.pointRef((j + 1) % n));
            if (t.area() > 0)
                ts.add(t);
            temp.removePoint(j);
            n--;
            j = getTdTempNext(temp, i);
        }
        if (temp.pointCount() > 3) {
            temp.planarize();
            ITriangle2f[] ts2 = temp.toTriangles();
            for (i = 0; i < ts2.length; i++)
                ts.add(ts2[i]);
        }
        return ts.toArray(new ITriangle2f[0]);
    }

    // -o EditablePointSet

    public void setPoint(int index, IPoint2f point) {
        removePoint(index);
        addPoint(index, point);
    }

    public void setPoint(int index, float x, float y) {
        removePoint(index);
        addPoint(index, x, y);
    }

    public abstract void addPoint(int index, float x, float y);

    public void addPoint(int index, IPoint2f point) {
        addPoint(index, point.x(), point.y());
    }

    public final void addPoint(float x, float y) {
        int n = pointCount();
        addPoint(n, x, y);
    }

    public final void addPoint(IPoint2f point) {
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
        ILine2f minEdge = null;

        for (int i = 0; i < n; i++) {
            ILine2f edge = edge(i);
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
    public IPoint2f spointRef(int id) {
        switch (id) {
        case SP_CENTER:
            return new PtCenter();
        }
        return super.spointRef(id);
    }

    @Override
    public IShape2f crop(IPoint2f baseHalfPlane, Vector2f normal) {
        ArrayList<IPoint2f> points = new ArrayList<IPoint2f>();

        int n = pointCount();

        int off = isOpened() ? 0 : n - 1;
        IPoint2f pre = pointRef(off);
        boolean lastOut = pre.crop(baseHalfPlane, normal) == null;

        ILine2f cut = baseHalfPlane.halfPlane(normal);

        for (int i = 0; i < n; i++) {
            IPoint2f p = pointRef(i);
            boolean out = p.crop(baseHalfPlane, normal) == null;
            if (lastOut == out) {
                if (!out)
                    points.add(p);
            } else {
                IPoint2f x = pre.lineTo(p)._intersectsAt(cut);
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
        return new IPolygon2f.Standard(points);
    }

    public void draw(DrawTarget2f target)
            throws DrawException {
        target.drawPolygon(this);
    }

    // -o ShapeAmount

    public float length() {
        float l = 0;
        int n = pointCount();
        IPoint2f prev = pointRef(n - 1);
        for (int i = 0; i < n; i++) {
            IPoint2f p = pointRef(i);
            l += prev.distance(p);
            prev = p;
        }
        return l;
    }

    public float area() {
        ITriangle2f[] td = toTriangles();
        float area = 0;
        for (int i = 0; i < td.length; i++)
            area += td[i].area();
        return area;
    }

}
