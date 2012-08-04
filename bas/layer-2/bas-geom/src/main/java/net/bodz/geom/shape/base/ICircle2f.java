package net.bodz.geom.shape.base;

import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.IShapeAmount2f;

public interface ICircle2f
        extends IShape2f, IShapeAmount2f {

    int SP_BORDER = 0;

    float centerX();

    float centerY();

    float radius();

    void centerX(float x);

    void centerY(float y);

    void radius(float radius);

    StaticPoint2f center();

    void center(IPointRef2d point);

    // -o Shape
    ICircle2f snapshot();

    ICircle2f clone();

    // -o ShapeAmount

    /**
     * Center-Radius
     */
    public static class Static_Cr
            extends AbstractCircle2f {

        public static final int PT_CENTER = 0;

        static final long serialVersionUID = -2013990214675853063L;

        float centerX;
        float centerY;
        float radius;

        public Static_Cr(float cx, float cy, float radius) {
            this.centerX = cx;
            this.centerY = cy;
            this.radius = radius;
        }

        public Static_Cr(IPointRef2d cpoint, float radius) {
            this.centerX = cpoint.x();
            this.centerY = cpoint.y();
            this.radius = radius;
        }

        @Override
        public ICircle2f snapshot() {
            return new Static_Cr(centerX, centerY, radius);
        }

        @Override
        public Static_Cr clone() {
            return new Static_Cr(centerX, centerY, radius);
        }

        public float centerX() {
            return centerX;
        }

        public float centerY() {
            return centerY;
        }

        public float radius() {
            return radius;
        }

        @Override
        public void centerX(float cx) {
            this.centerX = cx;
        }

        @Override
        public void centerY(float cy) {
            this.centerY = cy;
        }

        @Override
        public void radius(float radius) {
            this.radius = radius;
        }

        public int pointCount() {
            return 1;
        }

        public IPointRef2d pointRef(int index) {
            switch (index) {
            case PT_CENTER:
                return new PtCenter();
            }
            return null;
        }

    }

    /**
     * Center-PointOfBorder
     */
    public static class Static_C_POB
            extends AbstractCircle2f {

        static final long serialVersionUID = -5938926745325455815L;
        public static final int PT_CENTER = 0;
        public static final int PT_POB = 1;

        float centerX;
        float centerY;
        float pobDX;
        float pobDY;

        private final class PtPob
                extends AbstractPoint2f {

            static final long serialVersionUID = 5155211937994980628L;

            @Override
            public PtPob clone() {
                return new PtPob();
            }

            @Override
            public void x(float x) {
                pobDX = x - centerX;
            }

            @Override
            public void y(float y) {
                pobDY = y - centerY;
            }

            public float x() {
                return pobDX + centerX;
            }

            public float y() {
                return pobDY + centerY;
            }

        }

        public Static_C_POB(float centerX, float centerY, float pobX, float pobY) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.pobDX = pobX - centerX;
            this.pobDY = pobY - centerY;
        }

        @Override
        public Static_C_POB snapshot() {
            return new Static_C_POB(centerX, centerY, centerX + pobDX, centerY + pobDY);
        }

        @Override
        public Static_C_POB clone() {
            return new Static_C_POB(centerX, centerY, centerX + pobDX, centerY + pobDY);
        }

        public float centerX() {
            return centerX;
        }

        public float centerY() {
            return centerY;
        }

        public float pobX() {
            return pobDX + centerX;
        }

        public float pobY() {
            return pobDY + centerY;
        }

        public float radius() {
            return (float) Math.sqrt(pobDX * pobDX + pobDY * pobDY);
        }

        @Override
        public void centerX(float x) {
            centerX = x;
        }

        @Override
        public void centerY(float y) {
            centerY = y;
        }

        public void pobX(float x) {
            pobDX = x - centerX;
        }

        public void pobY(float y) {
            pobDY = y - centerY;
        }

        @Override
        public void radius(float radius) {
            float angle = (float) Math.atan2(pobDY, pobDX);
            float S = (float) Math.sin(angle);
            float C = (float) Math.cos(angle);
            pobDX = C * radius;
            pobDY = S * radius;
        }

        public int pointCount() {
            return 2;
        }

        public IPointRef2d pointRef(int index) {
            switch (index) {
            case PT_CENTER:
                return new PtCenter();
            case PT_POB:
                return new PtPob();
            }
            return null;
        }
    }

    /**
     * 3-Point Circle
     */
    public static class P3
            extends AbstractCircle2f {

        static final long serialVersionUID = 3403072193043361737L;
        public static final int PT_0 = 0;
        public static final int PT_1 = 1;
        public static final int PT_2 = 2;

        IPointRef2d a;
        IPointRef2d b;
        IPointRef2d c;

        protected final class Pt0
                extends AbstractPoint2f {

            static final long serialVersionUID = -2763473647126920296L;

            public Pt0() {
            }

            @Override
            public Pt0 clone() {
                return new Pt0();
            }

            @Override
            public void x(float x) {
                a.x(x);
            }

            @Override
            public void y(float y) {
                a.y(y);
            }

            public float x() {
                return a.x();
            }

            public float y() {
                return a.y();
            }

        }

        protected final class Pt1
                extends AbstractPoint2f {

            static final long serialVersionUID = -5742275465912617357L;

            public Pt1() {
            }

            @Override
            public Pt1 clone() {
                return new Pt1();
            }

            @Override
            public void x(float x) {
                b.x(x);
            }

            @Override
            public void y(float y) {
                b.y(y);
            }

            public float x() {
                return b.x();
            }

            public float y() {
                return b.y();
            }

        }

        protected final class Pt2
                extends AbstractPoint2f {

            static final long serialVersionUID = -5605416088229255899L;

            public Pt2() {
            }

            @Override
            public Pt2 clone() {
                return new Pt2();
            }

            @Override
            public void x(float x) {
                c.x(x);
            }

            @Override
            public void y(float y) {
                c.y(y);
            }

            public float x() {
                return c.x();
            }

            public float y() {
                return c.y();
            }

        }

        public P3(IPointRef2d a, IPointRef2d b, IPointRef2d c) {
            assert a != null;
            assert b != null;
            assert c != null;
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public P3(IPointRef2d pt[]) {
            assert pt != null;
            assert pt.length >= 3;
            this.a = pt[0];
            this.b = pt[1];
            this.c = pt[2];
        }

        public P3(float aX, float aY, float bX, float bY, float cX, float cY) {
            this(new StaticPoint2f(aX, aY), new StaticPoint2f(bX, bY), new StaticPoint2f(cX, cY));
        }

        public P3(float x[], float y[]) {
            assert x != null;
            assert y != null;
            assert x.length >= 3;
            assert y.length >= 3;

            a = new StaticPoint2f(x[0], y[0]);
            b = new StaticPoint2f(x[1], y[1]);
            c = new StaticPoint2f(x[2], y[2]);
        }

        @Override
        public ICircle2f snapshot() {
            return new P3(a.snapshot(), b.snapshot(), c.snapshot());
        }

        @Override
        public P3 clone() {
            return new P3(a, b, c);
        }

        public float centerX() {
            // TODO
            return Float.NaN;
        }

        public float centerY() {
            // TODO
            return Float.NaN;
        }

        public float radius() {
            // TODO
            return Float.NaN;
        }

        public int pointCount() {
            return 3;
        }

        public IPointRef2d pointRef(int index) {
            switch (index) {
            case PT_0:
                return new Pt0();
            case PT_1:
                return new Pt1();
            case PT_2:
                return new Pt2();
            }
            return null;
        }

    }

    /**
     * In-BoundingBox
     * 
     * @TODO ...
     */
    public static class InBB
            extends AbstractCircle2f {
        static final long serialVersionUID = 3288356430378071947L;
        public static final int PT_P = 0;
        public static final int PT_Q = 1;

        IPointRef2d p;
        IPointRef2d q;

        protected final class PtP
                extends AbstractPoint2f {

            static final long serialVersionUID = 8831559582130716830L;

            public PtP() {
            }

            @Override
            public PtP clone() {
                return new PtP();
            }

            @Override
            public void x(float x) {
                p.x(x);
            }

            @Override
            public void y(float y) {
                p.y(y);
            }

            public float x() {
                return p.x();
            }

            public float y() {
                return p.y();
            }

        }

        protected final class PtQ
                extends AbstractPoint2f {

            static final long serialVersionUID = 6884560320235464133L;

            public PtQ() {
            }

            @Override
            public PtQ clone() {
                return new PtQ();
            }

            @Override
            public void x(float x) {
                p.x(x);
            }

            @Override
            public void y(float y) {
                p.y(y);
            }

            public float x() {
                return p.x();
            }

            public float y() {
                return p.y();
            }

        }

        @Override
        public InBB snapshot() {
            return null;
        }

        @Override
        public AbstractCircle2f clone() {
            return null;
        }

        public float centerX() {
            return 0;
        }

        public float centerY() {
            return 0;
        }

        public float radius() {
            return 0;
        }

        @Override
        public void centerX(float x) {
            super.centerX(x);
        }

        @Override
        public void centerY(float y) {
            super.centerY(y);
        }

        @Override
        public void radius(float radius) {
            super.radius(radius);
        }

        public int pointCount() {
            return 2;
        }

        public IPointRef2d pointRef(int index) {
            switch (index) {
            case PT_P:
                return new PtP();
            case PT_Q:
                return new PtQ();
            }
            return null;
        }

    }
}
