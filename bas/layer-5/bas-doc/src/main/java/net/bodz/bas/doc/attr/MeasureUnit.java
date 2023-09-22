package net.bodz.bas.doc.attr;

import net.bodz.bas.err.UnexpectedException;

public enum MeasureUnit {

    PIXEL,

    POINT,

    MM,

    CM,

    INCH,

    PERCENT,

    VIEW_WIDTH,

    VIEW_HEIGHT,

    ;

    public double toPoints(double val, double baseSizeInPoints, double ppi) {
        switch (this) {
        case PIXEL:
            return val * 72.0 / ppi;
        case POINT:
            return val;
        case MM:
            return val * 72.0 / 25.4;
        case CM:
            return val * 72.0 / 2.54;
        case INCH:
            return val * 72.0;
        case PERCENT:
            return baseSizeInPoints * val / 100.0;
        case VIEW_WIDTH:
            return baseSizeInPoints * val / 100.0;
        case VIEW_HEIGHT:
            return baseSizeInPoints * val / 100.0;
        default:
            throw new UnexpectedException();
        }
    }

    public double toInches(double val, double baseSizeInPoints, double ppi) {
        return toPoints(val, baseSizeInPoints, ppi) / 72.0;
    }

    public double toCm(double val, double baseSizeInPoints, double ppi) {
        return toPoints(val, baseSizeInPoints, ppi) / 72.0 * 2.54;
    }

    public double toMm(double val, double baseSizeInPoints, double ppi) {
        return toPoints(val, baseSizeInPoints, ppi) / 72.0 * 25.4;
    }

    public double toPixels(double val, double baseSizeInPixels, double ppi) {
        switch (this) {
        case PIXEL:
            return val;
        case POINT:
            return val * ppi / 72.0;
        case MM:
            return val * ppi / 25.4;
        case CM:
            return val * ppi / 2.54;
        case INCH:
            return val * ppi;
        case PERCENT:
            return baseSizeInPixels * val / 100.0;
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
        case "px":
            return PIXEL;
        case "pt":
            return POINT;
        case "mm":
            return MM;
        case "cm":
            return CM;
        case "in":
            return INCH;
        case "vw":
            return VIEW_WIDTH;
        case "vh":
            return VIEW_HEIGHT;
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
