package net.bodz.bas.doc.property;

import net.bodz.bas.err.UnexpectedException;

public enum MeasureUnit {

    MILLIMETER("mm"),

    CENTIMETER("cm"),

    INCH("in"),

    PERCENT("%"),

    PIXEL("px"),

    POINT("pt"),

    VIEW_WIDTH("vw"),

    VIEW_HEIGHT("vh"),

    TWIP("tw"),

    ;

    public final String suffix;

    private MeasureUnit(String suffix) {
        this.suffix = suffix;
    }

    public double toPoints(double val, double baseSizeInPoints, double ppi) {
        switch (this) {
        case CENTIMETER:
            return val * 72.0 / 2.54;
        case MILLIMETER:
            return val * 72.0 / 25.4;
        case INCH:
            return val * 72.0;
        case PERCENT:
            return baseSizeInPoints * val / 100.0;
        case PIXEL:
            return val * 72.0 / ppi;
        case POINT:
            return val;
        case TWIP:
            return val / 20.0;
        case VIEW_WIDTH:
            return baseSizeInPoints * val / 100.0;
        case VIEW_HEIGHT:
            return baseSizeInPoints * val / 100.0;
        default:
            throw new UnexpectedException();
        }
    }

    public double toTwips(double val, double baseSizeInTwips, double ppi) {
        double baseSizeInPoints = baseSizeInTwips / 20.0;
        return toPoints(val, baseSizeInPoints, ppi) * 20.0;
    }

    public double toInches(double val, double baseSizeInInches, double ppi) {
        double baseSizeInPoints = baseSizeInInches * 72.0;
        return toPoints(val, baseSizeInPoints, ppi) / 72.0;
    }

    public double toCentimeters(double val, double baseSizeInCm, double ppi) {
        double baseSizeInPoints = baseSizeInCm / 2.54 * 72.0;
        return toPoints(val, baseSizeInPoints, ppi) / 72.0 * 2.54;
    }

    public double toMillimeter(double val, double baseSizeInMm, double ppi) {
        double baseSizeInPoints = baseSizeInMm / 25.4 * 72.0;
        return toPoints(val, baseSizeInPoints, ppi) / 72.0 * 25.4;
    }

    public double toPixels(double val, double baseSizeInPixels, double ppi) {
        switch (this) {
        case PERCENT:
            return baseSizeInPixels * val / 100.0;
        case PIXEL:
            return val;
        case POINT:
            return val * ppi / 72.0;
        case CENTIMETER:
            return val * ppi / 2.54;
        case INCH:
            return val * ppi;
        case MILLIMETER:
            return val * ppi / 25.4;
        case TWIP:
            return val * ppi * 20.0 / 72.0;
        case VIEW_WIDTH:
            return baseSizeInPixels * val / 100.0;
        case VIEW_HEIGHT:
            return baseSizeInPixels * val / 100.0;
        default:
            throw new UnexpectedException();
        }
    }

    public MeasureLength length(int val) {
        return new MeasureLength(this, val);
    }

    public MeasureLength length(double val) {
        return new MeasureLength(this, val);
    }

    public static MeasureUnit forSuffix(String s, boolean raiseError) {
        return forSuffix(s, null, true);
    }

    public static MeasureUnit forSuffix(String s, MeasureUnit fallback) {
        return forSuffix(s, fallback, false);
    }

    public static MeasureUnit forSuffix(String s, MeasureUnit fallback, boolean raiseError) {
        s = s.toLowerCase();
        switch (s) {
        case "%":
            return PERCENT;
        case "cm":
            return CENTIMETER;
        case "in":
            return INCH;
        case "mm":
            return MILLIMETER;
        case "pt":
            return POINT;
        case "px":
            return PIXEL;
        case "t":
        case "tw":
        case "twip":
            return TWIP;
        case "vh":
            return VIEW_HEIGHT;
        case "vw":
            return VIEW_WIDTH;
        default:
            if (fallback != null)
                return fallback;
            if (raiseError)
                throw new IllegalArgumentException("unknown suffix: " + s);
            else
                return null;
        }
    }

}
