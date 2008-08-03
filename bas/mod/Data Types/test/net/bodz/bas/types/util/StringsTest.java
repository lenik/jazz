package net.bodz.bas.types.util;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static org.junit.Assert.assertEquals;

import java.util.regex.Pattern;

import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;
import net.bodz.bas.test._TestEval;

import org.junit.Test;

public class StringsTest {

    @Test
    public void testEllipseStringIntString() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        assertEquals("..", Strings.ellipse(s, 2, "..."));
        assertEquals("a...", Strings.ellipse(s, 4, "..."));
    }

    @Test
    public void testEllipseStringIntStringStringString() {
        String s = "a/b/c/d/e/f/g/h/i";
        assertEquals("a...", Strings.ellipse(s, 4, "...", "/", "/"));
        assertEquals("a/b/...", Strings.ellipse(s, 7, "...", "/", "/"));
        assertEquals("a/b.../i", Strings.ellipse(s, 8, "...", "/", "/"));
        assertEquals("a/b/c.../i", Strings.ellipse(s, 10, "...", "/", "/"));
    }

    @Test
    public void testCount() throws Exception {
        TestDefs.tests("(char pattern)", new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.count(input, '.');
            }
        }, //
                EQ("empty", 0), //
                EQ("hello.world", 1), //
                EQ("oh... my god! ", 3), //
                EQ(". dot .. everywhere .", 4) //
                );

        TestDefs.tests("(string pattern /1)", new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.count(input, ".");
            }
        }, //
                EQ("empty", 0), //
                EQ("hello.world", 1), //
                EQ("oh... my god! ", 3), //
                EQ(". dot .. everywhere .", 4) //
                );

        TestDefs.tests("(string pattern /2)", new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.count(input, "..");
            }
        }, //
                EQ("empty", 0), //
                EQ("hello.world", 0), //
                EQ("oh... my god! ", 1), //
                EQ(". dot .. everywhere .", 1) //
                );
    }

    @Test
    public void testEscape() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.escape(input);
            }
        }, //
                EQ("", ""), //
                EQ("hello", "hello"), //
                EQ("a\"b\'c", "a\\\"b\\'c"), //
                EQ("a\nb\rc", "a\\nb\\rc"), //
                EQ("a\\b\\\\c\\\\\\d", "a\\\\b\\\\\\\\c\\\\\\\\\\\\d"), //
                END);
    }

    @Test
    public void testFindAll() {
        final Pattern square = Pattern.compile("\\[(.*?)\\]");
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.join("|", Strings.findAll(input, square, 1));
            }
        }, //
                EQ("[hello]", "hello"), //
                EQ("[a] [b] [c]", "a|b|c"), //
                EQ("[a [b [c]]] [[d] [[e] f]]", "a [b [c|[d|[e"), //
                EQ("none", ""), //
                EQ("[][][]", "||"), //
                END);

        final Pattern names = Pattern.compile("(\\w+)\\.(\\w+)");
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.join("|", Strings.findAll(input, names,
                        "<$1-$2>"));
            }
        }, //
                EQ("bill.gates", "<bill-gates>"), //
                EQ("a.b, c.d,...  e.f", "<a-b>|<c-d>|<e-f>"), //
                END);
    }

    @Test
    public void testSplit_Comma() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String input) throws Throwable {
                String[] args = input.split("\\|", 3);
                char[] delim = args[0].toCharArray();
                int limit = Integer.parseInt(args[1]);
                input = args[2];
                if (isBreakpoint())
                    System.out.println(input);
                String[] result = Strings.split((Object) input, delim, limit,
                        Strings.TRIM | Strings.QUOTE);
                return Strings.join("|", result);
            }
        }, //
                EQ(",|0|a,b,c", "a|b|c"), // 1
                EQ(",|0|a,b,c,,,", "a|b|c"), //
                EQ(",|1|a,b,c", "a,b,c"), //
                EQ(",|2|a,b,c", "a|b,c"), //
                EQ(",|3|a,b,c", "a|b|c"), //
                EQ(",|4|a,b,c", "a|b|c"), //
                EQ(",|2| a , b , c", "a|b , c"), //
                EQ(",|2| a \t\n ,\nb,\n c\n\n", "a|b,\n c"), //
                EQ(",|2|,,", "|,"), //
                EQ(",|2|,   ,\n\n", "|,"), //

                EQ(",|0|a,\"b\",c", "a|b|c"), // 11
                EQ(",|0|a,\"b, \\\"j,k,l\\\", bb\",c,,,",
                        "a|b, \"j,k,l\", bb|c"), //
                END);
    }

    @Test
    public void testSplit_Space() {
        TestDefs
                .tests(new _TestEval<String>() {
                    public Object eval(String input) throws Throwable {
                        String[] args = input.split("\\|", 2);
                        int limit = Integer.parseInt(args[0]);
                        input = args[1];
                        if (isBreakpoint())
                            System.out.println(input);
                        String[] result = Strings.split((Object) input, null,
                                limit, Strings.QUOTE);
                        return Strings.join("|", result);
                    }
                }, //
                        EQ("0|a b c", "a|b|c"), // 1
                        EQ("0|a b c   ", "a|b|c"), //
                        EQ("1|a b c", "a b c"), //
                        EQ("2|a b c", "a|b c"), //
                        EQ("3|a b c", "a|b|c"), //
                        EQ("4|a b c", "a|b|c"), //
                        EQ("2| a   b   c", "a|b   c"), //
                        EQ("2| a \t\n  \nb \n c\n\n", "a|b \n c"), //
                        EQ("2|  ", ""), //
                        EQ("2|  x  \n\n", "x"), //

                        EQ("0|a \"b\" c", "a|b|c"), // 11
                        EQ("0|a \"b  \\\"j k l\\\"  bb\" c   ",
                                "a|b  \"j k l\"  bb|c"), //
                        END);
    }

}
