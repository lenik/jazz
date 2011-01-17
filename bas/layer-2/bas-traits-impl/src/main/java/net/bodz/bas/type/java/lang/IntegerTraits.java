package net.bodz.bas.type.java.lang;

import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.util.exception.ParseException;

public class IntegerTraits
        extends AbstractCommonTraits<Integer> {

    public IntegerTraits() {
        super(Integer.class);
    }

    @Override
    public IParser<Integer> getParser() {
        return this;
    }

    @Override
    public ISampleGenerator<Integer> getSampleGenerator() {
        return this;
    }

    @Override
    public Integer parse(String text)
            throws ParseException {
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Integer newSample()
            throws CreateException {
        return random.nextInt();
    }

}
