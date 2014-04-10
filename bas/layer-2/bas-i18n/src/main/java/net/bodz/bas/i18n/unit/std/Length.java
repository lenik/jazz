package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.unit.AbstractMeasurement;
import net.bodz.bas.i18n.unit.Measurement;

public class Length
        extends AbstractMeasurement<LengthUnit>
        implements ILength, LengthUnits {

    private static final long serialVersionUID = 1L;

    public static final Length NaN = new Length(Double.NaN);

    public Length(double value, LengthUnit unit) {
        super(value, unit);
    }

    public Length(double value) {
        super(value);
    }

    @Override
    public Length in(LengthUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new Length(value * times, otherUnit);
    }

    @Override
    public int dots(int dpi) {
        int times = unit.dots(dpi);
        return (int) (value * times);
    }

    @Override
    public Length inDots(int dpi) {
        int times = unit.dots(dpi);
        return new Length(value * times, POINT);
    }

    @Override
    public int pixels(int ppi) {
        int times = unit.pixels(ppi);
        return (int) (value * times);
    }

    @Override
    public Length inPixels(int ppi) {
        int times = unit.pixels(ppi);
        return new Length(value * times, PIXEL);
    }

    public static Length parse(String str)
            throws ParseException {
        Measurement m = Measurement.parse(str);
        return new Length(m.getValue(), (LengthUnit) m.getUnit());
    }

    public static Length parse(String str, Length fallback) {
        try {
            return parse(str);
        } catch (ParseException e) {
            return fallback;
        }
    }

    public static Length parseOrNaN(String str) {
        return parse(str, Length.NaN);
    }

    public static Length PIXEL(int length) {
        return new Length(length, LengthUnit.PIXEL);
    }

}
