package net.bodz.bas.text.charsets;

import org.junit.Assert;
import org.junit.Test;

/**
 * @see HexCharsetEncoder
 */
public class HexCharsetEncoderTest
        extends Assert {

    @Test
    public void testEncode()
            throws Exception {
        class D {
            void o(String input, String expected)
                    throws Exception {
                byte[] bytes = input.getBytes("hex");
                String actual = new String(bytes, "ascii");
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("", "");
        d.o("hello", "68 65 6c 6c 6f");
        d.o("汉字", "6c49 5b57");
        d.o("123汉4字56", "31 32 33 6c49 34 5b57 35 36");
    }

}
