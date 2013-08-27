package net.bodz.bas.typer.spi.array;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.typer.std.IParser;

/**
 * The typers for generic Object array.
 */
public abstract class ArrayTypers<T>
        extends AbstractArrayTypers<T[]> {

    public static final String textformSeparator = ",";

    private final Class<T> valueType;
    private IParser<T> valueParser;

    public ArrayTypers(Class<T> valueType) {
        super(Arrays.getArrayType(valueType));
        this.valueType = valueType;
    }

    @Override
    public IParser<T[]> getParser() {
        return this;
    }

    @Override
    public T[] parse(String text)
            throws ParseException {
        return super.parse(text);
    }

}
