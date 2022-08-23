package net.bodz.bas.compare;

import java.util.ArrayList;
import java.util.List;

public interface IListPatch<mutable_t>
        extends
            IPatch<List<mutable_t>> {

    @Override
    IListEditDelta<mutable_t> getDelta(int index);

    @Override
    default void applyTo(List<mutable_t> source)
            throws PatchException {
        int n = getDeltaCount();
        for (int i = n - 1; i >= 0; i--) {
            IListEditDelta<mutable_t> delta = getDelta(i);
            delta.apply(source);
        }
    }

    @Override
    default void unapplyTo(List<mutable_t> target)
            throws PatchException {
        int n = getDeltaCount();
        for (int i = n - 1; i >= 0; i--) {
            IListEditDelta<mutable_t> delta = getDelta(i);
            delta.unapply(target);
        }
    }

    @Override
    default List<mutable_t> copy(List<mutable_t> list) {
        ArrayList<mutable_t> copy = new ArrayList<>(list.size());
        for (mutable_t item : list)
            copy.add(internalCopy(item));
        return copy;
    }

    default mutable_t internalCopy(mutable_t o) {
        return o;
    }

    @Override
    default IListPatch<mutable_t> reverse() {
        return new ReversedListPatch<>(this);
    }

}
