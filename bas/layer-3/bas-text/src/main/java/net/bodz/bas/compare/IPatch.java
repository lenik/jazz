package net.bodz.bas.compare;

public interface IPatch<mutable_t> {

    void applyTo(mutable_t source)
            throws PatchException;

    void unapplyTo(mutable_t target)
            throws PatchException;

    mutable_t copy(mutable_t o);

    default mutable_t apply(mutable_t source)
            throws PatchException {
        mutable_t copy = copy(source);
        applyTo(copy);
        return copy;
    }

    default mutable_t unapply(mutable_t target)
            throws PatchException {
        mutable_t copy = copy(target);
        unapplyTo(copy);
        return copy;
    }

    int getDeltaCount();

    IEditDelta<mutable_t> getDelta(int index);

    default IPatch<mutable_t> reverse() {
        return new ReversedPatch<>(this);
    }

}
