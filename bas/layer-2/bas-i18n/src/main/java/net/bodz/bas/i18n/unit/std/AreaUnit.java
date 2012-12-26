package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasureUnit;
import net.bodz.bas.i18n.unit.IMeasureUnit;


public class AreaUnit
        extends AbstractMeasureUnit
        implements AreaUnits {

    private static final long serialVersionUID = 1L;

    private final double _squareMeters;

    public AreaUnit(String name, String symbol, double times, AreaUnit base) {
        this(name, symbol, base._squareMeters * times);
    }

    public AreaUnit(String name, String symbol, double squareMeters) {
        super(name, symbol);
        this._squareMeters = squareMeters;
    }

    @Override
    public double in(IMeasureUnit other) {
        if (other instanceof AreaUnit)
            return in((AreaUnit) other);
        else
            return Double.NaN;
    }

    @Override
    public double per(IMeasureUnit other) {
        if (other instanceof AreaUnit)
            return per((AreaUnit) other);
        else
            return Double.NaN;
    }

    public double in(AreaUnit other) {
        double times = _squareMeters / other._squareMeters;
        return times;
    }

    public double per(AreaUnit other) {
        double times = other._squareMeters / _squareMeters;
        return times;
    }

    public double squareMeters() {
        return _squareMeters;
    }

    public double perSquareMeter() {
        return 1 / _squareMeters;
    }

}
