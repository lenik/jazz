package net.bodz.bas.mf.spi.array;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.IParser;

/**
 * The mdaFeatures for generic Object array.
 */
public abstract class ArrayMdaFeatures<T>
        extends AbstractArrayMdaFeatures<T[]> {

    public static final String textformSeparator = ",";

    private final Class<T> valueType;
    private IParser<T> valueParser;

    public ArrayMdaFeatures(Class<T> valueType) {
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
