package net.bodz.bas.trait.spi.array;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.traits.IParser;

/**
 * The traits for generic Object array.
 */
public abstract class ArrayTraits<T>
        extends AbstractArrayTraits<T[]> {

    public static final String textformSeparator = ",";

    private final Class<T> valueType;
    private IParser<T> valueParser;

    public ArrayTraits(Class<T> valueType) {
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
