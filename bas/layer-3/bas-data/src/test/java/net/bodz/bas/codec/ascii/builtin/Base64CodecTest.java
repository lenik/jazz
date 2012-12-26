package net.bodz.bas.codec.ascii.builtin;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.data.codec.builtin.Base64Codec;

public class Base64CodecTest
        extends Assert {

    Base64Codec base64Codec = Base64Codec.getInstance();

    @Test
    public void testEncode()
            throws Exception {
        class D {
            void o(String input, String expected)
                    throws Exception {
                String actual = base64Codec.encode(input.getBytes("ascii"));
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", "");
        d.o("hello", "aGVsbG8=");
        d.o("[\0\n\r\t]", "WwAKDQld");
    }

    @Test
    public void testDecode()
            throws Exception {
        class D {
            void o(String input, String expected)
                    throws Exception {
                byte[] decode = base64Codec.decode(input);
                String actual = new String(decode, "ascii");
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", "");
        d.o("aGVsbG8=", "hello");
        d.o("WwAKDQld", "[\0\n\r\t]");
    }

    @Test
    public void testBase64Codec() {
        String s1 = "abcdefabcx";
        byte[] b1 = s1.getBytes();
        String t1 = base64Codec.encode(b1);
        assertEquals("YWJjZGVmYWJjeA==", t1);

        byte[] b2 = base64Codec.decode(t1);
        String s2 = new String(b2);
        assertEquals(s1, s2);
    }

}
