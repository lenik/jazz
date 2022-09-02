package net.bodz.bas.compare.diff_match_patch;

import java.util.List;

import net.bodz.bas.compare.ICompareResultVisitor;
import net.bodz.bas.compare.IListCompareResult;
import net.bodz.bas.compare.IListEditDelta;
import net.bodz.bas.compare.IListPatch;
import net.bodz.bas.compare.dmp.DifferenceType;
import net.bodz.bas.compare.dmp.EditList;
import net.bodz.bas.compare.dmp.IRowDifference;
import net.bodz.bas.compare.dmp.PatchList;
import net.bodz.bas.compare.dmp.RowEdit;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.text.row.IRow;

public class DMPResult<cell_t>
        implements
            IListCompareResult<cell_t, cell_t> {

    EditList<cell_t> editList;

    public DMPResult(EditList<cell_t> editList) {
        this.editList = editList;
    }

    @Override
    public List<? extends cell_t> getSource() {
        IRow<cell_t> row1 = editList.restoreRow1();
        return new RowList<cell_t>(row1);
    }

    @Override
    public List<? extends cell_t> getTarget() {
        IRow<cell_t> row2 = editList.restoreRow2();
        return new RowList<cell_t>(row2);
    }

    @Override
    public boolean isSame() {
        for (IRowDifference<cell_t> diff : editList)
            if (diff.getDifferenceType() != DifferenceType.MATCH)
                return false;
        return true;
    }

    @Override
    public int getDeltaCount() {
        return editList.size();
    }

    @SuppressWarnings("deprecation")
    @Override
    public IListEditDelta<cell_t> getDelta(int i) {
        RowEdit<cell_t> edit = editList.get(i);
        return new DMPDelta<>(edit);
    }

    @Override
    public IListPatch<cell_t> patch() {
        PatchList<cell_t> patchList = editList.createPatch();
        return new DMPPatch<>(patchList);
    }

    @Override
    public void accept(ICompareResultVisitor visitor) {
        throw new NotImplementedException();
    }

}
