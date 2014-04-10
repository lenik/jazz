package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.IMeasureUnitConsts;

public interface AreaUnits
        extends IMeasureUnitConsts {

    AreaUnit M2 = new AreaUnit("m2", "m²", 1.0);
    AreaUnit KM2 = new AreaUnit("km2", "km²", 1e+6, M2);
    AreaUnit CM2 = new AreaUnit("cm2", "cm²", 1e-4, M2);
    AreaUnit MM2 = new AreaUnit("mm2", "mm²", 1e-9, M2);

}
