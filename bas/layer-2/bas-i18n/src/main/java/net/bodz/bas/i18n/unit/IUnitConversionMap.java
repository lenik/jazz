package net.bodz.bas.i18n.unit;

public interface IUnitConversionMap {

    /**
     * convert(m, cm) = 100.
     */
    double convert(StdMeasureUnit unit, StdMeasureUnit other);

}
