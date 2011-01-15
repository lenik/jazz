package net.bodz.bas.type.java.lang;

import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.util.exception.ParseException;

public class ByteTraits
        extends AbstractCommonTraits<Byte> {

    public ByteTraits() {
        super(Byte.class);
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
