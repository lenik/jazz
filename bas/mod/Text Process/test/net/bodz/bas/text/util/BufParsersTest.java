package net.bodz.bas.text.util;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;

import java.nio.CharBuffer;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test._TestEval;

import org.junit.Test;

public class BufParsersTest {

    @Test
    public void testGetIntCharBuffer() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String input) throws Throwable {
                CharBuffer buf = CharBuffer.wrap(input);
                if (isBreakpoint())
                    System.err.println(input);
                int val = BufParsers.getInt(buf, 10, 1023);
                return val + "|" + buf;
            }
        }, //
                EQ("0", "0|"), // 1
                EQ("1xyz", "1|xyz"), // 
                EQ("xyz", "-1|xyz"), // 
                EQ("", "-1|"), //
                EQ("1", "1|"), //
                EQ("00000001", "1|"), // 
                EQ("9", "9|"), //
                EQ("99", "99|"), //
                EQ("999", "999|"), //
                EQ("9999", "999|9"), // 

                EQ("1000", "1000|"), // 11
                EQ("1020", "1020|"), //
                EQ("1023", "1023|"), //
                EQ("1024", "102|4"), //
                EQ("1033", "103|3"), //
                EQ("01023", "1023|"), //
                END);
    }

    @Test
    public void testGetLongCharBuffer() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String input) throws Throwable {
                CharBuffer buf = CharBuffer.wrap(input);
                if (isBreakpoint())
                    System.err.println(input);
                long val = BufParsers.getLong(buf, 10, 111222333444L);
                return val + "|" + buf;
            }
        }, //
                EQ("0", "0|"), // 1
                EQ("1xyz", "1|xyz"), // 
                EQ("xyz", "-1|xyz"), // 
                EQ("", "-1|"), //
                EQ("1", "1|"), //
                EQ("00000001", "1|"), //
                EQ("99999999999", "99999999999|"), //
                EQ("999999999999", "99999999999|9"), // 

                EQ("111222333443", "111222333443|"), // 11
                EQ("111222333444", "111222333444|"), // 11
                EQ("111222333445", "11122233344|5"), // 11
                EQ("000111222333444", "111222333444|"), // 11
                END);
    }

}
