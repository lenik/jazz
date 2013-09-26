package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasure;

public class MassMeasure
        extends AbstractMeasure<MassUnit>
        implements MassUnits {

    private static final long serialVersionUID = 1L;

    public MassMeasure(double value, MassUnit unit) {
        super(value, unit);
    }

    public MassMeasure(double value) {
        super(value);
    }

    @Override
    public MassMeasure in(MassUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new MassMeasure(value * times, otherUnit);
    }

}
