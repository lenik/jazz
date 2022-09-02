package net.bodz.bas.compare.diff_match_patch;

import java.util.List;

import net.bodz.bas.compare.IListEditDelta;
import net.bodz.bas.compare.IListEditDeltaType;
import net.bodz.bas.compare.ListEditDeltaType;
import net.bodz.bas.compare.PatchException;
import net.bodz.bas.compare.dmp.IRowDifference;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.UnexpectedException;

public class DMPDelta<cell_t>
        implements
            IListEditDelta<cell_t> {

    IRowDifference<cell_t> diff;

    public DMPDelta(IRowDifference<cell_t> diff) {
        this.diff = diff;
    }

    @Override
    public void apply(List<cell_t> source)
            throws PatchException {
//        MutableRow<cell_t> row = new MutableRow<>(source);
//        diff.getDelta();
        throw new NotImplementedException();
    }

    @Override
    public void unapply(List<cell_t> target)
            throws PatchException {
//        MutableRow<cell_t> row = new MutableRow<>(target);
//        diff.getDelta();
        throw new NotImplementedException();
    }

    @Override
    public IListEditDeltaType getType() {
        switch (diff.getDifferenceType()) {
        case MATCH:
            return ListEditDeltaType.EQUAL;
        case INSERTION:
            return ListEditDeltaType.INSERT;
        case REMOVAL:
            return ListEditDeltaType.DELETE;
        default:
            throw new UnexpectedException();
        }
    }

}
