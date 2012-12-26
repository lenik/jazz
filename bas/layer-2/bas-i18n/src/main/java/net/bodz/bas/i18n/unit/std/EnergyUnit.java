package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasureUnit;
import net.bodz.bas.i18n.unit.IMeasureUnit;

public class EnergyUnit
        extends AbstractMeasureUnit
        implements EnergyUnits {

    private static final long serialVersionUID = 1L;

    private final double _joule;

    public EnergyUnit(String name, String symbol, double times, EnergyUnit base) {
        this(name, symbol, base._joule * times);
    }

    public EnergyUnit(String name, String symbol, double joules) {
        super(name, symbol);
        this._joule = joules;
    }

    @Override
    public double in(IMeasureUnit other) {
        if (other instanceof EnergyUnit)
            return in((EnergyUnit) other);
        else
            return Double.NaN;
    }

    @Override
    public double per(IMeasureUnit other) {
        if (other instanceof EnergyUnit)
            return per((EnergyUnit) other);
        else
            return Double.NaN;
    }

    public double in(EnergyUnit other) {
        double times = _joule / other._joule;
        return times;
    }

    public double per(EnergyUnit other) {
        double times = other._joule / _joule;
        return times;
    }

    public double joules() {
        return _joule;
    }

    public double perJoule() {
        return 1 / _joule;
    }

}
