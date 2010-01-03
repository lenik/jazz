package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.aspect.annotation.Obtain;

@Obtain(registry = DiffComparators.class)
public interface DiffComparator {

    List<DiffInfo> diffCompare(List<?> a, List<?> b);

}
