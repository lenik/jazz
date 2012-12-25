package net.bodz.bas.text.diff.gnudiff;

import java.util.List;

import net.bodz.bas.text.diff.DiffEntry;
import net.bodz.bas.text.diff.IDiffComparator;

public class GNUDiffComparator
        implements IDiffComparator {

    @Override
    public List<DiffEntry> compareDiff(List<?> a, List<?> b) {
        GNUDiff gnuDiff = new GNUDiff(a, b);
        return gnuDiff.diff_2(false);
    }

}
