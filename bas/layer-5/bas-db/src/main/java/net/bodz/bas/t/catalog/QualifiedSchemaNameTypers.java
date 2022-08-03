package net.bodz.bas.t.catalog;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

public class QualifiedSchemaNameTypers
        extends AbstractCommonTypers<QualifiedSchemaName> {

    public QualifiedSchemaNameTypers() {
        super(QualifiedSchemaName.class);
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
    public QualifiedSchemaName parse(String text, IOptions options)
            throws ParseException {
        if (text == null)
            return null;
        return QualifiedSchemaName.fromFullName(text);
    }

}
