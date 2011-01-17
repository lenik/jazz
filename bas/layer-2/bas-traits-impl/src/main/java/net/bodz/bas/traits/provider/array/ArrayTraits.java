package net.bodz.bas.traits.provider.array;

import net.bodz.bas.traits.IParser;
import net.bodz.bas.util.ArrayType;
import net.bodz.bas.util.exception.ParseException;

/**
 * The traits for generic Object array.
 */
public abstract class ArrayTraits<T>
        extends AbstractArrayTraits<T[]> {

    public static final String textformSeparator = ",";

    private final Class<T> valueType;

    private IParser<T> valueParser;

    public ArrayTraits(Class<T> valueType) {
        super(ArrayType.getArrayType(valueType));
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
