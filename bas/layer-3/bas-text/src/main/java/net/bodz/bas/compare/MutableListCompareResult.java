package net.bodz.bas.compare;

import java.util.List;

public class MutableListCompareResult<view_t, mutable_t, delta_t extends IListEditDelta<mutable_t>>
        extends MutableCompareResult<List<? extends view_t>, List<mutable_t>, delta_t>
        implements
            IListCompareResult<view_t, mutable_t>,
            IListPatch<mutable_t> {

    private static final long serialVersionUID = 1L;

    public MutableListCompareResult(List<? extends view_t> source, List<? extends view_t> target) {
        super(source, target);
    }

    @Override
    public IListPatch<mutable_t> patch() {
        return this;
    }

}
