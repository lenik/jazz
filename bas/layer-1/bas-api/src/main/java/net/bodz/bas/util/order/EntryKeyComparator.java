package net.bodz.bas.util.order;

import java.util.Comparator;
import java.util.Map.Entry;

public class EntryKeyComparator<K, V>
        extends AbstractNonNullComparator<Entry<K, V>> {

    final Comparator<? super K> cmp;

    public EntryKeyComparator(Comparator<? super K> cmp) {
        if (cmp == null)
            throw new NullPointerException("cmp");
        this.cmp = cmp;
    }

    @Override
    public int compareNonNull(Entry<K, V> o1, Entry<K, V> o2) {
        K ak = o1.getKey();
        K bk = o2.getKey();
        return cmp.compare(ak, bk);
    }

    public static final <K, V> EntryKeyComparator<K, V> getNaturalInstance() {
        return new EntryKeyComparator<K, V>(ComparableComparator.getRawInstance());
    }

}
