package net.bodz.bas.text.util;

import static net.bodz.bas.test.TestDefs.ArrayEQU;
import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static net.bodz.bas.types.util.Arrays2._;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.text.util.Quotable;
import net.bodz.bas.types.util.Strings;

import org.junit.Test;

public class QuotableTest {

    @Test
    public void testSplitStringStringInt() {
        final Quotable q = new Quotable('\'');
        TestDefs.tests(new _TestEval<Object[]>() {
            public Object eval(Object[] input) throws Throwable {
                if (isBreakpoint())
                    System.out.println(Strings.join(" | ", input)); //$NON-NLS-1$
                String sep = (String) input[0];
                String s = (String) input[1];
                int limit = (Integer) input[2];
                return q.split(sep, s, limit);
            }
        }, // 1
                ArrayEQU(_(",", "hello", 0), _("hello")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ArrayEQU(_(",", "a, b, c", 0), _("a", " b", " c")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                ArrayEQU(_(",", "a,b,c,,", 0), _("a", "b", "c")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                ArrayEQU(_(",", "a,b,c,,", 1), _("a,b,c,,")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ArrayEQU(_(",", "a,b,c,,", 2), _("a", "b,c,,")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                ArrayEQU(_(",", "a,b,c,,", 3), _("a", "b", "c,,")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                ArrayEQU(_(",", "a,b,c,,", 4), _("a", "b", "c", ",")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
                ArrayEQU(_(",", "a,b,c,,", 5), _("a", "b", "c", "", "")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                ArrayEQU(_(",", "a,b,c,,", 6), _("a", "b", "c", "", "")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                ArrayEQU(_(",", "a,b,c,,", 100), _("a", "b", "c", "", "")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                // 11
                ArrayEQU(_(",", "a,b,c,,", -1), _("a", "b", "c", "", "")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                ArrayEQU(_(",", "a,b,c,,", -2), _("a", "b", "c", "", "")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
                ArrayEQU(_(",", "a,'x,y',c", 0), _("a", "'x,y'", "c")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                ArrayEQU(_(",", "a,'x\\'y',c", 0), _("a", "'x\\'y'", "c")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                ArrayEQU(_(",", "a,'b,c", 0), _("a", "'b", "c")), // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                END);
    }

    @Test
    public void testProcess() {
        final Quotable q = new Quotable('\'') {
            @Override
            protected void part(String text, boolean quoted) {
                if (quoted)
                    print("<" + text + ">"); //$NON-NLS-1$ //$NON-NLS-2$
                else
                    print("[" + text + "]"); //$NON-NLS-1$ //$NON-NLS-2$
            }
        };
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return q.process(input);
            }
        }, //
                EQ("", ""), // 1 //$NON-NLS-1$ //$NON-NLS-2$
                EQ("abc", "[abc]"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a'b'c", "[a]<b>[c]"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("'abc'", "<abc>"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("''", "<>"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("'", "[']"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a'b", "[a'b]"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a'b'c'd", "[a]<b>[c'd]"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ(" '  '", "[ ]<  >"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("'''''", "<><>[']"), // //$NON-NLS-1$ //$NON-NLS-2$

                EQ("a'\\'x'b", "[a]<'x>[b]"), // 11 //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a'\\\\'x'b", "[a]<\\>[x'b]"), //  //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

}
