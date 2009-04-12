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
                return Unescape.unescape(input);
            }
        }, //
                EQ("", ""), // 1 //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello", "hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("he\\\\llo", "he\\llo"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("he\\\"llo", "he\"llo"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("he\\\'llo", "he\'llo"), // //$NON-NLS-1$ //$NON-NLS-2$

                EQ("\\thello", "\thello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("h\\tello", "h\tello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hell\\to", "hell\to"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello\\t", "hello\t"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("\\t", "\t"), // //$NON-NLS-1$ //$NON-NLS-2$

                EQ("\\x9hello", "\thello"), // 11 //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hell\\x9o", "hell\to"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello\\x9", "hello\t"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("\\x9", "\t"), // //$NON-NLS-1$ //$NON-NLS-2$

                EQ("\\11hello", "\thello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("h\\11ello", "h\tello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hell\\11o", "hell\to"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello\\11", "hello\t"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("\\11", "\t"), // //$NON-NLS-1$ //$NON-NLS-2$

                EQ("\\x41hello", "Ahello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("h\\x41ello", "hAello"), // 21 //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hell\\x41o", "hellAo"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello\\x41", "helloA"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("\\x41", "A"), // //$NON-NLS-1$ //$NON-NLS-2$

                END);
    }

    public static void main(String[] args) {
        new UnescapeTest().testInterpString();
    }

}
