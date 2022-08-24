package net.bodz.bas.compare.java_diff_utils;

import java.util.List;

import net.bodz.bas.compare.IListComparator;
import net.bodz.bas.compare.IListCompareResult;

public class JaverComparator<T>
        implements
            IListComparator<T, T> {

    @Override
    public IListCompareResult<T, T> compare(List<? extends T> source, List<? extends T> target) {
        return null;
    }

}
