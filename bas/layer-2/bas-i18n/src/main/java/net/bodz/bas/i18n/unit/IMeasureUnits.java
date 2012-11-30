package net.bodz.bas.i18n.unit;

public interface IMeasureUnits {

    // ⁰ ¹ ² ³ ⁴ ⁵ ⁶ ⁷ ⁸ ⁹

    // Time

    StdMeasureUnit second = new StdMeasureUnit("second", "sec");
    UserMeasureUnit millisecond = new UserMeasureUnit("millisecond", "ms", 1e-3, second);
    UserMeasureUnit microsecond = new UserMeasureUnit("microsecond", "µs", 1e-6, second);
    UserMeasureUnit nanosecond = new UserMeasureUnit("nanosecond", "ns", 1e-9, second);
    UserMeasureUnit picosecond = new UserMeasureUnit("picosecond", "ps", 1e-12, second);

    UserMeasureUnit minute = new UserMeasureUnit("minute", "min", 60, second);
    UserMeasureUnit hour = new UserMeasureUnit("hour", "h", 60, minute);
    UserMeasureUnit day = new UserMeasureUnit("day", "d", 24, hour);
    UserMeasureUnit shake = new UserMeasureUnit("shake", "min", 10, nanosecond);

    // Length or distance

    StdMeasureUnit meter = new StdMeasureUnit("meter", "m");
    UserMeasureUnit kilometer = new UserMeasureUnit("kilometer", "km", 1e+3, meter);
    UserMeasureUnit decimeter = new UserMeasureUnit("decimeter", "dm", 1e-1, meter);
    UserMeasureUnit centimeter = new UserMeasureUnit("centimeter", "cm", 1e-2, meter);
    UserMeasureUnit millimeter = new UserMeasureUnit("millimeter", "mm", 1e-3, meter);
    UserMeasureUnit micrometer = new UserMeasureUnit("micrometer", "µm", 1e-6, meter);
    UserMeasureUnit nanometer = new UserMeasureUnit("nanometer", "nm", 1e-9, meter);
    UserMeasureUnit picometer = new UserMeasureUnit("picometer", "pm", 1e-12, meter);

    UserMeasureUnit foot = new UserMeasureUnit("foot", "ft", 0.3048, meter);
    UserMeasureUnit mile = new UserMeasureUnit("mile", "mi", 5280, foot);
    UserMeasureUnit inch = new UserMeasureUnit("inch", "in", 1 / 12.0, foot);
    UserMeasureUnit milliinch = new UserMeasureUnit("milliinch", "mil", 1e-3, inch);
    UserMeasureUnit parsec = new UserMeasureUnit("parsec", "pc", 3.085678e16, meter);
    UserMeasureUnit league = new UserMeasureUnit("league", "league", 3, mile);
    UserMeasureUnit astronomicalUnit = new UserMeasureUnit("Astronomical Unit", "ua", 1.49598e11, meter);
    UserMeasureUnit yard = new UserMeasureUnit("yard", "yd", 3, foot);
    UserMeasureUnit angstrom = new UserMeasureUnit("angstrom", "Ang", 1e-10, meter);
    UserMeasureUnit furlong = new UserMeasureUnit("furlong", "furlong", 220, yard);
    UserMeasureUnit fathom = new UserMeasureUnit("fathom", "fathom", 6, foot);
    UserMeasureUnit rod = new UserMeasureUnit("rod", "rd", 16.5, foot);
    UserMeasureUnit point = new UserMeasureUnit("point", "pt", 1 / 72.0, inch);
    UserMeasureUnit pica = new UserMeasureUnit("pica", "pica", 1 / 6.0, inch);

    // Area

    StdMeasureUnit m2 = new StdMeasureUnit("m2", "m²");
    UserMeasureUnit km2 = new UserMeasureUnit("km2", "km²", 1e+6, m2);
    UserMeasureUnit cm2 = new UserMeasureUnit("cm2", "cm²", 1e-4, m2);
    UserMeasureUnit mm2 = new UserMeasureUnit("mm2", "mm²", 1e-9, m2);

    // Volume

    StdMeasureUnit m3 = new StdMeasureUnit("m3", "m³");
    UserMeasureUnit Liter = new UserMeasureUnit("Liter", "L", 1e-3, m3);
    UserMeasureUnit kiloLiter = new UserMeasureUnit("kiloLiter", "kL", 1e+3, Liter);
    UserMeasureUnit milliLiter = new UserMeasureUnit("milliLiter", "mL", 1e-3, Liter);
    UserMeasureUnit microLiter = new UserMeasureUnit("microLiter", "µL", 1e-6, Liter);

    // Mass

    StdMeasureUnit gram = new StdMeasureUnit("gram", "g");
    UserMeasureUnit kilogram = new UserMeasureUnit("kilogram", "kg", 1e+3, gram);
    UserMeasureUnit milligram = new UserMeasureUnit("milligram", "mg", 1e-3, gram);
    UserMeasureUnit microgram = new UserMeasureUnit("microgram", "µg", 1e-6, gram);
    UserMeasureUnit ton = new UserMeasureUnit("ton", "t", 1, kilogram);

    UserMeasureUnit pound = new UserMeasureUnit("pound", "lbm", 0.45359237, kilogram);
    UserMeasureUnit troyPound = new UserMeasureUnit("troy pound", "lbt", 0.3732417, kilogram);
    UserMeasureUnit carat = new UserMeasureUnit("carat", "carat", 0.2, gram);
    UserMeasureUnit shortTon = new UserMeasureUnit("short ton", "ton_s", 2000, pound);
    UserMeasureUnit longTon = new UserMeasureUnit("long ton", "ton_l", 2240, pound);
    UserMeasureUnit ounce = new UserMeasureUnit("ounce", "oz", 28.34952, gram);
    UserMeasureUnit grain = new UserMeasureUnit("grain", "gr", 64.79891, milligram);
    UserMeasureUnit pennyweight = new UserMeasureUnit("pennyweight", "dwt", 1.55174, gram);

    // Force or weight

    StdMeasureUnit newton = new StdMeasureUnit("newton", "N");
    UserMeasureUnit kilonewton = new UserMeasureUnit("kilonewton", "kN", 1e+3, newton);
    UserMeasureUnit millinewton = new UserMeasureUnit("millinewton", "mN", 1e-3, newton);
    UserMeasureUnit dyne = new UserMeasureUnit("dyne", "dyn", 1e-5, newton);

    // Energy

    StdMeasureUnit Joule = new StdMeasureUnit("Joule", "J");
    UserMeasureUnit kiloJoule = new UserMeasureUnit("kiloJoule", "kJ", 1e+3, Joule);
    UserMeasureUnit Calorie = new UserMeasureUnit("Calorie", "Cal", 4.1868, kiloJoule);
    UserMeasureUnit erg = new UserMeasureUnit("erg", "erg", 1e-7, Joule);

    // Power

    StdMeasureUnit Watt = new StdMeasureUnit("Watt", "W");
    StdMeasureUnit Hertz = new StdMeasureUnit("Hertz", "Hz");

    // Display & Print

    StdMeasureUnit pixel = new StdMeasureUnit("pixel", "px");
    StdMeasureUnit dot = new StdMeasureUnit("dot", "dot");

}
