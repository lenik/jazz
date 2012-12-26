package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasureUnit;
import net.bodz.bas.i18n.unit.IMeasureUnit;

public class TimeUnit
        extends AbstractMeasureUnit
        implements TimeUnits {

    private static final long serialVersionUID = 1L;

    private final double _seconds;

    public TimeUnit(String name, String symbol, double times, TimeUnit base) {
        this(name, symbol, base._seconds * times);
    }

    public TimeUnit(String name, String symbol, double seconds) {
        super(name, symbol);
        this._seconds = seconds;
    }

    @Override
    public double in(IMeasureUnit other) {
        if (other instanceof TimeUnit)
            return in((TimeUnit) other);
        else
            return Double.NaN;
    }

    @Override
    public double per(IMeasureUnit other) {
        if (other instanceof TimeUnit)
            return per((TimeUnit) other);
        else
            return Double.NaN;
    }

    public double in(TimeUnit other) {
        double times = _seconds / other._seconds;
        return times;
    }

    public double per(TimeUnit other) {
        double times = other._seconds / _seconds;
        return times;
    }

    public double seconds() {
        return _seconds;
    }

    public double perSecond() {
        return 1 / _seconds;
    }

}
