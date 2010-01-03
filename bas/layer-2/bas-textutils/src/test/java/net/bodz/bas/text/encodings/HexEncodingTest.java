package net.bodz.bas.text.encodings;

import static net.bodz.bas.text.encodings.Encodings.HEX;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HexEncodingTest {

    @Test
    public void testEncode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                String actual = HEX.encode(input.getBytes("ascii")); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); // //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello", "68 65 6c 6c 6f"); // //$NON-NLS-1$ //$NON-NLS-2$
        d.o("[\0\n\r\t]", "5b 00 0a 0d 09 5d"); // //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testDecode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                byte[] decode = HEX.decode(input);
                String actual = new String(decode, "ascii"); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); // //$NON-NLS-1$ //$NON-NLS-2$
        d.o("68 65 6c 6c 6f", "hello"); // //$NON-NLS-1$ //$NON-NLS-2$
        d.o("5b 00 0a 0d 09 5d", "[\0\n\r\t]"); // //$NON-NLS-1$ //$NON-NLS-2$
    }

}
