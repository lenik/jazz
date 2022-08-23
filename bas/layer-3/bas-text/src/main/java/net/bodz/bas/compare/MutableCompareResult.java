package net.bodz.bas.compare;

import java.util.ArrayList;

public abstract class MutableCompareResult<view_t, mutable_t, delta_t extends IEditDelta<mutable_t>>
        extends ArrayList<delta_t>
        implements
            ICompareResult<view_t, mutable_t> {

    private static final long serialVersionUID = 1L;

    view_t source;
    view_t target;

    public MutableCompareResult(view_t source, view_t target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public view_t getSource() {
        return source;
    }

    @Override
    public view_t getTarget() {
        return target;
    }

    @Override
    public boolean isSame() {
        return isEmpty();
    }

    @Override
    public int getDeltaCount() {
        return size();
    }

    @Override
    public delta_t getDelta(int i) {
        return get(i);
    }

    @Override
    public void accept(ICompareResultVisitor visitor) {
        int n = getDeltaCount();
        for (int i = 0; i < n; i++)
            visitor.delta();
    }

}
