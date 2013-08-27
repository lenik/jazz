package net.bodz.bas.typer.std;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.NoOptions;

public abstract class AbstractParser<T>
        implements IParser<T> {

    @Override
    public T parse(String text)
            throws ParseException {
        return parse(text, NoOptions.getInstance());
    }

}
