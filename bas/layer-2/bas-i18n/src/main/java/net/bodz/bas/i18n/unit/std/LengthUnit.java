package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasureUnit;
import net.bodz.bas.i18n.unit.IMeasureUnit;


public class LengthUnit
        extends AbstractMeasureUnit
        implements LengthUnits {

    private static final long serialVersionUID = 1L;

    static double FOOT_IN_METERS = 0.3048;
    static double INCH_IN_METERS = FOOT_IN_METERS / 12.0;

    private final double _meters;
    private final double _inches;

    public LengthUnit(String name, String symbol, double times, LengthUnit base) {
        this(name, symbol, base._meters * times);
    }

    public LengthUnit(String name, String symbol, double meters) {
        super(name, symbol);
        this._meters = meters;
        this._inches = meters * INCH_IN_METERS;
    }

    @Override
    public double in(IMeasureUnit other) {
        if (other instanceof LengthUnit)
            return in((LengthUnit) other);
        else
            return Double.NaN;
    }

    @Override
    public double per(IMeasureUnit other) {
        if (other instanceof LengthUnit)
            return per((LengthUnit) other);
        else
            return Double.NaN;
    }

    public double in(LengthUnit other) {
        double times = _meters / other._meters;
        return times;
    }

    public double per(LengthUnit other) {
        double times = other._meters / _meters;
        return times;
    }

    public double meters() {
        return _meters;
    }

    public double perMeter() {
        return 1 / _meters;
    }

    public double inches() {
        return _inches;
    }

    public double perInch() {
        return 1 / _inches;
    }

    public int dots(int dpi) {
        double dots = _inches * dpi;
        return (int) dots; // round...?
    }

    public double forDots(int dpi, int dots) {
        double inches = dots / dpi;
        return inches * per(INCH);
    }

    public int pixels(int ppi) {
        double pixels = _inches * ppi;
        return (int) pixels;
    }

    public double forPixels(int ppi, int pixels) {
        double inches = pixels / ppi;
        return inches * per(INCH);
    }

}
