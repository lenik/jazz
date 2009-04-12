package net.bodz.bas.text.encodings;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static net.bodz.bas.text.encodings.Encodings.BASE64;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class Base64EncodingTest {

    @Test
    public void testEncode() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return BASE64.encode(input.getBytes("ascii")); //$NON-NLS-1$
            }
        }, //
                EQ("", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello", "aGVsbG8="), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("[\0\n\r\t]", "WwAKDQld"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testDecode() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                byte[] decode = BASE64.decode(input);
                return new String(decode, "ascii"); //$NON-NLS-1$
            }
        }, //
                EQ("", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("aGVsbG8=", "hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("WwAKDQld", "[\0\n\r\t]"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

}
