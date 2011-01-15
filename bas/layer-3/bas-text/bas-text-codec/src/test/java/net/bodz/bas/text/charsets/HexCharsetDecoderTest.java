package net.bodz.bas.text.charsets;

import org.junit.Assert;
import org.junit.Test;

/**
 * @see HexCharsetDecoder
 */
public class HexCharsetDecoderTest
        extends Assert {

    @Test
    public void testDecode()
            throws Exception {
        class D {
            void o(String input, String expected)
                    throws Exception {
                byte[] bytes = input.getBytes("ascii");
                String actual = new String(bytes, "hex");
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", "");
        d.o("68 65 6C 6c 6F", "hello");
        d.o("6c49 5b57", "汉字");
        d.o("31 32 33 6c49 34 5b57 35 36", "123汉4字56");
    }

}
