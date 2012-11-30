package net.bodz.bas.i18n.unit;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Measure
        implements Serializable, IMeasureUnits {

    private static final long serialVersionUID = 1L;

    double value;
    MeasureUnit unit;

    public Measure(double value) {
        this.value = value;
    }

    public Measure(double value, MeasureUnit unit) {
        if (unit == null)
            throw new NullPointerException("unit");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public MeasureUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasureUnit unit) {
        if (unit == null)
            throw new NullPointerException("unit");
        this.unit = unit;
    }

    public Measure convert(MeasureUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double t = unit.timesOf(otherUnit);
        Measure other = new Measure(value * t, otherUnit);
        return other;
    }

    public Measure convert(MeasureUnit otherUnit, IUnitConversionMap conversionMap) {
        if (unit == otherUnit)
            return this;
        double t = unit.timesOf(otherUnit, conversionMap);
        Measure other = new Measure(value * t, otherUnit);
        return other;
    }

    private static NumberFormat numberFormat = new DecimalFormat(",###.#######");

    @Override
    public String toString() {
        String v = numberFormat.format(value);
        if (unit == null)
            return v;
        else
            return v + " " + unit;
    }

}
