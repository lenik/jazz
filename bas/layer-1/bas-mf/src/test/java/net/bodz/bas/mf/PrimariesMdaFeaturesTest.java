package net.bodz.bas.mf;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.mf.std.ParserUtil;

public class PrimariesMdaFeaturesTest
        extends Assert {

    @Test
    public void parseInt()
            throws ParseException {
        Integer value = ParserUtil.parse(int.class, "123");
        assertEquals(new Integer(123), value);
    }

}
