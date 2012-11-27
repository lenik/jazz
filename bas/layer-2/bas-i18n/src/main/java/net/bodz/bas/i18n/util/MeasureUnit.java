package net.bodz.bas.i18n.util;

import java.io.Serializable;

public abstract class MeasureUnit
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;

    public MeasureUnit(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isStd();

    public abstract StdMeasureUnit getStdUnit();

    public abstract double getScale();

    public abstract double getScaleTo(MeasureUnit other);

    public abstract double getScaleTo(StdMeasureUnit other);

    @Override
    public String toString() {
        return name;
    }

}
