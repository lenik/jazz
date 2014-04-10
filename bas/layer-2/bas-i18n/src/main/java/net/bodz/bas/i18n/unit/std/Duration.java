package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasurement;

public class Duration
        extends AbstractMeasurement<TimeUnit>
        implements DurationUnits {

    private static final long serialVersionUID = 1L;

    public Duration(double value, TimeUnit unit) {
        super(value, unit);
    }

    public Duration(double value) {
        super(value);
    }

    @Override
    public Duration in(TimeUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new Duration(value * times, otherUnit);
    }

}
