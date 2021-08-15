package net.bodz.bas.typer;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.typer.std.ParserUtil;

public class PrimariesTypersTest
        extends Assert {

    @Test
    public void parseInt()
            throws ParseException {
        Integer value = ParserUtil.parse(int.class, "123");
        assertEquals(Integer.valueOf(123), value);
    }

}
