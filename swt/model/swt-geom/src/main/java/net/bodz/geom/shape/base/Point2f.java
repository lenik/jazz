package net.bodz.geom.shape.base;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.shape.Shape2f;

public interface Point2f
        extends Shape2f {

    float x();

    float y();

    void x(float x);

    void y(float y);

    void add(float dx, float dy);

    void add(Vector2f dv);

    void sub(float dx, float dy);

    void sub(Vector2f dv);

    Point2f toAdd(float dx, float dy);

    Point2f toAdd(Vector2f dv);

    Point2f toSub(float dx, float dy);

    Point2f toSub(Vector2f dv);

    Vector2f vectorFrom(float startX, float startY);

    Vector2f vectorFrom(Point2f start);

    Vector2f vectorTo(float endX, float endY);

    Vector2f vectorTo(Point2f end);

    Line2f lineFromRef(Point2f start);

    Line2f lineToRef(Point2f end);

    Line2f.Static lineFrom(Point2f start);

    Line2f.Static lineTo(Point2f end);

    Line2f.Static lineTo(Vector2f dv);

    Line2f.Static halfPlane(Vector2f normal);

    float distanceSquared(Point2f point);

    float distanceSquared(float x, float y);

    boolean inside(Point2f base, Vector2f normal);

    // -o Pick

    // -o Shape
    void translate(float dx, float dy);

    void scale(float kx, float ky);

    void rotate(float angle);

    void scale(float kx, float ky, float baseX, float baseY);

    void rotate(float angle, float baseX, float baseY);

    Point2f crop(Point2f base, Vector2f normal);

    Point2f.Static snapshot();

    Point2f clone();

    public static class Static
            extends AbstractPoint2f {
        static final long serialVersionUID = -966532598582919425L;

        public float x;
        public float y;

        public Static(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public Static(Vector2f v) {
            this.x = v.x;
            this.y = v.y;
        }

        public Static(Point2f point) {
            this.x = point.x();
            this.y = point.y();
        }

        public Static(Point2f.Static point) {
            this.x = point.x;
            this.y = point.y;
        }

        public final float x() {
            return x;
        }

        public final float y() {
            return y;
        }

        @Override
        public final void x(float x) {
            this.x = x;
        }

        @Override
        public final void y(float y) {
            this.y = y;
        }

        @Override
        public final int pointCount() {
            return 1;
        }

        @Override
        public final Point2f pointRef(int index) {
            if (index == 0)
                return this;
            return null;
        }

        @Override
        public final Static clone() {
            return new Static(x, y);
        }
    }
}
