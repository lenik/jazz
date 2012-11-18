package net.bodz.bas.c.string;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class StringArrayTest
        extends Assert {

    @Test
    public void testJoinReversed() {
        class D {
            void o(String expected, Object... args) {
                List<?> list = Arrays.asList(args);
                String s = StringArray.joinReversed(",", list);
                System.out.println(s);
            }
        }
        D d = new D();
        d.o("", Arrays.asList());
        d.o("1", Arrays.asList(1));
        d.o("3,2", Arrays.asList(1, 2, 3));
    }

    @Test
    public void testSplitRawByChar() {
        class D {
            void o(String string, char sep, int limit, String... expected) {
                String[] actual = StringArray.splitRaw(string, sep, limit);
                assertArrayEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("a:b:c", ':', 0);
        d.o("a:b:c", ':', 1, "a:b:c");
        d.o("a:b:c", ':', 2, "a", "b:c");
        d.o("a:b:c", ':', 3, "a", "b", "c");
        d.o("a:b:c", ':', 4, "a", "b", "c");
        d.o("a:b:c", ':', -1, "a", "b", "c");

        d.o("a:b:", ':', 2, "a", "b:");
        d.o("a:b:", ':', -1, "a", "b", "");
    }

    @Test
    public void testSplitBySize() {
        class D {
            void o(String input, String expected) {
                String[] args = input.split("\\|", 2);
                int limit = Integer.parseInt(args[0]);
                String s = args[1];
                String[] v = StringArray.splitBySize(s, 3, limit);
                String actual = StringArray.join("|", v);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("1|aaabbbccc", "aaabbbccc");
        d.o("2|aaabbbccc", "aaa|bbbccc");
        d.o("3|aaabbbccc", "aaa|bbb|ccc");
        d.o("4|aaabbbccc", "aaa|bbb|ccc");
        d.o("3|aaabbbcc", "aaa|bbb|cc");
        d.o("3|aaabbbc", "aaa|bbb|c");
        d.o("3|aaa", "aaa");
        d.o("3|aa", "aa");
        d.o("3|a", "a");
        d.o("3|", "");
    }

}
