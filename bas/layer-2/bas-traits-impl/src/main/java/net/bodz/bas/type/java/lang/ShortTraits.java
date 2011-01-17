package net.bodz.bas.type.java.lang;

import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.util.exception.ParseException;

public class ShortTraits
        extends AbstractCommonTraits<Short> {

    public ShortTraits() {
        super(Short.class);
    }

    @Override
    public IParser<Short> getParser() {
        return this;
    }

    @Override
    public ISampleGenerator<Short> getSampleGenerator() {
        return this;
    }

    @Override
    public Short parse(String text)
            throws ParseException {
        try {
            return Short.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Short newSample()
            throws CreateException {
        return (short) random.nextInt();
    }

}
