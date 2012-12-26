package net.bodz.bas.i18n.unit;

import java.io.Serializable;

public interface IMeasure<U extends IMeasureUnit>
        extends Serializable {

    double getValue();

    void setValue(double value);

    U getUnit();

    void setUnit(U unit);

    IMeasure<U> in(U otherUnit);

}
