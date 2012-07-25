package net.bodz.bas.c.string;

import net.bodz.bas.util.order.AbstractOrderComparator;

public class StringLengthComparator
        extends AbstractOrderComparator<String, Integer> {

    @Override
    public Integer getOrder(String string) {
        return string.length();
    }

    @Override
    protected int compareFallback(String o1, String o2) {
        return o1.compareTo(o2);
    }

    public static final StringLengthComparator INSTANCE = new StringLengthComparator();

}
