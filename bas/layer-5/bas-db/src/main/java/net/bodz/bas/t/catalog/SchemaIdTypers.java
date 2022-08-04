package net.bodz.bas.t.catalog;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

public class SchemaIdTypers
        extends AbstractCommonTypers<SchemaId> {

    public SchemaIdTypers() {
        super(SchemaId.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public SchemaId parse(String text, IOptions options)
            throws ParseException {
        if (text == null)
            return null;
        return SchemaId.parse(text);
    }

}
