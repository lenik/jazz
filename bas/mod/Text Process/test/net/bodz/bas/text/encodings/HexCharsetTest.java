package net.bodz.bas.text.encodings;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;

import org.junit.Test;

public class HexCharsetTest {

    @Test
    public void testEncode() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                byte[] bytes = input.getBytes("hex");
                return new String(bytes, "ascii");
            }
        }, //
                EQ("", ""), //
                EQ("hello", "68 65 6c 6c 6f"), //
                EQ("汉字", "6c49 5b57"), //
                EQ("123汉4字56", "31 32 33 6c49 34 5b57 35 36"), //
                END);
    }

    @Test
    public void testDecode() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                byte[] bytes = input.getBytes("ascii");
                return new String(bytes, "hex");
            }
        }, //
                EQ("", ""), //
                EQ("68 65 6C 6c 6F", "hello"), //
                EQ("6c49 5b57", "汉字"), //
                EQ("31 32 33 6c49 34 5b57 35 36", "123汉4字56"), //
                END);
    }

}
