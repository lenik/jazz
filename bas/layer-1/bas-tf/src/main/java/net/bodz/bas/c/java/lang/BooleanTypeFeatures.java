package net.bodz.bas.c.java.lang;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.tf.std.ISampleGenerator;

public class BooleanTypeFeatures
        extends AbstractCommonTypeFeatures<Boolean> {

    public BooleanTypeFeatures() {
        super(Boolean.class);
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
