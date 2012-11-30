package net.bodz.bas.i18n.unit;

public class StdMeasureUnit
        extends MeasureUnit {

    private static final long serialVersionUID = 1L;

    public StdMeasureUnit(String name, String symbol) {
        super(name, symbol);
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
    public double timesOf(MeasureUnit other) {
        if (other.isStd()) {
            return timesOf(other.getStdUnit());
        } else {
            double t = timesOf(other.getStdUnit());
            return t / other.getScale();
        }
    }

    @Override
    public double timesOf(StdMeasureUnit other) {
        if (this == other)
            return 1.0;

        double times = getTimesOf(other);
        if (times != Double.NaN)
            return times;

        double invTimes = other.getTimesOf(this);
        if (invTimes != Double.NaN)
            return 1.0 / invTimes;

        return Double.NaN;
    }

    protected double getTimesOf(StdMeasureUnit other) {
        return Double.NaN;
    }

}
