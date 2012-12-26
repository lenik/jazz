package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasure;

public class ForceMeasure
        extends AbstractMeasure<ForceUnit>
        implements ForceUnits {

    private static final long serialVersionUID = 1L;

    public ForceMeasure(double value, ForceUnit unit) {
        super(value, unit);
    }

    public ForceMeasure(double value) {
        super(value);
    }

    @Override
    public ForceMeasure in(ForceUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new ForceMeasure(value * times, otherUnit);
    }

}
