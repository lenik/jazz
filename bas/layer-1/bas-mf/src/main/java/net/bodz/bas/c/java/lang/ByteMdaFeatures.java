package net.bodz.bas.c.java.lang;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.mf.std.ISampleGenerator;

public class ByteMdaFeatures
        extends AbstractCommonMdaFeatures<Byte> {

    public ByteMdaFeatures() {
        super(Byte.class);
    }

    @Override
    protected Object _query(int mdaFeatureIndex) {
        switch (mdaFeatureIndex) {
        case IParser.mdaFeatureIndex:
        case ISampleGenerator.mdaFeatureIndex:
            return this;
        }
        return null;
    }

    @Override
    public Byte parse(String text)
            throws ParseException {
        try {
            return Byte.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Byte newSample()
            throws CreateException {
        return (byte) random.nextInt();
    }

}
