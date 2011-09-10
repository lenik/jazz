package net.bodz.bas.text.codec.builtin;

import org.junit.Assert;
import org.junit.Test;

public class Base64CodecTest
        extends Assert {

    @Test
    public void testBase64Codec() {
        Base64Codec c = Base64Codec.getInstance();
        String s1 = "abcdefabcx";
        byte[] b1 = s1.getBytes();
        String t1 = c.encode(b1);
        assertEquals("YWJjZGVmYWJjeA==", t1);

        byte[] b2 = c.decode(t1);
        String s2 = new String(b2);
        assertEquals(s1, s2);
    }

}
