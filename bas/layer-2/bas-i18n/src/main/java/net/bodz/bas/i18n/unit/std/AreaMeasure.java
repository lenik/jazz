package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasure;


public class AreaMeasure
        extends AbstractMeasure<AreaUnit>
        implements AreaUnits {

    private static final long serialVersionUID = 1L;

    public AreaMeasure(double value, AreaUnit unit) {
        super(value, unit);
    }

    public AreaMeasure(double value) {
        super(value);
    }

    @Override
    public AreaMeasure in(AreaUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new AreaMeasure(value * times, otherUnit);
    }

}
