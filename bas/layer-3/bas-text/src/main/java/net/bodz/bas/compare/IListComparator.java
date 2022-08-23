package net.bodz.bas.compare;

import java.util.List;

public interface IListComparator<view_t, mutable_t>
        extends
            IContentComparator<List<? extends view_t>, List<mutable_t>> {

    @Override
    IListCompareResult<view_t, mutable_t> compare(List<? extends view_t> source, List<? extends view_t> target);

}
