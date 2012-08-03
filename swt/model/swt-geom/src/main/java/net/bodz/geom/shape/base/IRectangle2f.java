package net.bodz.geom.shape.base;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.IShapeAmount2f;

public interface IRectangle2f
        extends IShape2f, IShapeAmount2f {

    int SP_0 = 0;
    int SP_1 = 1;
    int SP_2 = 2;
    int SP_3 = 3;

    int OUT_LEFT = 1;
    int OUT_TOP = 2;
    int OUT_RIGHT = 4;
    int OUT_BOTTOM = 8;

    float x0();

    float y0();

    float x1();

    float y1();

    float x2();

    float y2();

    float x3();

    float y3();

    void x0(float x0);

    void y0(float y0);

    void x1(float x1);

    void y1(float y1);

    void x2(float x2);

    void y2(float y2);

    void x3(float x3);

    void y3(float y3);

    StaticPoint2f p0();

    StaticPoint2f p1();

    StaticPoint2f p2();

    StaticPoint2f p3();

    void p0(IPoint2f point);

    void p1(IPoint2f point);

    void p2(IPoint2f point);

    void p3(IPoint2f point);

    float width();

    float height();

    void width(float width);

    void height(float height);

    boolean positive();

    void positize();

    void normalize();

    int outcode(float x, float y);

    int outcode(IPoint2f point);

    // -o Shape
    IRectangle2f snapshot();

    IRectangle2f clone();

    // -o ShapeAmount

    public static abstract class StaticOrtho
            extends AbstractRectangle2f {

        private static final long serialVersionUID = -890462479561775L;

        public static final int PT_0 = 0;
        public static final int PT_2 = 1;

        float x0;
        float y0;
        float x2;
        float y2;

        public StaticOrtho(float x0, float y0, float x2, float y2) {
            this.x0 = x0;
            this.y0 = y0;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public AbstractRectangle2f clone() {
            return null;
        }

        public float x0() {
            return x0;
        }

        public void x0(float x0) {
            this.x0 = x0;
        }

        public float x2() {
            return x2;
        }

        public void x2(float x2) {
            this.x2 = x2;
        }

        public float y0() {
            return y0;
        }

        public void y0(float y0) {
            this.y0 = y0;
        }

        public float y2() {
            return y2;
        }

        public void y2(float y2) {
            this.y2 = y2;
        }

        @Override
        public float width() {
            return x2 - x0;
        }

        @Override
        public float height() {
            return y2 - y0;
        }

        @Override
        public void width(float width) {
            x2 = x0 + width;
        }

        @Override
        public void height(float height) {
            y2 = y0 + height;
        }

        public int pointCount() {
            return 4;
        }

        public IPoint2f pointRef(int index) {
            switch (index) {
            case PT_0:
                return new Pt0();
            case PT_2:
                return new Pt2();
            }
            return null;
        }
    }

    /**
     * ^+ (CW) | 1---2 Width x3-x0 | | Height y1-y0 0---3--> +
     */
    public static class StaticLeft
            extends StaticOrtho {
        static final long serialVersionUID = 6554255652557776990L;

        public StaticLeft(float x0, float y0, float x2, float y2) {
            super(x0, y0, x2, y2);
        }

        public StaticLeft(float x0, float y0, Vector2f d2) {
            super(x0, y0, x0 + d2.x, y0 + d2.y);
        }

        public StaticLeft(IPoint2f p0, IPoint2f p2) {
            super(p0.x(), p0.y(), p2.x(), p2.y());
        }

        public StaticLeft(IRectangle2f rectangle) {
            super(rectangle.x0(), rectangle.y0(), rectangle.y2(), rectangle.y2());
        }

        public StaticLeft(IPoint2f p0, Vector2f d2) {
            this(p0, p0.addCopy(d2));
        }

        @Override
        public StaticLeft snapshot() {
            return new StaticLeft(x0, y0, x2, y2);
        }

        @Override
        public StaticLeft clone() {
            return new StaticLeft(x0, y0, x2, y2);
        }

        public float x1() {
            return x0;
        }

        public float y1() {
            return y2;
        }

        public float x3() {
            return x2;
        }

        public float y3() {
            return y0;
        }

        public void x1(float x1) {
            this.x0 = x1;
        }

        public void y1(float y1) {
            this.y2 = y1;
        }

        public void x3(float x3) {
            this.x2 = x3;
        }

        public void y3(float y3) {
            this.y0 = y3;
        }
    }

    /**
     * ^+ (CCW) | 3---2 Width x1-x0 | | Height y3-y0 0---1--> +
     */
    public static class StaticRight
            extends StaticOrtho {

        static final long serialVersionUID = -7617593489424805744L;

        public StaticRight(float x0, float y0, float x2, float y2) {
            super(x0, y0, x2, y2);
        }

        public StaticRight(float x0, float y0, Vector2f d2) {
            super(x0, y0, x0 + d2.x, y0 + d2.y);
        }

        public StaticRight(IPoint2f p0, IPoint2f p2) {
            super(p0.x(), p0.y(), p2.x(), p2.y());
        }

        public StaticRight(IRectangle2f rectangle) {
            super(rectangle.x0(), rectangle.y0(), rectangle.y2(), rectangle.y2());
        }

        public StaticRight(IPoint2f p0, Vector2f d2) {
            this(p0, p0.addCopy(d2));
        }

        @Override
        public StaticRight snapshot() {
            return new StaticRight(x0, y0, x2, y2);
        }

        @Override
        public StaticRight clone() {
            return new StaticRight(x0, y0, x2, y2);
        }

        public float x1() {
            return x2;
        }

        public float y1() {
            return y0;
        }

        public float x3() {
            return x0;
        }

        public float y3() {
            return y2;
        }

        public void x1(float x1) {
            this.x2 = x1;
        }

        public void y1(float y1) {
            this.y0 = y1;
        }

        public void x3(float x3) {
            this.x0 = x3;
        }

        public void y3(float y3) {
            this.y2 = y3;
        }

    }

    /**
     * Tag Interface
     */
    public static interface Positive {
    }

    /**
     * Left with positive width and height.
     */
    public static class LeftPositive
            extends StaticLeft
            implements Positive {

        static final long serialVersionUID = -2122884655929404195L;

        public LeftPositive(float x0, float y0, float x2, float y2) {
            super(x0, y0, x2, y2);
            positize();
        }

        public LeftPositive(float x0, float y0, Vector2f d2) {
            super(x0, y0, d2);
            positize();
        }

        public LeftPositive(IPoint2f p0, IPoint2f p2) {
            super(p0, p2);
            positize();
        }

        public LeftPositive(IPoint2f p0, Vector2f d2) {
            super(p0, d2);
            positize();
        }

        public LeftPositive(IRectangle2f rectangle) {
            this(rectangle.x0(), rectangle.y0(), rectangle.y2(), rectangle.y2());
        }

        @Override
        public LeftPositive clone() {
            return new LeftPositive(x0, y0, x2, y2);
        }
    }

    /**
     * Right with positive width and height.
     */
    public static class RightPositive
            extends StaticRight
            implements Positive {
        static final long serialVersionUID = -4775688543578114356L;

        public RightPositive(float x0, float y0, float x2, float y2) {
            super(x0, y0, x2, y2);
            positize();
        }

        public RightPositive(float x0, float y0, Vector2f d2) {
            super(x0, y0, d2);
            positize();
        }

        public RightPositive(IPoint2f p0, IPoint2f p2) {
            super(p0, p2);
            positize();
        }

        public RightPositive(IPoint2f p0, Vector2f d2) {
            super(p0, d2);
            positize();
        }

        public RightPositive(IRectangle2f rectangle) {
            this(rectangle.x0(), rectangle.y0(), rectangle.y2(), rectangle.y2());
        }

        @Override
        public RightPositive clone() {
            return new RightPositive(x0, y0, x2, y2);
        }
    }

}
