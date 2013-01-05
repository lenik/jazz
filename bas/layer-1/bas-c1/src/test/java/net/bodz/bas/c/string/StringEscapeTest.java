package net.bodz.bas.c.string;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class StringEscapeTest
        extends Assert {

    @Test
    public void testUnescapeJava_uchars()
            throws ParseException {
        String str = StringEscape.unescapeJava("\"one\\t\u0031\"");
        assertEquals("one\t1", str);
    }

}
