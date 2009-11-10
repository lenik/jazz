package net.bodz.bas.text.encodings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HexCharsetTest {

    @Test
    public void testEncode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                byte[] bytes = input.getBytes("hex"); //$NON-NLS-1$
                String actual = new String(bytes, "ascii"); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("", ""); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello", "68 65 6c 6c 6f"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("汉字", "6c49 5b57"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("123汉4字56", "31 32 33 6c49 34 5b57 35 36"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testDecode() throws Exception {
        class D {
            void o(String input, String expected) throws Exception {
                byte[] bytes = input.getBytes("ascii"); //$NON-NLS-1$
                String actual = new String(bytes, "hex"); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("68 65 6C 6c 6F", "hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("6c49 5b57", "汉字"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("31 32 33 6c49 34 5b57 35 36", "123汉4字56"); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
