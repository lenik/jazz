package net.bodz.bas.t.specmap;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class RangeComparator<key_t extends Comparable<key_t>>
        extends AbstractNonNullComparator<IRange<key_t>> {

    @Override
    public int compareNonNull(IRange<key_t> o1, IRange<key_t> o2) {
        int cmp = o1.compareTo(o2);
        return cmp;
    }

    public static <K extends Comparable<K>> RangeComparator<K> getInstance() {
        return new RangeComparator<>();
    }

}
