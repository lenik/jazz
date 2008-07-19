package net.bodz.bas.text.interp;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test._TestEval;

import org.junit.Test;

public class UnescapeTest {

    @Test
    public void testInterpString() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String input) throws Throwable {
                if (isBreakpoint())
                    System.out.println(input);
                return Unescape.interp(input);
            }
        }, //
                EQ("", ""), //
                EQ("hello", "hello"), //
                EQ("he\\\\llo", "he\\llo"), //
                EQ("he\\\"llo", "he\"llo"), //
                EQ("he\\\'llo", "he\'llo"), //

                EQ("\\thello", "\thello"), //
                EQ("h\\tello", "h\tello"), //
                EQ("hell\\to", "hell\to"), //
                EQ("hello\\t", "hello\t"), //
                EQ("\\t", "\t"), //

                EQ("\\x9hello", "\thello"), //
                EQ("hell\\x9o", "hell\to"), //
                EQ("hello\\x9", "hello\t"), //
                EQ("\\x9", "\t"), //

                EQ("\\11hello", "\thello"), //
                EQ("h\\11ello", "h\tello"), //
                EQ("hell\\11o", "hell\to"), //
                EQ("hello\\11", "hello\t"), //
                EQ("\\11", "\t"), //

                EQ("\\x41hello", "Ahello"), //
                EQ("h\\x41ello", "hAello"), //
                EQ("hell\\x41o", "hellAo"), //
                EQ("hello\\x41", "helloA"), //
                EQ("\\x41", "A"), //

                END);
    }

}
