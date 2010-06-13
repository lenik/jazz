package net.bodz.bas.text.util;

import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import org.junit.Test;

public class StringGrepTest {

    @Test
    public void testFindAll() {
        final Pattern square = Pattern.compile("\\[(.*?)\\]");
        class D {
            void o(String input, String expected) {
                String actual = StringArray.join("|", StringGrep.findAll(input, square, 1));
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("[hello]", "hello");
        d.o("[a] [b] [c]", "a|b|c");
        d.o("[a [b [c]]] [[d] [[e] f]]", "a [b [c|[d|[e");
        d.o("none", "");
        d.o("[][][]", "||");
    }

    @Test
    public void testFindAll_2() {
        final Pattern names = Pattern.compile("(\\w+)\\.(\\w+)");
        class D {
            void o(String input, String expected) {
                String actual = StringArray.join("|", StringGrep.findAll(input, names, "<$1-$2>"));
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("bill.gates", "<bill-gates>");
        d.o("a.b, c.d,...  e.f", "<a-b>|<c-d>|<e-f>");
    }

}
