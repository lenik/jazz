package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.unit.AbstractMeasure;
import net.bodz.bas.i18n.unit.Measure;

public class LengthMeasure
        extends AbstractMeasure<LengthUnit>
        implements LengthUnits {

    private static final long serialVersionUID = 1L;

    public LengthMeasure(double value, LengthUnit unit) {
        super(value, unit);
    }

    public LengthMeasure(double value) {
        super(value);
    }

    public static LengthMeasure parse(String str)
            throws ParseException {
        Measure m = Measure.parse(str);
        return new LengthMeasure(m.getValue(), (LengthUnit) m.getUnit());
    }

    public static LengthMeasure parse(String str, LengthMeasure fallback) {
        try {
            return parse(str);
        } catch (ParseException e) {
            return fallback;
        }
    }

    public static LengthMeasure parseOrNaN(String str) {
        return parse(str, LengthMeasure.NaN);
    }

    @Override
    public LengthMeasure in(LengthUnit otherUnit) {
        if (unit == otherUnit)
            return this;
        double times = unit.in(otherUnit);
        return new LengthMeasure(value * times, otherUnit);
    }

    public int dots(int dpi) {
        int times = unit.dots(dpi);
        return (int) (value * times);
    }

    public LengthMeasure inDots(int dpi) {
        int times = unit.dots(dpi);
        return new LengthMeasure(value * times, POINT);
    }

    public int pixels(int ppi) {
        int times = unit.pixels(ppi);
        return (int) (value * times);
    }

    public LengthMeasure inPixels(int ppi) {
        int times = unit.pixels(ppi);
        return new LengthMeasure(value * times, PIXEL);
    }

    public static final LengthMeasure NaN = new LengthMeasure(Double.NaN);

}
