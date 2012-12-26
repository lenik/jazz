package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasureUnit;
import net.bodz.bas.i18n.unit.IMeasureUnit;

public class ForceUnit
        extends AbstractMeasureUnit
        implements ForceUnits {

    private static final long serialVersionUID = 1L;

    private final double _newtons;

    public ForceUnit(String name, String symbol, double times, ForceUnit base) {
        this(name, symbol, base._newtons * times);
    }

    public ForceUnit(String name, String symbol, double newtons) {
        super(name, symbol);
        this._newtons = newtons;
    }

    @Override
    public double in(IMeasureUnit other) {
        if (other instanceof ForceUnit)
            return in((ForceUnit) other);
        else
            return Double.NaN;
    }

    @Override
    public double per(IMeasureUnit other) {
        if (other instanceof ForceUnit)
            return per((ForceUnit) other);
        else
            return Double.NaN;
    }

    public double in(ForceUnit other) {
        double times = _newtons / other._newtons;
        return times;
    }

    public double per(ForceUnit other) {
        double times = other._newtons / _newtons;
        return times;
    }

    public double newtons() {
        return _newtons;
    }

    public double perNewton() {
        return 1 / _newtons;
    }

}
