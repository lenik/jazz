package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.meta.lang.TraitsClass;

@TraitsClass(DiffComparatorTraits.class)
public interface DiffComparator {

    List<DiffInfo> diffCompare(List<?> a, List<?> b);

}
