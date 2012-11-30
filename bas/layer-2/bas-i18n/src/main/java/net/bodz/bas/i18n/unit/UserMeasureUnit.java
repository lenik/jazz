package net.bodz.bas.i18n.unit;

public class UserMeasureUnit
        extends MeasureUnit {

    private static final long serialVersionUID = 1L;

    private final StdMeasureUnit stdUnit;
    private final double scaleToStdUnit;

    public UserMeasureUnit(String name, String symbol, double scale, UserMeasureUnit unit) {
        this(name, symbol, unit.getScale() * scale, unit.getStdUnit());
    }

    public UserMeasureUnit(String name, String symbol, double scale, StdMeasureUnit stdUnit) {
        super(name, symbol);
        this.scaleToStdUnit = scale;
        this.stdUnit = stdUnit;
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
    public double timesOf(MeasureUnit other) {
        if (this == other)
            return 1.0;
        double t = stdUnit.timesOf(other);
        return t * scaleToStdUnit;
    }

    @Override
    public double timesOf(StdMeasureUnit other) {
        double t = stdUnit.timesOf(other);
        return t * scaleToStdUnit;
    }

}
