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
                return HEX.encode(input.getBytes("ascii"));
            }
        }, //
                EQ("", ""), //
                EQ("hello", "68 65 6c 6c 6f"), //
                EQ("[\0\n\r\t]", "5b 00 0a 0d 09 5d"), //
                END);
    }

    @Test
    public void testDecode() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                byte[] decode = HEX.decode(input);
                return new String(decode, "ascii");
            }
        }, //
                EQ("", ""), //
                EQ("68 65 6c 6c 6f", "hello"), //
                EQ("5b 00 0a 0d 09 5d", "[\0\n\r\t]"), //
                END);
    }

}
