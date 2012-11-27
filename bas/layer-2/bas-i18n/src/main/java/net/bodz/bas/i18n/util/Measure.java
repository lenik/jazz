package net.bodz.bas.i18n.util;

import java.io.Serializable;

public class Measure
        implements Serializable {

    private static final long serialVersionUID = 1L;

    float value;
    MeasureUnit unit;

    public Measure(float value) {
        this.value = value;
        unit = MeasureUnit.pixel;
    }

    public Measure(float value, MeasureUnit unit) {
        if (unit == null)
            throw new NullPointerException("unit");
        this.value = value;
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
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

}
