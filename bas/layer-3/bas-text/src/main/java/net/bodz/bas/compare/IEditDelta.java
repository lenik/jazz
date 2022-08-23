package net.bodz.bas.compare;

public interface IEditDelta<mutable_t> {

    IEditDeltaType getType();

    void apply(mutable_t source)
            throws PatchException;

    void unapply(mutable_t target)
            throws PatchException;

}
