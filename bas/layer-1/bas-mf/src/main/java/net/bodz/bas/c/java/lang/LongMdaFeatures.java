package net.bodz.bas.c.java.lang;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.mf.std.ISampleGenerator;

public class LongMdaFeatures
        extends AbstractCommonMdaFeatures<Long> {

    public LongMdaFeatures() {
        super(Long.class);
    }

    @Override
    protected Object query(int mdaFeatureIndex) {
        switch (mdaFeatureIndex) {
        case IParser.mdaFeatureIndex:
        case ISampleGenerator.mdaFeatureIndex:
            return this;
        }
        return null;
    }

    @Override
    public Long parse(String text)
            throws ParseException {
        try {
            return Long.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Long newSample()
            throws CreateException {
        return random.nextLong();
    }

}
