package net.bodz.mda.xjdoc.tagtype;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;

public class TokensTagTypeTest_SpaceSeparated
        extends Assert {

    TokensTagType tagType = TokensTagType.SPACE_SEPARATED;

    @Test
    public void testEmpty()
            throws ParseException {
        assertEquals(Arrays.asList(), tagType._parse(""));
        assertEquals(Arrays.asList(), tagType._parse("   "));
    }

    @Test
    public void testSingle()
            throws ParseException {
        assertEquals(Arrays.asList("a"), tagType._parse("a"));
        assertEquals(Arrays.asList("a"), tagType._parse(" a "));
    }

    @Test
    public void testMultiple()
            throws ParseException {
        assertEquals(Arrays.asList("a", "b"), tagType._parse("a b"));
        assertEquals(Arrays.asList("a", "b"), tagType._parse(" a   b  "));
        assertEquals(Arrays.asList("a,b", "c"), tagType._parse("  a,b c  "));
    }

}
