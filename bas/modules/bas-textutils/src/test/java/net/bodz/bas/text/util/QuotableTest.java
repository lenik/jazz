package net.bodz.bas.text.util;

import static net.bodz.bas.commons.collection.util.Arrays2._;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QuotableTest {

    @Test
    public void testSplitStringStringInt() {
        final Quotable q = new Quotable('\'');
        class D {
            void o(Object[] input, Object[] expected) {
                String sep = (String) input[0];
                String s = (String) input[1];
                int limit = (Integer) input[2];
                String[] actual = q.split(sep, s, limit);
                assertArrayEquals(expected, actual);
            }
        }
        D d = new D(); // 1
        d.o(_(",", "hello", 0), _("hello")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        d.o(_(",", "a, b, c", 0), _("a", " b", " c")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        d.o(_(",", "a,b,c,,", 0), _("a", "b", "c")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        d.o(_(",", "a,b,c,,", 1), _("a,b,c,,")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        d.o(_(",", "a,b,c,,", 2), _("a", "b,c,,")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        d.o(_(",", "a,b,c,,", 3), _("a", "b", "c,,")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        d.o(_(",", "a,b,c,,", 4), _("a", "b", "c", ",")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        d.o(_(",", "a,b,c,,", 5), _("a", "b", "c", "", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
        d.o(_(",", "a,b,c,,", 6), _("a", "b", "c", "", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
        d.o(_(",", "a,b,c,,", 100), _("a", "b", "c", "", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
        // 11
        d.o(_(",", "a,b,c,,", -1), _("a", "b", "c", "", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
        d.o(_(",", "a,b,c,,", -2), _("a", "b", "c", "", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
        d.o(_(",", "a,'x,y',c", 0), _("a", "'x,y'", "c")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        d.o(_(",", "a,'x\\'y',c", 0), _("a", "'x\\'y'", "c")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        d.o(_(",", "a,'b,c", 0), _("a", "'b", "c")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
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
        class D {
            void o(String input, String expected) {
                String actual = q.process(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); // 1 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("abc", "[abc]"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a'b'c", "[a]<b>[c]"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("'abc'", "<abc>"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("''", "<>"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("'", "[']"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a'b", "[a'b]"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a'b'c'd", "[a]<b>[c'd]"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o(" '  '", "[ ]<  >"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("'''''", "<><>[']"); //$NON-NLS-1$ //$NON-NLS-2$

        d.o("a'\\'x'b", "[a]<'x>[b]"); // 11 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a'\\\\'x'b", "[a]<\\>[x'b]"); //  //$NON-NLS-1$ //$NON-NLS-2$
    }

}
