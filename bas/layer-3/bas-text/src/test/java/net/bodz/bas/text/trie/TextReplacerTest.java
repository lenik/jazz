package net.bodz.bas.text.trie;

import org.junit.Assert;
import org.junit.Test;

public class TextReplacerTest
        extends Assert {

    @Test
    public void testReplace() {
        TextReplacer replacer = new TextReplacer();
        replacer.replace("hello", "world");
        replacer.replace("foo", "bar");
        String dst = replacer.transform("Hello, foo. This is foo, hello, foo.");
        assertEquals("Hello, bar. This is bar, world, bar.", dst);
    }

}
