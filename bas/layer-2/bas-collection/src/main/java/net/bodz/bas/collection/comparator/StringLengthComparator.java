package net.bodz.bas.collection.comparator;

public class StringLengthComparator
        extends OrderComparator<String, Integer> {

    @Override
    public Integer getOrder(String string) {
        return string.length();
    }

    static final StringLengthComparator instance = new StringLengthComparator();

    public static StringLengthComparator getInstance() {
        return instance;
    }

}
