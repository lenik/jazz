package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.IMeasureUnitConstants;

public interface ForceUnits
        extends IMeasureUnitConstants {

    ForceUnit NEWTON = new ForceUnit("newton", "N", 1.0);
    ForceUnit KILONEWTON = new ForceUnit("kilonewton", "kN", 1e+3, NEWTON);
    ForceUnit MILLINEWTON = new ForceUnit("millinewton", "mN", 1e-3, NEWTON);
    ForceUnit DYNE = new ForceUnit("dyne", "dyn", 1e-5, NEWTON);

}
