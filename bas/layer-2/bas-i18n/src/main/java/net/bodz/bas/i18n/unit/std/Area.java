package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasurement;

public class Area
        extends AbstractMeasurement<AreaUnit>
        implements AreaUnits {

    private static final long serialVersionUID = 1L;

    public Area(double value, AreaUnit unit) {
        super(value, unit);
    }

    public Area(double value) {
        super(value);
    }

    @Override
    public Area in(AreaUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new Area(value * times, otherUnit);
    }

}
