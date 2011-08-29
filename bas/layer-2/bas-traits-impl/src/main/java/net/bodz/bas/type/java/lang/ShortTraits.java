package net.bodz.bas.type.java.lang;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;

public class ShortTraits
        extends AbstractCommonTraits<Short> {

    public ShortTraits() {
        super(Short.class);
    }

    @Override
    protected Object query(int traitsIndex) {
        switch (traitsIndex) {
        case IParser.traitsIndex:
        case ISampleGenerator.traitsIndex:
            return this;
        }
        return null;
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
