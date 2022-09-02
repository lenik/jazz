package net.bodz.bas.compare.diff_match_patch;

import java.util.List;

import net.bodz.bas.compare.IListEditDelta;
import net.bodz.bas.compare.IListEditDeltaType;
import net.bodz.bas.compare.ListEditDeltaType;
import net.bodz.bas.compare.PatchException;
import net.bodz.bas.compare.dmp.IRowDifference;
import net.bodz.bas.compare.dmp.Patch;
import net.bodz.bas.err.NotImplementedException;

public class DMPPatchDelta<cell_t>
        implements
            IListEditDelta<cell_t> {

    Patch<cell_t> patch;

    public DMPPatchDelta(Patch<cell_t> patch) {
        this.patch = patch;
    }

    @Override
    public void apply(List<cell_t> source)
            throws PatchException {
        // delete(source,patch.start1, patch.length1);
        // insert(source,patch.)
        throw new NotImplementedException();
    }

    @Override
    public void unapply(List<cell_t> target)
            throws PatchException {
        throw new NotImplementedException();
    }

    @Override
    public IListEditDeltaType getType() {
        int inserts = 0, deletes = 0;
        for (IRowDifference<cell_t> diff : patch.diffs) {
            switch (diff.getDifferenceType()) {
            case INSERTION:
                inserts++;
                break;
            case REMOVAL:
                deletes++;
                break;
            default:
            }
        }
        if (inserts == 0)
            if (deletes == 0)
                return ListEditDeltaType.EQUAL;
            else
                return ListEditDeltaType.DELETE;
        else if (deletes == 0)
            return ListEditDeltaType.INSERT;
        else
            return ListEditDeltaType.CHANGE;
    }

}
