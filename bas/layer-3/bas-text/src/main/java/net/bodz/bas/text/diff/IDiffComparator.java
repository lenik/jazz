package net.bodz.bas.text.diff;

import java.util.List;

import net.bodz.bas.meta.lang.typer;

@typer.family(DiffComparatorTypers.class)
public interface IDiffComparator {

    List<DiffEntry> compareDiff(List<?> a, List<?> b);

}
