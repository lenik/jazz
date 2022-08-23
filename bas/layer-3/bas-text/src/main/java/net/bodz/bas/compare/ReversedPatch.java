package net.bodz.bas.compare;

public class ReversedPatch<T>
        implements
            IPatch<T> {

    IPatch<T> patch;

    public ReversedPatch(IPatch<T> patch) {
        this.patch = patch;
    }

    @Override
    public int getDeltaCount() {
        return patch.getDeltaCount();
    }

    @Override
    public IEditDelta<T> getDelta(int index) {
        return patch.getDelta(index);
    }

    @Override
    public void applyTo(T source)
            throws PatchException {
        patch.unapplyTo(source);
    }

    @Override
    public void unapplyTo(T target)
            throws PatchException {
        patch.applyTo(target);
    }

    @Override
    public T copy(T o) {
        return patch.copy(o);
    }

    @Override
    public T apply(T source)
            throws PatchException {
        return patch.apply(source);
    }

    @Override
    public T unapply(T target)
            throws PatchException {
        return patch.unapply(target);
    }

    @Override
    public IPatch<T> reverse() {
        return patch;
    }

}
