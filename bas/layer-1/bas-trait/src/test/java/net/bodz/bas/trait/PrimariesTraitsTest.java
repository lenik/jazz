package net.bodz.bas.trait;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.traits.ParserUtil;

public class PrimariesTraitsTest
        extends Assert {

    @Test
    public void parseInt()
            throws ParseException {
        Integer value = ParserUtil.parse(int.class, "123");
        assertEquals(new Integer(123), value);
    }

}
