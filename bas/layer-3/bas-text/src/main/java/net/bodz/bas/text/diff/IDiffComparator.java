package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.meta.lang.TraitClass;

@TraitClass(DiffComparatorTraits.class)
public interface IDiffComparator {

    List<DiffEntry> compareDiff(List<?> a, List<?> b);

}
