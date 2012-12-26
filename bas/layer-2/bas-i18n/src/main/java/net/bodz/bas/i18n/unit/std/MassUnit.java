package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasureUnit;
import net.bodz.bas.i18n.unit.IMeasureUnit;


public class MassUnit
        extends AbstractMeasureUnit
        implements MassUnits {

    private static final long serialVersionUID = 1L;

    private final double _grams;

    public MassUnit(String name, String symbol, double times, MassUnit base) {
        this(name, symbol, base._grams * times);
    }

    public MassUnit(String name, String symbol, double grams) {
        super(name, symbol);
        this._grams = grams;
    }

    @Override
    public double in(IMeasureUnit other) {
        if (other instanceof MassUnit)
            return in((MassUnit) other);
        else
            return Double.NaN;
    }

    @Override
    public double per(IMeasureUnit other) {
        if (other instanceof MassUnit)
            return per((MassUnit) other);
        else
            return Double.NaN;
    }

    public double in(MassUnit other) {
        double times = _grams / other._grams;
        return times;
    }

    public double per(MassUnit other) {
        double times = other._grams / _grams;
        return times;
    }

    public double grams() {
        return _grams;
    }

    public double perGram() {
        return 1 / _grams;
    }

}
