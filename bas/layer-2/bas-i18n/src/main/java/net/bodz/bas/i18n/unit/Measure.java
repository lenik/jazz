package net.bodz.bas.i18n.unit;

import java.util.List;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.ParseException;

public class Measure
        extends AbstractMeasure<IMeasureUnit> {

    private static final long serialVersionUID = 1L;

    public Measure(double value) {
        super(value);
    }

    public Measure(double value, IMeasureUnit unit) {
        super(value, unit);
    }

    @Override
    public Measure in(IMeasureUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new Measure(value * times, otherUnit);
    }

    public static Measure parse(String str)
            throws ParseException {
        if (str == null)
            throw new NullPointerException("str");

        str = str.trim();
        if (str.isEmpty())
            throw new ParseException("Measure string is empty");

        String num = StringPart.peekDecimal(str);
        if (num.isEmpty())
            throw new ParseException("Measure isn't begin with a number");

        String suffix = str.substring(num.length()).trim();

        IMeasureUnit unit = null;
        if (!suffix.isEmpty()) {
            List<IMeasureUnit> units = MeasureUnits.forSymbol(suffix);
            switch (units.size()) {
            case 0: // illegal unit. assume the str isn't a measure.
                return null;
            case 1:
                unit = units.get(0);
            default:
                // warn: ambiguous unit symbol...
                throw new ParseException("Ambiguous unit symbol: " + suffix);
            }
        }

        try {
            double value = Double.parseDouble(num);
            return new Measure(value, unit);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

}
