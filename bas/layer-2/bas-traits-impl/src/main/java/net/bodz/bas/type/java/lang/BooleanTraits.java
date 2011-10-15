package net.bodz.bas.type.java.lang;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;

public class BooleanTraits
        extends AbstractCommonTraits<Boolean> {

    public BooleanTraits() {
        super(Boolean.class);
    }

    @Override
    protected Object query(int traitIndex) {
        switch (traitIndex) {
        case IParser.traitIndex:
        case ISampleGenerator.traitIndex:
            return this;
        }
        return null;
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
