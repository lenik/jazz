package net.bodz.bas.t._int;

import java.util.Comparator;

/**
 * @see Comparator
 */
public interface IntComparator {

    int compare(int a, int b);

    IntComparator ASCEND = new AscendIntComp();
    IntComparator DESCEND = new DescendIntComp();

}

class AscendIntComp
        implements IntComparator {

    @Override
    public int compare(int a, int b) {
        return a - b;
    }

}

class DescendIntComp
        implements IntComparator {

    @Override
    public int compare(int a, int b) {
        return b - a;
    }

}
