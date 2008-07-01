package net.bodz.extern.diff;

import java.util.List;

import net.bodz.bas.types.diff.DiffComparator;
import net.bodz.bas.types.diff.DiffInfo;

public class GNUDiff implements DiffComparator {

    @Override
    public List<DiffInfo> diffCompare(List<?> a, List<?> b) {
        _GNUDiff diff = new _GNUDiff(a, b);
        return diff.diff_2(false);
    }

}
