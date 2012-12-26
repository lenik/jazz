package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.IMeasureUnitConstants;

public interface VolumeUnits
        extends IMeasureUnitConstants {

    VolumeUnit M3 = new VolumeUnit("m3", "m³", 1.0);
    VolumeUnit LITER = new VolumeUnit("Liter", "L", 1e-3, M3);
    VolumeUnit KILOLITER = new VolumeUnit("kiloLiter", "kL", 1e+3, LITER);
    VolumeUnit MILLILITER = new VolumeUnit("milliLiter", "mL", 1e-3, LITER);
    VolumeUnit MICROLITER = new VolumeUnit("microLiter", "µL", 1e-6, LITER);

}
