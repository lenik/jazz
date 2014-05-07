package net.bodz.bas.text.diff;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IInstanceStore;
import net.bodz.bas.typer.std.IParser;

public class DiffComparatorTypers
        extends AbstractCommonTypers<IDiffComparator> {

    public DiffComparatorTypers() {
        super(IDiffComparator.class);
        addStoreInstancesFromStaticFields(DiffComparators.class, IDiffComparator.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case IInstanceStore.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public IDiffComparator parse(String name, IOptions options)
            throws ParseException {
        IDiffComparator instance = getInstance(name);
        if (instance == null)
            throw new ParseException("Unknown comparator: " + name);
        else
            return instance;
    }

}
