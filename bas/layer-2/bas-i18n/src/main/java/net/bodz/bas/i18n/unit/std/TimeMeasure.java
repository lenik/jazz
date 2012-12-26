package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasure;

public class TimeMeasure
        extends AbstractMeasure<TimeUnit>
        implements TimeUnits {

    private static final long serialVersionUID = 1L;

    public TimeMeasure(double value, TimeUnit unit) {
        super(value, unit);
    }

    public TimeMeasure(double value) {
        super(value);
    }

    @Override
    public TimeMeasure in(TimeUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new TimeMeasure(value * times, otherUnit);
    }

}
