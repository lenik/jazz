package net.bodz.geom.shape.base;

import net.bodz.geom.shape.IShape2f;
import net.bodz.geom.shape.IShapeAmount2f;

public interface ITriangle2f
        extends IShape2f, IShapeAmount2f {

    int SP_0 = 1;
    int SP_1 = 2;
    int SP_2 = 3;

    float x0();

    float y0();

    float x1();

    float y1();

    float x2();

    float y2();

    void x0(float x);

    void y0(float y);

    void x1(float x);

    void y1(float y);

    void x2(float x);

    void y2(float y);

    StaticPoint2f p0();

    StaticPoint2f p1();

    StaticPoint2f p2();

    void p0(IPointRef2d p);

    void p1(IPointRef2d p);

    void p2(IPointRef2d p);

    float centerX();

    float centerY();

    IPointRef2d center();

    ICircle2f cIn();

    ICircle2f cEx();

    // -o Shape
    ITriangle2f.Static snapshot();

    ITriangle2f clone();

    public static class Static
            extends AbstractTriangle2f {

        static final long serialVersionUID = -1385583752792452100L;

        public static final int PT_0 = 0;
        public static final int PT_1 = 1;
        public static final int PT_2 = 2;

        float x0;
        float y0;
        float x1;
        float y1;
        float x2;
        float y2;

        public Static(float x0, float y0, float x1, float y1, float x2, float y2) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public Static(IPointRef2d p0, IPointRef2d p1, IPointRef2d p2) {
            this.x0 = p0.x();
            this.y0 = p0.y();
            this.x1 = p1.x();
            this.y1 = p1.y();
            this.x2 = p2.x();
            this.y2 = p2.y();
        }

        @Override
        public AbstractTriangle2f clone() {
            return new Static(x0, y0, x1, y1, x2, y2);
        }

        public float x0() {
            return x0;
        }

        public void x0(float x) {
            this.x0 = x;
        }

        public float x1() {
            return x1;
        }

        public void x1(float x) {
            this.x1 = x;
        }

        public float x2() {
            return x2;
        }

        public void x2(float x) {
            this.x2 = x;
        }

        public float y0() {
            return y0;
        }

        public void y0(float y) {
            this.y0 = y;
        }

        public float y1() {
            return y1;
        }

        public void y1(float y) {
            this.y1 = y;
        }

        public float y2() {
            return y2;
        }

        public void y2(float y) {
            this.y2 = y;
        }

        @Override
        public int pointCount() {
            return 3;
        }

        @Override
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
}
