package net.bodz.bas.typer.spi.array;

import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.typer.std.AbstractCommonTypers;

public abstract class AbstractArrayTypers<T>
        extends AbstractCommonTypers<T> {

    /**
     * The min length of the sample char array, in Integer.
     */
    @ParameterType(Integer.class)
    public static final String sampleMinLength = "sample.minLength";
    public static final int defaultSampleMinLength = 0;

    /**
     * The max length of the sample char array, in Integer.
     */
    @ParameterType(Integer.class)
    public static final String sampleMaxLength = "sample.maxLength";
    public static final int defaultSampleMaxLength = 32;

    public AbstractArrayTypers(Class<T> arrayType) {
        super(arrayType);
    }

}
