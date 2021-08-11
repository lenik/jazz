package net.bodz.bas.typer.std;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;

public abstract class AbstractTextForm<T>
        implements
            ITextForm<T> {

    @Override
    public final String format(T object)
            throws FormatException {
        return format(object, IOptions.NULL);
    }

    @Override
    public final T parse(String text)
            throws ParseException {
        return parse(text, IOptions.NULL);
    }

}
