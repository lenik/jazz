package net.bodz.bas.compare.diff_match_patch;

import net.bodz.bas.compare.IListEditDelta;
import net.bodz.bas.compare.IListPatch;
import net.bodz.bas.compare.dmp.Patch;
import net.bodz.bas.compare.dmp.PatchList;

public class DMPPatch<cell_t>
        implements
            IListPatch<cell_t> {

    PatchList<cell_t> patchList;

    public DMPPatch(PatchList<cell_t> patchList) {
        this.patchList = patchList;
    }

    @Override
    public int getDeltaCount() {
        return patchList.size();
    }

    @Override
    public IListEditDelta<cell_t> getDelta(int index) {
        Patch<cell_t> patch = patchList.get(index);
        return new DMPPatchDelta<>(patch);
    }

}
