package net.bodz.bas.typer.std;

import net.bodz.bas.rtx.IOptions;

public abstract class AbstractFormatter<T>
        implements IFormatter<T> {

    @Override
    public final String format(T object) {
        return format(object, IOptions.NULL);
    }

}
