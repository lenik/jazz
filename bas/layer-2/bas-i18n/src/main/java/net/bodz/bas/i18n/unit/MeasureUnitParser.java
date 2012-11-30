package net.bodz.bas.i18n.unit;

import java.util.List;

import net.bodz.bas.err.ParseException;

public class MeasureUnitParser
        implements IMeasureUnits {

    public static MeasureUnit parseUnit(String nameOrSymbol)
            throws ParseException {
        MeasureUnit unit = MeasureUnit.forName(nameOrSymbol);
        if (unit != null)
            return unit;

        List<MeasureUnit> units = MeasureUnit.forSymbol(nameOrSymbol);
        if (units.size() != 1)
            throw new ParseException("Ambiguous unit symbol: " + nameOrSymbol);

        unit = units.get(0);
        return unit;
    }

}
