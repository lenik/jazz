package net.bodz.bas.i18n.unit;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import net.bodz.bas.c.string.StringPart;

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

    public static Measure parse(String str) {
        if (str == null)
            throw new NullPointerException("str");

        str = str.trim();
        if (str.isEmpty())
            throw new IllegalArgumentException("Measure string is empty");

        String num = StringPart.peekDecimal(str);
        if (num.isEmpty())
            throw new IllegalArgumentException("Measure isn't begin with a number");

        String suffix = str.substring(num.length()).trim();

        MeasureUnit unit = null;
        if (!suffix.isEmpty()) {
            List<MeasureUnit> units = MeasureUnit.forSymbol(suffix);
            switch (units.size()) {
            case 0: // illegal unit. assume the str isn't a measure.
                return null;
            case 1:
                unit = units.get(0);
            default:
                // warn: ambiguous unit symbol...
                throw new IllegalArgumentException("Ambiguous unit symbol: " + suffix);
            }
        }

        double value = Double.parseDouble(num);
        return new Measure(value, unit);
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

    public static final Measure NaN = new Measure(Double.NaN);

}
