package net.bodz.bas.c.java.util.regex;

import org.junit.Assert;
import org.junit.Test;

public class QuotedSplitProcessorTest
        extends Assert {

    static Object[] __(Object... args) {
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
        d.o(__(",", "hello", 0), __("hello"));
        d.o(__(",", "a, b, c", 0), __("a", " b", " c"));
        d.o(__(",", "a,b,c,,", 0), __("a", "b", "c"));
        d.o(__(",", "a,b,c,,", 1), __("a,b,c,,"));
        d.o(__(",", "a,b,c,,", 2), __("a", "b,c,,"));
        d.o(__(",", "a,b,c,,", 3), __("a", "b", "c,,"));
        d.o(__(",", "a,b,c,,", 4), __("a", "b", "c", ","));
        d.o(__(",", "a,b,c,,", 5), __("a", "b", "c", "", ""));
        d.o(__(",", "a,b,c,,", 6), __("a", "b", "c", "", ""));
        d.o(__(",", "a,b,c,,", 100), __("a", "b", "c", "", ""));
        // 11
        d.o(__(",", "a,b,c,,", -1), __("a", "b", "c", "", ""));
        d.o(__(",", "a,'x,y',c", 0), __("a", "'x,y'", "c"));
        d.o(__(",", "a,'x\\'y',c", 0), __("a", "'x\\'y'", "c"));
        d.o(__(",", "a,'b,c", 0), __("a", "'b", "c"));
    }

}
