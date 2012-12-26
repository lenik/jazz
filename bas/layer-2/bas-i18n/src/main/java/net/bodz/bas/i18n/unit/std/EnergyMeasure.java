package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasure;

public class EnergyMeasure
        extends AbstractMeasure<EnergyUnit>
        implements EnergyUnits {

    private static final long serialVersionUID = 1L;

    public EnergyMeasure(double value, EnergyUnit unit) {
        super(value, unit);
    }

    public EnergyMeasure(double value) {
        super(value);
    }

    @Override
    public EnergyMeasure in(EnergyUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new EnergyMeasure(value * times, otherUnit);
    }

}
