package net.bodz.bas.t.variant;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.predef.Predef;
import net.bodz.bas.t.predef.PredefMetadata;
import net.bodz.bas.t.range.DateRange;
import net.bodz.bas.t.range.DoubleRange;
import net.bodz.bas.t.range.FloatRange;
import net.bodz.bas.t.range.IntRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.bas.t.variant.DecoratedVariantMap;
import net.bodz.bas.t.variant.IVariantMap;
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

    public <T extends IId<Integer>> T getIntIdRef(K key, T skel) {
        String s = getString(key);
        if (s != null && !s.isEmpty()) {
            int id = getInt(key);
            skel.setId(id);
        }
        return skel;
    }

    public <T extends IId<Long>> T getLongIdRef(K key, T skel) {
        String s = getString(key);
        if (s != null && !s.isEmpty()) {
            long id = getLong(key);
            skel.setId(id);
        }
        return skel;
    }

}
