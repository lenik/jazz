package net.bodz.bas.c.java.lang;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.tf.std.ISampleGenerator;

public class LongTypeFeatures
        extends AbstractCommonTypeFeatures<Long> {

    public LongTypeFeatures() {
        super(Long.class);
    }

    @Override
    protected Object _query(int typeFeatureIndex) {
        switch (typeFeatureIndex) {
        case IParser.typeFeatureIndex:
        case ISampleGenerator.typeFeatureIndex:
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
