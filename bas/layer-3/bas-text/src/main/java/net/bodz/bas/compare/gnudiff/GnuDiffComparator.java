package net.bodz.bas.compare.gnudiff;

import java.util.List;

import net.bodz.bas.compare.IListComparator;

public class GnuDiffComparator
        implements
            IListComparator<Object, Object> {

    boolean reverse;

    @Override
    public GnuDiffCompareResult<Object> compare(List<?> source, List<?> target) {
        GnuDiffCompareResult<Object> result = new GnuDiffCompareResult<>(source, target);

        GNUDiff gnuDiff = new GNUDiff(source, target);
        List<DiffEntry<Object>> script = gnuDiff.diff_2(reverse);

        for (DiffEntry<Object> ent : script)
            result.add(ent);

        return result;
    }

}
