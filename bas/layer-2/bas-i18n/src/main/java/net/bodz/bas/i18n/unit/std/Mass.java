package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasurement;

public class Mass
        extends AbstractMeasurement<MassUnit>
        implements MassUnits {

    private static final long serialVersionUID = 1L;

    public Mass(double value, MassUnit unit) {
        super(value, unit);
    }

    public Mass(double value) {
        super(value);
    }

    @Override
    public Mass in(MassUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new Mass(value * times, otherUnit);
    }

}
