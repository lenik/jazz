package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.IMeasureUnitConstants;

public interface EnergyUnits
        extends IMeasureUnitConstants {

    EnergyUnit JOULE = new EnergyUnit("Joule", "J", 1.0);
    EnergyUnit KILOJOULE = new EnergyUnit("kiloJoule", "kJ", 1e+3, JOULE);
    EnergyUnit CALORIE = new EnergyUnit("Calorie", "Cal", 4.1868, KILOJOULE);
    EnergyUnit ERG = new EnergyUnit("erg", "erg", 1e-7, JOULE);

}
