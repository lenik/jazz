package net.bodz.bas.i18n.util;

public class StdMeasureUnit
        extends MeasureUnit {

    private static final long serialVersionUID = 1L;

    public StdMeasureUnit(String name) {
        super(name);
    }

    @Override
    public boolean isStd() {
        return true;
    }

    @Override
    public StdMeasureUnit getStdUnit() {
        return this;
    }

    @Override
    public double getScale() {
        return 1.0;
    }

    @Override
    public double getScaleTo(MeasureUnit other) {
        if (other.isStd()) {
            return getScaleTo(other.getStdUnit());
        } else {
            double s0 = getScaleTo(other.getStdUnit());
            return s0 / other.getScale();
        }
    }

    @Override
    public double getScaleTo(StdMeasureUnit other) {
        if (this == other)
            return 1.0;

        double scale = getScaleToImpl(other);
        if (scale != Double.NaN)
            return scale;

        double inversedScale = other.getScaleToImpl(this);
        if (inversedScale != Double.NaN)
            return 1.0 / inversedScale;

        return Double.NaN;
    }

    protected double getScaleToImpl(StdMeasureUnit other) {
        return Double.NaN;
    }

}
