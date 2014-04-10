package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasurement;

public class Force
        extends AbstractMeasurement<ForceUnit>
        implements ForceUnits {

    private static final long serialVersionUID = 1L;

    public Force(double value, ForceUnit unit) {
        super(value, unit);
    }

    public Force(double value) {
        super(value);
    }

    @Override
    public Force in(ForceUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new Force(value * times, otherUnit);
    }

}
