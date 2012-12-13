package net.bodz.bas.t.order;

import java.util.Comparator;
import java.util.Map.Entry;

public class EntryKeyComparator<K>
        extends AbstractNonNullComparator<Entry<K, ?>> {

    final Comparator<? super K> cmp;

    public EntryKeyComparator(Comparator<? super K> cmp) {
        if (cmp == null)
            throw new NullPointerException("cmp");
        this.cmp = cmp;
    }

    @Override
    public int compareNonNull(Entry<K, ?> o1, Entry<K, ?> o2) {
        K ak = o1.getKey();
        K bk = o2.getKey();
        return cmp.compare(ak, bk);
    }

    public static final <K> EntryKeyComparator<K> getNaturalInstance() {
        return new EntryKeyComparator<K>(ComparableComparator.getRawInstance());
    }

}
