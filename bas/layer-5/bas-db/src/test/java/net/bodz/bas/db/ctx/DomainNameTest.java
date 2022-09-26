package net.bodz.bas.db.ctx;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DomainNameTest {

    @Test
    public void testReverse() {
        testReverse("", "");
        testReverse("foo", "foo");
        testReverse("foo.bar", "bar.foo");
        testReverse(".", ".");
        testReverse("..", "..");
        testReverse("foo.", ".foo");
        testReverse("foo..", "..foo");
        testReverse("..foo", "foo..");
    }

    void testReverse(String in, String out) {
        String actual = DomainName.reverse(in);
        assertEquals(out, actual);
    }

}
