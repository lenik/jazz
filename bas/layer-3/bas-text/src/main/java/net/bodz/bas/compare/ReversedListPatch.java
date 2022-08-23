package net.bodz.bas.compare;

import java.util.List;

public class ReversedListPatch<T>
        implements
            IListPatch<T> {

    IListPatch<T> patch;

    public ReversedListPatch(IListPatch<T> patch) {
        this.patch = patch;
    }

    @Override
    public int getDeltaCount() {
        return patch.getDeltaCount();
    }

    @Override
    public IListEditDelta<T> getDelta(int index) {
        return patch.getDelta(index);
    }

    @Override
    public void applyTo(List<T> source)
            throws PatchException {
        patch.unapplyTo(source);
    }

    @Override
    public void unapplyTo(List<T> target)
            throws PatchException {
        patch.applyTo(target);
    }

    @Override
    public IListPatch<T> reverse() {
        return patch;
    }

}
