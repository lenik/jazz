package net.bodz.bas.tf.spi.array;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.tf.std.IParser;

/**
 * The typeFeatures for generic Object array.
 */
public abstract class ArrayTypeFeatures<T>
        extends AbstractArrayTypeFeatures<T[]> {

    public static final String textformSeparator = ",";

    private final Class<T> valueType;
    private IParser<T> valueParser;

    public ArrayTypeFeatures(Class<T> valueType) {
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
