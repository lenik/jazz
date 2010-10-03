package net.bodz.bas.text.util;

import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;

import org.junit.Test;

public class QuotedSplitProcessorTest
        extends TestCase {

    static Object[] _(Object... args) {
        return args;
    }

    @Test
    public void testSplitStringStringInt() {
        final QuoteFormat qf = QuoteFormat.Q;
        class D {
            void o(Object[] input, Object[] expected) {
                String sep = (String) input[0];
                String s = (String) input[1];
                int limit = (Integer) input[2];
                String[] actual = qf.split(sep, s, limit);
                assertArrayEquals(expected, actual);
            }
        }
        D d = new D(); // 1
        d.o(_(",", "hello", 0), _("hello"));
        d.o(_(",", "a, b, c", 0), _("a", " b", " c"));
        d.o(_(",", "a,b,c,,", 0), _("a", "b", "c"));
        d.o(_(",", "a,b,c,,", 1), _("a,b,c,,"));
        d.o(_(",", "a,b,c,,", 2), _("a", "b,c,,"));
        d.o(_(",", "a,b,c,,", 3), _("a", "b", "c,,"));
        d.o(_(",", "a,b,c,,", 4), _("a", "b", "c", ","));
        d.o(_(",", "a,b,c,,", 5), _("a", "b", "c", "", ""));
        d.o(_(",", "a,b,c,,", 6), _("a", "b", "c", "", ""));
        d.o(_(",", "a,b,c,,", 100), _("a", "b", "c", "", ""));
        // 11
        d.o(_(",", "a,b,c,,", -1), _("a", "b", "c", "", ""));
        d.o(_(",", "a,'x,y',c", 0), _("a", "'x,y'", "c"));
        d.o(_(",", "a,'x\\'y',c", 0), _("a", "'x\\'y'", "c"));
        d.o(_(",", "a,'b,c", 0), _("a", "'b", "c"));
    }

}
