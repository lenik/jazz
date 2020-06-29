package net.bodz.bas.t.range;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.source.FnHelper;
import net.bodz.bas.t.variant.IVariantMap;

@FnHelper
public class RangeFn {

    public static <range_t extends Range<range_t, val_t>, val_t> range_t getFrom(IVariantMap<String> map, String key,
            range_t newValue, range_t defaultValue)
            throws LoaderException {
        String s = map.getString(key);
        if (s == null)
            return defaultValue;
        try {
            newValue.parse(s);
        } catch (ParseException e) {
            throw new LoaderException(String.format(//
                    "Can't parse value of key %s: %s", key, e.getMessage()), e);
        }
        return newValue;
    }

    public static ShortRange getFrom(IVariantMap<String> map, String key, ShortRange defaultValue)
            throws LoaderException {
        return getFrom(map, key, new ShortRange(), defaultValue);
    }

    public static IntegerRange getFrom(IVariantMap<String> map, String key, IntegerRange defaultValue)
            throws LoaderException {
        return getFrom(map, key, new IntegerRange(), defaultValue);
    }

    public static LongRange getFrom(IVariantMap<String> map, String key, LongRange defaultValue)
            throws LoaderException {
        return getFrom(map, key, new LongRange(), defaultValue);
    }

    public static FloatRange getFrom(IVariantMap<String> map, String key, FloatRange defaultValue)
            throws LoaderException {
        return getFrom(map, key, new FloatRange(), defaultValue);
    }

    public static DoubleRange getFrom(IVariantMap<String> map, String key, DoubleRange defaultValue)
            throws LoaderException {
        return getFrom(map, key, new DoubleRange(), defaultValue);
    }

    public static DateTimeRange getFrom(IVariantMap<String> map, String key, DateTimeRange defaultValue)
            throws LoaderException {
        return getFrom(map, key, new DateTimeRange(), defaultValue);
    }

}
