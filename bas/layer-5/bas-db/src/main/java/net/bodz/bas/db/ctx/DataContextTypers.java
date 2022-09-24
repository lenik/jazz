package net.bodz.bas.db.ctx;

import java.util.regex.Pattern;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;

public class DataContextTypers
        extends AbstractCommonTypers<DataContext> {

    public DataContextTypers() {
        super(DataContext.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        if (typerIndex == IParser.typerIndex)
            return this;
        return null;
    }

    static Pattern ID = Pattern.compile("^[A-Za-z0-9_\\-]+(\\.[A-Za-z0-9_\\-]+)*$");

    @Override
    public DataContext parse(String text, IOptions options)
            throws ParseException {
        if (ID.matcher(text).matches()) {
            String id = text;
            DataContext dataContext = DataHub.getPreferredHub().getDataContext(id);
            if (dataContext == null)
                throw new ParseException("undefined data context id: " + id);
            else
                return dataContext;
        }
        throw new ParseException("invalid data context specifier: " + text);
    }

}
