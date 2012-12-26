package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasureUnit;
import net.bodz.bas.i18n.unit.IMeasureUnit;


public class VolumeUnit
        extends AbstractMeasureUnit
        implements VolumeUnits {

    private static final long serialVersionUID = 1L;

    private final double _cubeMeters;

    public VolumeUnit(String name, String symbol, double times, VolumeUnit base) {
        this(name, symbol, base._cubeMeters * times);
    }

    public VolumeUnit(String name, String symbol, double grams) {
        super(name, symbol);
        this._cubeMeters = grams;
    }

    @Override
    public double in(IMeasureUnit other) {
        if (other instanceof VolumeUnit)
            return in((VolumeUnit) other);
        else
            return Double.NaN;
    }

    @Override
    public double per(IMeasureUnit other) {
        if (other instanceof VolumeUnit)
            return per((VolumeUnit) other);
        else
            return Double.NaN;
    }

    public double in(VolumeUnit other) {
        double times = _cubeMeters / other._cubeMeters;
        return times;
    }

    public double per(VolumeUnit other) {
        double times = other._cubeMeters / _cubeMeters;
        return times;
    }

    public double cubeMeters() {
        return _cubeMeters;
    }

    public double perCubeMeter() {
        return 1 / _cubeMeters;
    }

}
