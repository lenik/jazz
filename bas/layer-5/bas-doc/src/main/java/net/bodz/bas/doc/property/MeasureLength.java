package net.bodz.bas.doc.property;

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

    public MeasureLength negate() {
        return new MeasureLength(-length, unit);
    }

    static final double BASE_SIZE_DEFAULT = -1000;
    static final double DEFAULT_PPI = 96; // Microsoft 96, Mac 72

    public double toPoints() {
        return toPoints(BASE_SIZE_DEFAULT);
    }

    public double toPoints(double baseSizeInPoints) {
        return toPoints(baseSizeInPoints, DEFAULT_PPI);
    }

    public double toPoints(double baseSizeInPoints, double ppi) {
        return this.unit.toPoints(length, baseSizeInPoints, ppi);
    }

    public double toTwips() {
        return toTwips(BASE_SIZE_DEFAULT);
    }

    public double toTwips(double baseSizeInTwips) {
        return toTwips(baseSizeInTwips, DEFAULT_PPI);
    }

    public double toTwips(double baseSizeInTwips, double ppi) {
        return this.unit.toTwips(length, baseSizeInTwips, ppi);
    }

    public double toCentimeters() {
        return toCentimeters(BASE_SIZE_DEFAULT);
    }

    public double toCentimeters(double baseSizeInCentimeters) {
        return toCentimeters(baseSizeInCentimeters, DEFAULT_PPI);
    }

    public double toCentimeters(double baseSizeInCentimeters, double ppi) {
        return this.unit.toCentimeters(length, baseSizeInCentimeters, ppi);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(length);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MeasureLength other = (MeasureLength) obj;
        if (Double.doubleToLongBits(length) != Double.doubleToLongBits(other.length))
            return false;
        if (unit != other.unit)
            return false;
        return true;
    }

    @Override
    public String toString() {
        String num = String.format("%.6f", length);
        int len = num.length();
        int end = len;
        for (int pos = len - 1; pos >= 0; pos--) {
            if (num.charAt(pos) == '0')
                end--;
            else
                break;
        }
        if (num.charAt(end - 1) == '.')
            end--;
        num = num.substring(0, end);

        return num + unit.suffix;
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

    public static MeasureLength twips(double val) {
        return new MeasureLength(val, MeasureUnit.TWIP);
    }

    public static MeasureLength points(double val) {
        return new MeasureLength(val, MeasureUnit.POINT);
    }

    public static MeasureLength pixels(double val) {
        return new MeasureLength(val, MeasureUnit.PIXEL);
    }

    public static MeasureLength millimeters(double val) {
        return new MeasureLength(val, MeasureUnit.MILLIMETER);
    }

    public static MeasureLength centimeters(double val) {
        return new MeasureLength(val, MeasureUnit.CENTIMETER);
    }

    public static final MeasureLength ZERO = points(0);

    static Map<String, MeasureLength> nameMap = new HashMap<>();

    // scale factor can be 1.2 (CSS2), 1.3, 1.5 (CSS).

    /** @see https://weboverhauls.github.io/conversion-table/ */
    public static final MeasureLength X_SMALL = _percent(62.5, "x-small");
    public static final MeasureLength SMALL = _percent(81.0, "small");
    public static final MeasureLength MEDIUM = _percent(100.0, "medium");
    public static final MeasureLength LARGE = _percent(112.5, "large");
    public static final MeasureLength X_LARGE = _percent(150.0, "x-large");
    public static final MeasureLength XX_LARGE = _percent(200.0, "xx-large");

    // get from Google Chrome
    public static final MeasureLength SMALLER = _percent(83.0, "smaller");
    public static final MeasureLength LARGER = _percent(120.0, "larger");

    private static MeasureLength _percent(double percent, String... names) {
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
