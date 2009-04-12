package net.bodz.bas.text.encodings;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static net.bodz.bas.text.encodings.Encodings.HEX;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class HexEncodingTest {

    @Test
    public void testEncode() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return HEX.encode(input.getBytes("ascii")); //$NON-NLS-1$
            }
        }, //
                EQ("", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello", "68 65 6c 6c 6f"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("[\0\n\r\t]", "5b 00 0a 0d 09 5d"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testDecode() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                byte[] decode = HEX.decode(input);
                return new String(decode, "ascii"); //$NON-NLS-1$
            }
        }, //
                EQ("", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("68 65 6c 6c 6f", "hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("5b 00 0a 0d 09 5d", "[\0\n\r\t]"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

}
