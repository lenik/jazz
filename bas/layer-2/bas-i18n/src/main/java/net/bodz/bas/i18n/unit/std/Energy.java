package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasurement;

public class Energy
        extends AbstractMeasurement<EnergyUnit>
        implements EnergyUnits {

    private static final long serialVersionUID = 1L;

    public Energy(double value, EnergyUnit unit) {
        super(value, unit);
    }

    public Energy(double value) {
        super(value);
    }

    @Override
    public Energy in(EnergyUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new Energy(value * times, otherUnit);
    }

}
