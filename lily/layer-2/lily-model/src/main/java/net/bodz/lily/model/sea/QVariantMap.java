package net.bodz.lily.model.sea;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.repr.util.Predef;
import net.bodz.bas.repr.util.PredefMetadata;
import net.bodz.bas.t.range.DateRange;
import net.bodz.bas.t.range.DoubleRange;
import net.bodz.bas.t.range.FloatRange;
import net.bodz.bas.t.range.IntRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.bas.t.variant.DecoratedVariantMap;
import net.bodz.bas.t.variant.IVariantMap;

/**
 * "Quick/Easy"-VariantMap.
 */
public class QVariantMap<K>
        extends DecoratedVariantMap<K> {

    private static final long serialVersionUID = 1L;

    public QVariantMap(IVariantMap<K> _orig) {
        super(_orig);
    }

    public Integer getInt(K key, Integer defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        int val = getInt(key);
        return val;
    }

    public Long getLong(K key, Long defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        long val = getLong(key);
        return val;
    }

    public Boolean getBoolean(K key, Boolean defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        switch (s) {
        case "true":
        case "yes":
        case "on":
        case "1":
            return true;
        }
        return false;
    }

    public <T extends Enum<T>> T getEnum(Class<T> enumType, K key, T defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        T value = Enum.valueOf(enumType, s);
        return value;
    }

    public <T extends Predef<T, Km>, Km extends Comparable<Km>> T getPredef(Class<T> type, K key, T defaultValue) {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        PredefMetadata<T, Km> metadata = PredefMetadata.forClass(type);
        T value = metadata.ofName(s);
        return value;
    }

    public Set<Integer> getInts(K key, Set<Integer> defaultValue) {
        String s = getString(key);
        if (s == null)
            return defaultValue;
        Set<Integer> ints = new HashSet<Integer>(16);
        StringTokenizer tokens = new StringTokenizer(s, ",");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            int val = Integer.parseInt(token);
            ints.add(val);
        }
        return ints;
    }

    public IntRange getIntRange(K key, IntRange defaultValue)
            throws ParseException {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        return IntRange.parse(s);
    }

    public LongRange getLongRange(K key, LongRange defaultValue)
            throws ParseException {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        return LongRange.parse(s);
    }

    public FloatRange getFloatRange(K key, FloatRange defaultValue)
            throws ParseException {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        return FloatRange.parse(s);
    }

    public DoubleRange getDoubleRange(K key, DoubleRange defaultValue)
            throws ParseException {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        return DoubleRange.parse(s);
    }

    public DateRange getDateRange(K key, DateRange defaultValue)
            throws ParseException {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        return DateRange.parse(s, false);
    }

}
