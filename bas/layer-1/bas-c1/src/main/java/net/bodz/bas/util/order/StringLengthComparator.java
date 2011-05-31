package net.bodz.bas.util.order;

public class StringLengthComparator
        extends OrderComparator<String, Integer> {

    @Override
    public Integer getOrder(String string) {
        return string.length();
    }

    public static final StringLengthComparator INSTANCE = new StringLengthComparator();

}
