package net.bodz.bas.text.diff;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IInstanceStore;
import net.bodz.bas.typer.std.IParser;

public class DiffFormatTypers
        extends AbstractCommonTypers<IDiffFormat> {

    public DiffFormatTypers() {
        super(IDiffFormat.class);
        addStaticFieldsToStore(IDiffFormat.class);
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
    public IDiffFormat parse(String name, IOptions options)
            throws ParseException {
        IDiffFormat instance = getInstance(name);
        if (instance == null)
            throw new ParseException("Undefined diff format: " + name);
        else
            return instance;
    }

}
