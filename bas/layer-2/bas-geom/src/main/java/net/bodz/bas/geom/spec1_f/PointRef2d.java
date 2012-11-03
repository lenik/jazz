package net.bodz.bas.geom.spec1_f;

import javax.vecmath.Tuple2f;

public abstract class PointRef2d
        extends AbstractPointRef2d {

    private static final long serialVersionUID = 1L;

    // I_Point2f_

    @Override
    public final PointRef2d add_(Tuple2f t1) {
        this.add(t1);
        return this;
    }

    @Override
    public final PointRef2d sub_(Tuple2f t1) {
        this.sub(t1);
        return this;
    }

    @Override
    public final PointRef2d negate_(Tuple2f t1) {
        this.negate(t1);
        return this;
    }

    @Override
    public final PointRef2d negate_() {
        this.negate();
        return this;
    }

    @Override
    public final PointRef2d scale_(float s) {
        this.scale(s);
        return this;
    }

    @Override
    public final PointRef2d scaleAdd_(float s, Tuple2f t1) {
        this.scaleAdd(s, t1);
        return this;
    }

    @Override
    public final PointRef2d absolute_(Tuple2f t) {
        this.absolute(t);
        return this;
    }

    @Override
    public final PointRef2d clamp_(float min, float max) {
        this.clamp(min, max);
        return this;
    }

    @Override
    public final PointRef2d clampMin_(float min) {
        this.clampMin(min);
        return this;
    }

    @Override
    public final PointRef2d clampMax_(float max) {
        this.clampMax(max);
        return this;
    }

    @Override
    public final PointRef2d absolute_() {
        this.absolute();
        return this;
    }

    @Override
    public final PointRef2d interpolate_(Tuple2f t1, float alpha) {
        this.interpolate(t1, alpha);
        return this;
    }

}
