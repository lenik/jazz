package net.bodz.bas.gui.css3;

import java.io.Serializable;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.unit.Measurement;
import net.bodz.bas.i18n.unit.std.Length;
import net.bodz.bas.i18n.unit.std.LengthUnit;

/**
 * Css-Size: aligned top/left/.., percent%
 * 
 * 1px 2em 3mm 4in 'bottom' 'larger' ... ICssKeywords
 * 
 * top/width/... min-width/max-height letter-spacing line-height: padding/margin:
 * background-position border-*-spacing border-width outline-offset z-index font-size text-indent
 * vertical-align word-spacing
 */
public class Css3Length
        extends Length
        implements ICss3Length, Serializable {

    private static final long serialVersionUID = 1L;

    public static final Css3Length NaN = new Css3Length(Double.NaN);

    public int type;
    public int number;
    public float percentage;

    public Css3Length(Measurement measure) {
        this(measure.getValue(), (LengthUnit) measure.getUnit());
    }

    public Css3Length(double value, LengthUnit unit) {
        super(value, unit);
    }

    public Css3Length(double value) {
        super(value);
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.type = NUMBER;
        this.number = number;
    }

    @Override
    public float getPercentage() {
        return percentage;
    }

    @Override
    public void setPercentage(float percentage) {
        this.type = PERCENTAGE;
        this.percentage = percentage;
    }

    public static Css3Length parse(String str)
            throws ParseException {
        Measurement m = Measurement.parse(str);
        return new Css3Length(m);
    }

    public static Css3Length parse(String str, Css3Length fail) {
        try {
            return parse(str);
        } catch (ParseException e) {
            return fail;
        }
    }

    public static Css3Length parseOrNaN(String str) {
        return parse(str, Css3Length.NaN);
    }

    public static Css3Length PIXEL(int length) {
        return new Css3Length(length, LengthUnit.PIXEL);
    }

}
