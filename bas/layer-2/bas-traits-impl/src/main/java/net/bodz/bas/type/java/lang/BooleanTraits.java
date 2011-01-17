package net.bodz.bas.type.java.lang;

import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.util.exception.ParseException;

public class BooleanTraits
        extends AbstractCommonTraits<Boolean> {

    public BooleanTraits() {
        super(Boolean.class);
    }

    @Override
    public IParser<Boolean> getParser() {
        return this;
    }

    @Override
    public ISampleGenerator<Boolean> getSampleGenerator() {
        return this;
    }

    @Override
    public Boolean parse(String text)
            throws ParseException {
        try {
            return Boolean.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean newSample()
            throws CreateException {
        return random.nextBoolean();
    }

}
