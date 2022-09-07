package net.bodz.bas.t.variant;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.joda.time.DateTimeZone;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.predef.Predef;
import net.bodz.bas.t.predef.PredefMetadata;
import net.bodz.bas.t.range.*;
import net.bodz.lily.entity.IId;

/**
 * "Quick/Easy"-VariantMap.
 */
public class QVariantMap<K>
        extends DecoratedVariantMap<K> {

    private static final long serialVersionUID = 1L;

    QVariantMap(IVariantMap<K> _orig) {
        super(_orig);
    }

    public static <K> QVariantMap<K> from(IVariantMap<K> _orig) {
        return new QVariantMap<K>(_orig);
    }

    /**
     * Treat empty value as <code>null</code>.
     *
     * @see #getString(Object, String)
     */
    public final String getStringE4n(K key) {
        return getStringE4n(key, null);
    }

    /**
     * Treat empty value as <code>null</code>.
     *
     * @see #getString(Object, String)
     */
    public String getStringE4n(K key, String defaultValue) {
        String s = super.getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        else
            return s;
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

    public <range_t extends Range<?, ?>> range_t getRange(range_t newRange, K key, range_t defaultValue)
            throws ParseException {
        String s = getString(key);
        if (s == null || s.isEmpty())
            return defaultValue;
        range_t range = newRange;
        range.parse(s);
        return range;
    }

    public ShortRange getShortRange(K key, ShortRange defaultValue)
            throws ParseException {
        return getRange(new ShortRange(), key, defaultValue);
    }

    public IntegerRange getIntRange(K key, IntegerRange defaultValue)
            throws ParseException {
        return getRange(new IntegerRange(), key, defaultValue);
    }

    public LongRange getLongRange(K key, LongRange defaultValue)
            throws ParseException {
        return getRange(new LongRange(), key, defaultValue);
    }

    public FloatRange getFloatRange(K key, FloatRange defaultValue)
            throws ParseException {
        return getRange(new FloatRange(), key, defaultValue);
    }

    public DoubleRange getDoubleRange(K key, DoubleRange defaultValue)
            throws ParseException {
        return getRange(new DoubleRange(), key, defaultValue);
    }

    public DateTimeRange getDateRange(K key, DateTimeRange defaultValue, DateTimeZone timeZone)
            throws ParseException {
        return getRange(new DateTimeRange(timeZone), key, defaultValue);
    }

    public <T extends IId<Integer>> T getIntIdRef(K key, T skel) {
        String s = getString(key);
        if (s != null && !s.isEmpty()) {
            Integer id = getInt(key);
            skel.id(id);
        }
        return skel;
    }

    public <T extends IId<Long>> T getLongIdRef(K key, T skel) {
        String s = getString(key);
        if (s != null && !s.isEmpty()) {
            Long id = getLong(key);
            skel.id(id);
        }
        return skel;
    }

}
