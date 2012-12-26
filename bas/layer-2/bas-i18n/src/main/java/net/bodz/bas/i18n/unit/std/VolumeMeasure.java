package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.AbstractMeasure;


public class VolumeMeasure
        extends AbstractMeasure<VolumeUnit>
        implements VolumeUnits {

    private static final long serialVersionUID = 1L;

    public VolumeMeasure(double value, VolumeUnit unit) {
        super(value, unit);
    }

    public VolumeMeasure(double value) {
        super(value);
    }

    @Override
    public VolumeMeasure in(VolumeUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new VolumeMeasure(value * times, otherUnit);
    }

}
