package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.IMeasurement;

public interface ILength
        extends IMeasurement<LengthUnit> {

    int dots(int dpi);

    Length inDots(int dpi);

    int pixels(int ppi);

    Length inPixels(int ppi);

}
