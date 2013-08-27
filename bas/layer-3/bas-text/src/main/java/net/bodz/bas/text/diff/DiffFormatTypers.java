package net.bodz.bas.text.diff;

import net.bodz.bas.typer.std.AbstractCommonTypers;

public class DiffFormatTypers
        extends AbstractCommonTypers<IDiffFormat> {

    public DiffFormatTypers() {
        super(IDiffFormat.class);
        addStaticFieldsToStore(IDiffFormat.class);
    }

    @Override
    protected Object _query(int typerIndex) {
        return null;
    }

}
