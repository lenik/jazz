package net.bodz.bas.text.diff;

import net.bodz.bas.typer.std.AbstractCommonTypers;

public class DiffComparatorTypers
        extends AbstractCommonTypers<IDiffComparator> {

    public DiffComparatorTypers() {
        super(IDiffComparator.class);
        addStaticFieldsToStore(DiffComparators.class);
    }

    @Override
    protected Object _query(int typerIndex) {
        return null;
    }

}
