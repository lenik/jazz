package net.bodz.bas.compare;

public interface ICompareResult<view_t, mutable_t> {

    view_t getSource();

    view_t getTarget();

    boolean isSame();

    default boolean isDifferent() {
        return !isSame();
    }

    int getDeltaCount();

    IEditDelta<mutable_t> getDelta(int i);

    IPatch<mutable_t> patch();

    void accept(ICompareResultVisitor visitor);

}
