package net.bodz.bas.doc.attr;

import java.util.HashMap;
import java.util.Map;

public class MeasureLength {

    MeasureUnit unit = MeasureUnit.PIXEL;
    double length;

    public MeasureLength(MeasureUnit unit, double length) {
        if (unit == null)
            throw new NullPointerException("unit");
        this.unit = unit;
        this.length = length;
    }

    public MeasureLength(double length, MeasureUnit unit) {
        if (unit == null)
            throw new NullPointerException("unit");
        this.unit = unit;
        this.length = length;
    }

    public MeasureLength(double length) {
        this.unit = MeasureUnit.PIXEL;
        this.length = length;
    }

    public MeasureUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasureUnit unit) {
        if (unit == null)
            throw new NullPointerException("unit");
        this.unit = unit;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    static final double DEFAULT_PPI = 96; // Microsoft 96, Mac 72

    public double getPoints(double baseSizeInPoints) {
        return getPoints(baseSizeInPoints, DEFAULT_PPI);
    }

    public double getPoints(double baseSizeInPoints, double ppi) {
        return this.unit.toPoints(length, baseSizeInPoints, ppi);
    }

    public static MeasureLength parse(String s) {
        MeasureLength named = forName(s);
        if (named != null)
            return named;

        if (s.endsWith("%"))
            return new MeasureLength(parseDouble(s, 1), MeasureUnit.PERCENT);
        int len = s.length();
        String suffix = len > 2 ? s.substring(len - 2) : "";
        MeasureUnit unit = MeasureUnit.forSuffix(suffix, null);
        double val = parseDouble(s, suffix.length());
        if (unit == null)
            unit = MeasureUnit.PIXEL;
        return new MeasureLength(val, unit);
    }

    static double parseDouble(String s, int trimLen) {
        String numPart = s.substring(s.length() - trimLen);
        return Double.parseDouble(numPart);
    }

    static double parseDouble(String s, int trimLen, Double defaultVal) {
        if (defaultVal == null)
            return parseDouble(s, trimLen);
        String numPart = s.substring(s.length() - trimLen);
        try {
            double num = Double.parseDouble(numPart);
            return num;
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    // scale factor can be 1.2 (CSS2), 1.3, 1.5 (CSS).

    /** @see https://weboverhauls.github.io/conversion-table/ */
    public static final MeasureLength X_SMALL = percent(62.5, "x-small");
    public static final MeasureLength SMALL = percent(81.0, "small");
    public static final MeasureLength MEDIUM = percent(100.0, "medium");
    public static final MeasureLength LARGE = percent(112.5, "large");
    public static final MeasureLength X_LARGE = percent(150.0, "x-large");
    public static final MeasureLength XX_LARGE = percent(200.0, "xx-large");

    // get from Google Chrome
    public static final MeasureLength SMALLER = percent(83.0, "smaller");
    public static final MeasureLength LARGER = percent(120.0, "larger");

    static Map<String, MeasureLength> nameMap = new HashMap<>();

    static MeasureLength percent(double percent, String... names) {
        MeasureLength len = new MeasureLength(MeasureUnit.PERCENT, percent);
        for (String name : names)
            nameMap.put(name, len);
        return len;
    }

    public static MeasureLength forName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        name = name.toLowerCase();
        MeasureLength len = nameMap.get(name);
        return len;
    }

}
