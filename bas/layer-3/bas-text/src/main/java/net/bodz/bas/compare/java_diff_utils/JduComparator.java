package net.bodz.bas.compare.java_diff_utils;

import java.util.List;

import net.bodz.bas.compare.IListComparator;
import net.bodz.bas.compare.IListCompareResult;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;

public class JduComparator<T>
        implements
            IListComparator<T, T> {

    @SuppressWarnings("unchecked")
    @Override
    public IListCompareResult<T, T> compare(List<? extends T> source, List<? extends T> target) {
        Patch<T> patch = DiffUtils.diff((List<T>) source, (List<T>) target);
        JduResult<T> result = new JduResult<>(source, target, patch);
        return result;
    }

}
