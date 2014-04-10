package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.IMeasureUnitConsts;

public interface LengthUnits
        extends IMeasureUnitConsts {

    public static final LengthUnit METER = new LengthUnit("meter", "m", 1.0);
    public static final LengthUnit KILOMETER = new LengthUnit("kilometer", "km", 1e+3, METER);
    public static final LengthUnit DECIMETER = new LengthUnit("decimeter", "dm", 1e-1, METER);
    public static final LengthUnit CENTIMETER = new LengthUnit("centimeter", "cm", 1e-2, METER);
    public static final LengthUnit MILLIMETER = new LengthUnit("millimeter", "mm", 1e-3, METER);
    public static final LengthUnit MICROMETER = new LengthUnit("micrometer", "µm", 1e-6, METER);
    public static final LengthUnit NANOMETER = new LengthUnit("nanometer", "nm", 1e-9, METER);
    public static final LengthUnit PICOMETER = new LengthUnit("picometer", "pm", 1e-12, METER);

    public static final LengthUnit FOOT = new LengthUnit("foot", "ft", 0.3048, METER);
    public static final LengthUnit MILE = new LengthUnit("mile", "mi", 5280, FOOT);
    public static final LengthUnit INCH = new LengthUnit("inch", "in", 1 / 12.0, FOOT);
    public static final LengthUnit MILLIINCH = new LengthUnit("milliinch", "mil", 1e-3, INCH);
    public static final LengthUnit PARSEC = new LengthUnit("parsec", "pc", 3.085678e16, METER);
    public static final LengthUnit LEAGUE = new LengthUnit("league", "league", 3, MILE);
    public static final LengthUnit ASTRONOMICAL_UNIT = new LengthUnit("Astronomical Unit", "ua", 1.49598e11, METER);
    public static final LengthUnit YARD = new LengthUnit("yard", "yd", 3, FOOT);
    public static final LengthUnit ANGSTROM = new LengthUnit("angstrom", "Ang", 1e-10, METER);
    public static final LengthUnit FURLONG = new LengthUnit("furlong", "furlong", 220, YARD);
    public static final LengthUnit FATHOM = new LengthUnit("fathom", "fathom", 6, FOOT);
    public static final LengthUnit ROD = new LengthUnit("rod", "rd", 16.5, FOOT);
    public static final LengthUnit PICA = new LengthUnit("pica", "pica", 1 / 6.0, INCH);

    public static final PointUnit POINT = PointUnit.getInstance();

    /** @deprecated See {@link #POINT}. */
    public static final PointUnit DOT = POINT;

    public static final PixelUnit PIXEL = PixelUnit.getInstance();

}
