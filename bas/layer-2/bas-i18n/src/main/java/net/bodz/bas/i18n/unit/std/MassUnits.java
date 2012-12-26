package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.IMeasureUnitConstants;

public interface MassUnits
        extends IMeasureUnitConstants {

    MassUnit GRAM = new MassUnit("gram", "g", 1.0);
    MassUnit KILOGRAM = new MassUnit("kilogram", "kg", 1e+3, GRAM);
    MassUnit MILLIGRAM = new MassUnit("milligram", "mg", 1e-3, GRAM);
    MassUnit MICROGRAM = new MassUnit("microgram", "µg", 1e-6, GRAM);
    MassUnit TON = new MassUnit("ton", "t", 1, KILOGRAM);

    MassUnit POUND = new MassUnit("pound", "lbm", 0.45359237, KILOGRAM);
    MassUnit TROY_POUND = new MassUnit("troy pound", "lbt", 0.3732417, KILOGRAM);
    MassUnit CARAT = new MassUnit("carat", "carat", 0.2, GRAM);
    MassUnit SHORT_TON = new MassUnit("short ton", "ton_s", 2000, POUND);
    MassUnit LONG_TON = new MassUnit("long ton", "ton_l", 2240, POUND);
    MassUnit OUNCE = new MassUnit("ounce", "oz", 28.34952, GRAM);
    MassUnit GRAIN = new MassUnit("grain", "gr", 64.79891, MILLIGRAM);
    MassUnit PENNYWEIGHT = new MassUnit("pennyweight", "dwt", 1.55174, GRAM);

}
