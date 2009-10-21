package net.bodz.bas.text.encodings;

import static net.bodz.bas.text.encodings.Encodings.BASE64;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Base64EncodingTest {

    @Test
    public void testEncode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                String actual = BASE64.encode(input.getBytes("ascii")); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello", "aGVsbG8="); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("[\0\n\r\t]", "WwAKDQld"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testDecode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                byte[] decode = BASE64.decode(input);
                String actual = new String(decode, "ascii"); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("aGVsbG8=", "hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("WwAKDQld", "[\0\n\r\t]"); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
