package net.bodz.bas.typer.spi.array;

import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.typer.std.AbstractCommonTypers;

public abstract class AbstractArrayTypers<T>
        extends AbstractCommonTypers<T> {

    /**
     * The min length of the sample char array, in Integer.
     */
    @ParameterType(Integer.class)
    public static final String OPTION_MIN_LENGTH = "sample.minLength";
    public static final int DEFAULT_MIN_LENGTH = 0;

    /**
     * The max length of the sample char array, in Integer.
     */
    @ParameterType(Integer.class)
    public static final String OPTION_MAX_LENGTH = "sample.maxLength";
    public static final int DEFAULT_MAX_LENGTH = 32;

    public AbstractArrayTypers(Class<T> arrayType) {
        super(arrayType);
    }

}
