package net.bodz.bas.t.catalog;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

public class SchemaOidTypers
        extends AbstractCommonTypers<SchemaOid> {

    public SchemaOidTypers() {
        super(SchemaOid.class);
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
    public SchemaOid parse(String text, IOptions options)
            throws ParseException {
        if (text == null)
            return null;
        return SchemaOid.parse(text);
    }

}
