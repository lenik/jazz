package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasurement;

public class Volume
        extends AbstractMeasurement<VolumeUnit>
        implements VolumeUnits {

    private static final long serialVersionUID = 1L;

    public Volume(double value, VolumeUnit unit) {
        super(value, unit);
    }

    public Volume(double value) {
        super(value);
    }

    @Override
    public Volume in(VolumeUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new Volume(value * times, otherUnit);
    }

}
