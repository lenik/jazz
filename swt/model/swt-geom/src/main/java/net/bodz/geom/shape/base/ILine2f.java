package net.bodz.geom.shape.base;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.IShapeAmount2f;

public interface ILine2f
        extends IShape2f, IShapeAmount2f {

    int SP_0 = 1;
    int SP_1 = 2;

    float x0();

    float y0();

    float x1();

    float y1();

    void x0(float x0);

    void y0(float y0);

    void x1(float x1);

    void y1(float y1);

    StaticPoint2f p0();

    StaticPoint2f p1();

    void p0(IPoint2f p0);

    void p1(IPoint2f p1);

    float width();

    float height();

    /**
     * intersect X-axis at
     */
    float x();

    /**
     * intersect Y-axis at
     */
    float y();

    /**
     * The component k in y = kx + b
     */
    float gradient();

    // the length of the line is also the outline-length of the shape.
    float length();

    float angle();

    StaticPoint2f point(float index);

    float pointX(float index);

    float pointY(float index);

    float index(float x, float y);

    float index(IPoint2f point);

    float indexX(float x);

    float indexY(float y);

    StaticPoint2f _intersectsAt(ILine2f line);

    boolean _intersects(ILine2f line);

    StaticPoint2f intersectsAt(ILine2f line);

    boolean intersects(ILine2f line);

    Vector2f toVector();

    Vector2f getNormal();

    boolean containsHP(IPoint2f point);

    // -o Shape
    float distanceExtended(float x, float y);

    float distanceExtended(IPoint2f point);

    ILine2f crop(IPoint2f base, Vector2f vector);

    ILine2f crop(IRectangle2f rect);

    ILine2f crop(float x0, float y0, float x1, float y1);

    ILine2f.Static snapshot();

    ILine2f clone();

    // -o ShapeAmount

    static class Static
            extends AbstractLine2f {

        public static final int PT_0 = 0;
        public static final int PT_1 = 1;

        static final long serialVersionUID = 7790957543115400772L;

        float x0;
        float y0;
        float x1;
        float y1;

        public Static(float x0, float y0, float x1, float y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
        }

        public Static(IPoint2f p0, IPoint2f p1) {
            this.x0 = p0.x();
            this.y0 = p0.y();
            this.x1 = p1.x();
            this.y1 = p1.y();
        }

        @Override
        public Static clone() {
            return new Static(x0, y0, x1, y1);
        }

        @Override
        public final float x0() {
            return x0;
        }

        @Override
        public final float y0() {
            return y0;
        }

        @Override
        public final float x1() {
            return x1;
        }

        @Override
        public final float y1() {
            return y1;
        }

        @Override
        public final void x0(float x0) {
            this.x0 = x0;
        }

        @Override
        public final void y0(float y0) {
            this.y0 = y0;
        }

        @Override
        public final void x1(float x1) {
            this.x1 = x1;
        }

        @Override
        public final void y1(float y1) {
            this.y1 = y1;
        }

        @Override
        public final int pointCount() {
            return 1;
        }

        @Override
        public IPoint2f pointRef(int index) {
            switch (index) {
            case PT_0:
                return new Pt0();
            case PT_1:
                return new Pt1();
            }
            return null;
        }
    }

    public class Ref
            extends AbstractLine2f {

        static final long serialVersionUID = -5269902095199829937L;

        public static final int PT_0 = 0;
        public static final int PT_1 = 1;

        IPoint2f p0;
        IPoint2f p1;

        public Ref(IPoint2f p0, IPoint2f p1) {
            assert p0 != null;
            assert p1 != null;
            this.p0 = p0;
            this.p1 = p1;
        }

        @Override
        public Static snapshot() {
            return new Static(p0.snapshot(), p1.snapshot());
        }

        @Override
        public Ref clone() {
            return new Ref(p0, p1);
        }

        @Override
        public float x0() {
            return p0.x();
        }

        @Override
        public float y0() {
            return p0.y();
        }

        @Override
        public float x1() {
            return p1.x();
        }

        @Override
        public float y1() {
            return p1.y();
        }

        @Override
        public void p0(IPoint2f p0) {
            this.p0 = p0;
        }

        @Override
        public void p1(IPoint2f p1) {
            this.p1 = p1;
        }

    }
}
