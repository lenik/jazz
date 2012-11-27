package net.bodz.bas.i18n.util;

public class UserMeasureUnit
        extends MeasureUnit {

    private static final long serialVersionUID = 1L;

    final StdMeasureUnit stdUnit;
    final double scaleToStdUnit;

    public UserMeasureUnit(String name, StdMeasureUnit stdUnit, double scale) {
        super(name);
        this.stdUnit = stdUnit;
        this.scaleToStdUnit = scale;
    }

    @Override
    public boolean isStd() {
        return false;
    }

    @Override
    public StdMeasureUnit getStdUnit() {
        return stdUnit;
    }

    @Override
    public double getScale() {
        return scaleToStdUnit;
    }

    @Override
    public double getScaleTo(MeasureUnit other) {
        if (this == other)
            return 1.0;
        double s0 = stdUnit.getScaleTo(other);
        return s0 * scaleToStdUnit;
    }

    @Override
    public double getScaleTo(StdMeasureUnit other) {
        double s0 = stdUnit.getScaleTo(other);
        return s0 * scaleToStdUnit;
    }

}
