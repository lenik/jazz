package net.bodz.geom.shape.base;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.types.Pair;
import net.bodz.geom.base.ReadOnlyException;
import net.bodz.geom.shape.EditablePointSet2f;
import net.bodz.geom.shape.Shape2f;
import net.bodz.geom.shape.ShapeAmount2f;

public interface Polygon2f extends Shape2f, ShapeAmount2f, EditablePointSet2f {

    float pointX(int index);

    float pointY(int index);

    Point2f.Static point(float index);

    float pointX(float index);

    float pointY(float index);

    float index(Point2f point);

    float index(float x, float y);

    Point2f.Static center();

    float centerX();

    float centerY();

    Line2f edgeRef(int index);

    Line2f edgeRef(int index1, int index2);

    Line2f.Static edge(int index);

    Line2f.Static edge(int index1, int index2);

    /**
     * Open/close is only affect to following operations: addCross draw
     */
    boolean isOpened();

    boolean isClosed();

    void open();

    void close();

    void addIndex(float index);

    void addCross(Line2f line);

    List<Point2f.Static> intersectsAt(Line2f line);

    List<Integer> intersectsAtIndex(Line2f line);

    int intersectsCount(Line2f line);

    boolean intersects(Line2f line);

    /**
     * If the polygon has 0, 1, 2 points, or not planarized, then the direction
     * is N/A.
     */
    int N_A = 0;

    int CW  = -1;
    int CCW = 1;

    int direction();

    void reverse();

    void planarize();

    Triangle2f[] toTriangles();

    // -o EditablePointSet

    // -o Shape
    Polygon2f clone();

    // -o ShapeAmount

    /**
     * Not Static Sizable
     */
    public static class Standard extends AbstractPolygon2f {

        static final long    serialVersionUID = -2531595816721898308L;

        public List<Point2f> points;

        public Standard() {
            this.points = new ArrayList<Point2f>();
        }

        public Standard(List<Point2f> points, boolean copy) {
            if (points == null)
                this.points = new ArrayList<Point2f>();
            else if (copy) {
                this.points = new ArrayList<Point2f>();
                for (Point2f pt : points) {
                    this.points.add(pt.clone());
                }
            } else {
                this.points = points;
            }
        }

        public Standard(List<Point2f> points) {
            this(points, false);
        }

        @Override
        public Standard snapshot() {
            int n = this.points.size();
            List<Point2f> points = new ArrayList<Point2f>(n);
            for (int i = 0; i < n; i++)
                points.add(this.points.get(i).snapshot());
            return new Standard(points);
        }

        @Override
        public Standard clone() {
            return new Standard(this.points, true);
        }

        public int pointCount() {
            return points.size();
        }

        public Point2f pointRef(int index) {
            assert index >= 0 && index < points.size();
            return points.get(index);
        }

        @Override
        public void addPoint(int index, Point2f point) {
            points.add(index, point);
        }

        @Override
        public void addPoint(int index, float x, float y) {
            points.add(index, new Point2f.Static(x, y));
        }

        @Override
        public void removePoint(int index) {
            points.remove(index);
        }
    }

    /**
     * Static Not Sizable
     */
    public static class StaticFixed extends AbstractPolygon2f {

        static final long serialVersionUID = 2860113364931775745L;

        public float      x[];
        public float      y[];
        public int        offset;
        public int        count;

        public StaticFixed(float x[], float y[], int offset, int count) {
            assert x != null;
            assert y != null;
            assert x.length <= y.length;
            this.x = x;
            this.y = y;
            this.offset = offset;
            this.count = count;
        }

        public StaticFixed(float x[], float y[], int offset) {
            assert x != null;
            assert y != null;
            assert x.length <= y.length;
            this.x = x;
            this.y = y;
            this.offset = offset;
            this.count = x.length - offset;
        }

        public StaticFixed(float x[], float y[]) {
            assert x != null;
            assert y != null;
            assert x.length <= y.length;
            this.x = x;
            this.y = y;
            this.offset = 0;
            this.count = x.length;
        }

        public StaticFixed(Pair<float[], float[]> init) {
            this(init.first, init.second);
        }

        public StaticFixed(Point2f[] points) {
            assert points != null;
            assert points.length > 0;
            x = new float[points.length];
            y = new float[points.length];
            for (int i = 0; i < points.length; i++) {
                x[i] = points[i].x();
                y[i] = points[i].y();
            }
            offset = 0;
            count = points.length;
        }

        public StaticFixed(float x0, float y0, float... xylist) {
            assert xylist != null;
            assert xylist.length % 2 == 0;
            int n = 1 + xylist.length / 2;
            x = new float[n];
            y = new float[n];
            x[0] = x0;
            y[0] = y0;
            for (int i = 1, j = 0; i < n; i++) {
                x[i] = xylist[j++];
                y[i] = xylist[j++];
            }
        }

        @Override
        public Shape2f snapshot() {
            return clone();
        }

        @Override
        public StaticFixed clone() {
            return new StaticFixed(x, y);
        }

        public int pointCount() {
            return count;
        }

        // TODO
        public Point2f pointRef(int index) {
            assert index >= 0 && index < count;
            return new Point2f.Static(x[offset + index], y[offset + index]);
        }

        @Override
        public void addPoint(int index, float x, float y) {
            throw new ReadOnlyException();
        }

        @Override
        public void removePoint(int index) {
            throw new ReadOnlyException();
        }

    }

    // By Segments
    public static class SampleEllipse extends StaticFixed {

        static final long serialVersionUID = -3404181840736093596L;
        static final int  DEFAULT_SEGMENTS = 100;

        public SampleEllipse(float x0, float y0, float x1, float y1, int segments, float angleOffset) {
            super(sample(x0, y0, x1, y1, segments, angleOffset));
        }

        public SampleEllipse(float x0, float y0, float x1, float y1, int segments) {
            this(x0, y0, x1, y1, segments, 0.0f);
        }

        public SampleEllipse(float x0, float y0, float x1, float y1) {
            this(x0, y0, x1, y1, DEFAULT_SEGMENTS, 0.0f);
        }

        static Pair<float[], float[]> sample(float x0, float y0, float x1, float y1, int segments,
                float angleOffset) {
            float cx = (x0 + x1) / 2;
            float cy = (y0 + y1) / 2;
            float rx = Math.abs((x1 - x0) / 2);
            float ry = Math.abs((y1 - y0) / 2);
            float[] xset = new float[segments];
            float[] yset = new float[segments];
            float a = angleOffset;
            float delta = (float) Math.PI * 2 / segments;
            for (int i = 0; i < segments; i++) {
                xset[i] = cx + rx * (float) Math.cos(a);
                yset[i] = cy + ry * (float) Math.sin(a);
                a = a + delta;
            }
            return new Pair<float[], float[]>(xset, yset);
        }
    }
}
