package net.bodz.bas.c.string;

import org.junit.Assert;
import org.junit.Test;

public class StringIdTest
        extends Assert {

    @Test
    public void testXmlTagName() {
        assertFalse(StringId.isXmlTagName(""));

        assertTrue(StringId.isXmlTagName("f"));
        assertTrue(StringId.isXmlTagName("f1"));
        assertFalse(StringId.isXmlTagName("1"));
        assertTrue(StringId.isXmlTagName("f-1"));
        assertFalse(StringId.isXmlTagName("f-"));
        assertFalse(StringId.isXmlTagName("-f"));
        assertFalse(StringId.isXmlTagName("-"));
        assertTrue(StringId.isXmlTagName("f-b"));
        assertTrue(StringId.isXmlTagName("foo-bar"));

        assertTrue(StringId.isXmlTagName("水"));
        assertTrue(StringId.isXmlTagName("水果"));

        assertFalse(StringId.isXmlTagName("foo_bar"));
        assertFalse(StringId.isXmlTagName("a?"));
        assertFalse(StringId.isXmlTagName("水果。"));
        assertFalse(StringId.isXmlTagName("a space"));
    }

}
