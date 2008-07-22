package net.bodz.bas.text.interp;

import static net.bodz.bas.test.TestDefs.ArrayEQU;
import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static net.bodz.bas.types.util.Arrays2._;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.types.util.Strings;

import org.junit.Test;

public class QuotableTest {

    @Test
    public void testSplitStringStringInt() {
        final Quotable q = new Quotable('\'');
        TestDefs.tests(new _TestEval<Object[]>() {
            public Object eval(Object[] input) throws Throwable {
                if (isBreakpoint())
                    System.out.println(Strings.join(" | ", input));
                String sep = (String) input[0];
                String s = (String) input[1];
                int limit = (Integer) input[2];
                return q.split(sep, s, limit);
            }
        }, // 1
                ArrayEQU(_(",", "hello", 0), _("hello")), //
                ArrayEQU(_(",", "a, b, c", 0), _("a", " b", " c")), //
                ArrayEQU(_(",", "a,b,c,,", 0), _("a", "b", "c")), //
                ArrayEQU(_(",", "a,b,c,,", 1), _("a,b,c,,")), //
                ArrayEQU(_(",", "a,b,c,,", 2), _("a", "b,c,,")), //
                ArrayEQU(_(",", "a,b,c,,", 3), _("a", "b", "c,,")), //
                ArrayEQU(_(",", "a,b,c,,", 4), _("a", "b", "c", ",")), //
                ArrayEQU(_(",", "a,b,c,,", 5), _("a", "b", "c", "", "")), //
                ArrayEQU(_(",", "a,b,c,,", 6), _("a", "b", "c", "", "")), //
                ArrayEQU(_(",", "a,b,c,,", 100), _("a", "b", "c", "", "")), //
                // 11
                ArrayEQU(_(",", "a,b,c,,", -1), _("a", "b", "c", "", "")), //
                ArrayEQU(_(",", "a,b,c,,", -2), _("a", "b", "c", "", "")), //
                ArrayEQU(_(",", "a,'x,y',c", 0), _("a", "'x,y'", "c")), //
                ArrayEQU(_(",", "a,'x\\'y',c", 0), _("a", "'x\\'y'", "c")), //
                ArrayEQU(_(",", "a,'b,c", 0), _("a", "'b", "c")), //
                END);
    }

    @Test
    public void testProcess() {
        final Quotable q = new Quotable('\'') {
            @Override
            protected void part(String text, boolean quoted) {
                if (quoted)
                    print("<" + text + ">");
                else
                    print("[" + text + "]");
            }
        };
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return q.process(input);
            }
        }, //
                EQ("", ""), // 1
                EQ("abc", "[abc]"), //
                EQ("a'b'c", "[a]<b>[c]"), //
                EQ("'abc'", "<abc>"), //
                EQ("''", "<>"), //
                EQ("'", "[']"), //
                EQ("a'b", "[a'b]"), //
                EQ("a'b'c'd", "[a]<b>[c'd]"), //
                EQ(" '  '", "[ ]<  >"), //
                EQ("'''''", "<><>[']"), //

                EQ("a'\\'x'b", "[a]<'x>[b]"), // 11
                EQ("a'\\\\'x'b", "[a]<\\>[x'b]"), // 
                END);
    }

}
