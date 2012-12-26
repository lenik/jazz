package net.bodz.bas.i18n.unit;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public abstract class AbstractMeasure<U extends IMeasureUnit>
        implements IMeasure<U> {

    private static final long serialVersionUID = 1L;

    protected double value;
    protected U unit;

    public AbstractMeasure(double value) {
        this.value = value;
    }

    public AbstractMeasure(double value, U unit) {
        if (unit == null)
            throw new NullPointerException("unit");
        this.value = value;
        this.unit = unit;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public U getUnit() {
        return unit;
    }

    @Override
    public void setUnit(U unit) {
        if (unit == null)
            throw new NullPointerException("unit");
        this.unit = unit;
    }

    @Override
    public String toString() {
        String v = NUMBER_FORMAT.format(value);
        if (unit == null)
            return v;
        else
            return v + " " + unit.getSymbol();
    }

    private static NumberFormat NUMBER_FORMAT = new DecimalFormat(",###.#######");
    public static final Measure NaN = new Measure(Double.NaN);

}
