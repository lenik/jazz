package net.bodz.bas.compare.gnudiff;

import java.util.List;

import net.bodz.bas.compare.IListEditDelta;
import net.bodz.bas.compare.IListEditDeltaType;
import net.bodz.bas.compare.ListEditDeltaType;
import net.bodz.bas.compare.PatchException;

public class DiffEntry<T>
        implements
            IListEditDelta<T> {

    final List<? extends T> list0;
    final List<? extends T> list1;

    public final int index0;
    public final int index1;
    public final int deleted;
    public final int inserted;

    public DiffEntry(List<? extends T> list0, List<? extends T> list1, int index0, int index1, int deleted,
            int inserted) {
        this.list0 = list0;
        this.list1 = list1;
        this.index0 = index0;
        this.index1 = index1;
        this.deleted = deleted;
        this.inserted = inserted;
    }

    @Override
    public IListEditDeltaType getType() {
        if (deleted == 0) {
            if (inserted == 0)
                return ListEditDeltaType.EQUAL;
            else
                return ListEditDeltaType.INSERT;
        } else {
            if (inserted == 0)
                return ListEditDeltaType.DELETE;
            else
                return ListEditDeltaType.CHANGE;
        }
    }

    @Override
    public void apply(List<T> source)
            throws PatchException {
        if (deleted > 0) {
            int end0 = index0 + deleted;
            if (end0 > source.size())
                throw new PatchException();
            for (int c = 0; c < deleted; c++)
                source.remove(--end0);
        }
        if (inserted > 0) {
            int pos = index0;
            for (int i = 0; i < inserted; i++) {
                T s = list1.get(index1 + i);
                source.add(pos++, s);
            }
        }
    }

    @Override
    public void unapply(List<T> target)
            throws PatchException {
        if (inserted > 0) {
            int end1 = index1 + inserted;
            if (end1 > target.size())
                throw new PatchException();
            for (int c = 0; c < inserted; c++)
                target.remove(--end1);
        }
        if (deleted > 0) {
            int pos = index1;
            for (int i = 0; i < deleted; i++) {
                T s = list0.get(index0 + i);
                target.add(pos++, s);
            }
        }
    }

}
