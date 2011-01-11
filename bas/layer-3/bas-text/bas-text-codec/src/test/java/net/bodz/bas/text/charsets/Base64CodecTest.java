package net.bodz.bas.text.charsets;

import junit.framework.TestCase;
import net.bodz.bas.text.codec.builtin.Base64Codec;

import org.junit.Test;

public class Base64CodecTest
        extends TestCase {

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

}
