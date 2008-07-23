package net.bodz.bas.text.encodings;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class Base64EncodingTest {

    Encoding base64 = new Base64Encoding();

    @Test
    public void testEncode() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return base64.encode(input.getBytes("ascii"));
            }
        }, //
                EQ("", ""), //
                EQ("hello", "aGVsbG8="), //
                EQ("[\0\n\r\t]", "WwAKDQld"), //
                END);
    }

    @Test
    public void testDecode() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                byte[] decode = base64.decode(input);
                return new String(decode, "ascii");
            }
        }, //
                EQ("", ""), //
                EQ("aGVsbG8=", "hello"), //
                EQ("WwAKDQld", "[\0\n\r\t]"), //
                END);
    }

}
