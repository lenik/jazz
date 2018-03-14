package net.bodz.bas.text.trie;

import org.junit.Assert;
import org.junit.Test;

public class BinaryReplacerTest
        extends Assert {

    @Test
    public void testReplace() {
        BinaryReplacer replacer = new BinaryReplacer(true);
        replacer.replace("hello".getBytes(), "world".getBytes());
        replacer.replace("foo".getBytes(), "bar".getBytes());
        byte[] dst = replacer.transform("Hello, foo. This is foo, hello, foo.".getBytes());

        assertArrayEquals("Hello, bar. This is bar, world, bar.".getBytes(), dst);
    }

}
