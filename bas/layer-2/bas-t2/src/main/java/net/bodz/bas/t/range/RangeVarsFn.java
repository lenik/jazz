package net.bodz.bas.t.range;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.source.FnHelper;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

/**
 * @see IVarMapForm
 */
@FnHelper
public class RangeVarsFn {

    public static <range_t extends Range<range_t, val_t>, val_t> range_t getFrom(IVariantMap<String> map, String key,
            range_t newValue, range_t defaultValue)
            throws ParseException {
        String pointStr = map.getString(key);
        if (pointStr != null) {
            newValue.parseNonNullValue(pointStr);
            return newValue;
        }
        String rangeStr = map.getString(key + "Range");
        if (rangeStr != null) {
            newValue.parse(rangeStr);
            return newValue;
        }
        return defaultValue;
    }

    public static ShortRange getFrom(IVariantMap<String> map, String key, ShortRange defaultValue)
            throws ParseException {
        return getFrom(map, key, new ShortRange(), defaultValue);
    }

    public static IntegerRange getFrom(IVariantMap<String> map, String key, IntegerRange defaultValue)
            throws ParseException {
        return getFrom(map, key, new IntegerRange(), defaultValue);
    }

    public static LongRange getFrom(IVariantMap<String> map, String key, LongRange defaultValue)
            throws ParseException {
        return getFrom(map, key, new LongRange(), defaultValue);
    }

    public static FloatRange getFrom(IVariantMap<String> map, String key, FloatRange defaultValue)
            throws ParseException {
        return getFrom(map, key, new FloatRange(), defaultValue);
    }

    public static DoubleRange getFrom(IVariantMap<String> map, String key, DoubleRange defaultValue)
            throws ParseException {
        return getFrom(map, key, new DoubleRange(), defaultValue);
    }

    public static ZonedDateTimeRange getFrom(IVariantMap<String> map, String key, ZonedDateTimeRange defaultValue)
            throws ParseException {
        return getFrom(map, key, new ZonedDateTimeRange(), defaultValue);
    }

}
