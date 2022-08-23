package net.bodz.bas.compare;

import java.util.List;

public interface IListCompareResult<view_t, mutable_t>
        extends
            ICompareResult<List<? extends view_t>, List<mutable_t>> {

    @Override
    IListEditDelta<mutable_t> getDelta(int i);

    @Override
    IListPatch<mutable_t> patch();

}
