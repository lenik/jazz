package net.bodz.bas.types.diff;

import java.util.List;

import net.bodz.bas.types.Obtain;

@Obtain(registry = DiffComparators.class)
public interface DiffComparator {

    List<DiffInfo> diffCompare(List<?> a, List<?> b);

}
