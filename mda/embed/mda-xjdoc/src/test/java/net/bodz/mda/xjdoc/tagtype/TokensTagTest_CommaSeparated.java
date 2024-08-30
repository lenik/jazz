package net.bodz.mda.xjdoc.tagtype;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class TokensTagTest_CommaSeparated
        extends Assert {

    TokensTag tag = new TokensTag(",", true);

    @Test
    public void testEmpty()
            throws ParseException {
        assertEquals(Arrays.asList(), tag._parse(""));
        assertEquals(Arrays.asList(""), tag._parse("   "));
    }

    @Test
    public void testSingle()
            throws ParseException {
        assertEquals(Arrays.asList("a"), tag._parse("a"));
        assertEquals(Arrays.asList("a"), tag._parse(" a "));
    }

    @Test
    public void testMultiple()
            throws ParseException {
        assertEquals(Arrays.asList("a", "b"), tag._parse("a, b"));
        assertEquals(Arrays.asList("a   b"), tag._parse(" a   b  "));
        assertEquals(Arrays.asList("a", "b c"), tag._parse("  a, b c  "));
    }

}
