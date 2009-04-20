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
        String s = "abcdefghijklmnopqrstuvwxyz"; //$NON-NLS-1$
        assertEquals("..", Strings.ellipse(s, 2, "...")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("a...", Strings.ellipse(s, 4, "...")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testEllipseStringIntStringStringString() {
        String s = "a/b/c/d/e/f/g/h/i"; //$NON-NLS-1$
        assertEquals("a...", Strings.ellipse(s, 4, "...", "/", "/")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        assertEquals("a/b/...", Strings.ellipse(s, 7, "...", "/", "/")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        assertEquals("a/b.../i", Strings.ellipse(s, 8, "...", "/", "/")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        assertEquals("a/b/c.../i", Strings.ellipse(s, 10, "...", "/", "/")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }

    @Test
    public void testCount() throws Exception {
        TestDefs.tests("(char pattern)", new TestEval<String>() { //$NON-NLS-1$
                    public Object eval(String input) throws Throwable {
                        return Strings.count(input, '.');
                    }
                }, //
                EQ("empty", 0), // //$NON-NLS-1$
                EQ("hello.world", 1), // //$NON-NLS-1$
                EQ("oh... my god! ", 3), // //$NON-NLS-1$
                EQ(". dot .. everywhere .", 4) // //$NON-NLS-1$
                );

        TestDefs.tests("(string pattern /1)", new TestEval<String>() { //$NON-NLS-1$
                    public Object eval(String input) throws Throwable {
                        return Strings.count(input, "."); //$NON-NLS-1$
                    }
                }, //
                EQ("empty", 0), // //$NON-NLS-1$
                EQ("hello.world", 1), // //$NON-NLS-1$
                EQ("oh... my god! ", 3), // //$NON-NLS-1$
                EQ(". dot .. everywhere .", 4) // //$NON-NLS-1$
                );

        TestDefs.tests("(string pattern /2)", new TestEval<String>() { //$NON-NLS-1$
                    public Object eval(String input) throws Throwable {
                        return Strings.count(input, ".."); //$NON-NLS-1$
                    }
                }, //
                EQ("empty", 0), // //$NON-NLS-1$
                EQ("hello.world", 0), // //$NON-NLS-1$
                EQ("oh... my god! ", 1), // //$NON-NLS-1$
                EQ(". dot .. everywhere .", 1) // //$NON-NLS-1$
                );
    }

    @Test
    public void testEscape() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.escape(input);
            }
        }, //
                EQ("", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello", "hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\"b\'c", "a\\\"b\\'c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\nb\rc", "a\\nb\\rc"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a\\b\\\\c\\\\\\d", "a\\\\b\\\\\\\\c\\\\\\\\\\\\d"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testFindAll() {
        final Pattern square = Pattern.compile("\\[(.*?)\\]"); //$NON-NLS-1$
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.join("|", Strings.findAll(input, square, 1)); //$NON-NLS-1$
            }
        }, //
                EQ("[hello]", "hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("[a] [b] [c]", "a|b|c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("[a [b [c]]] [[d] [[e] f]]", "a [b [c|[d|[e"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("none", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("[][][]", "||"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);

        final Pattern names = Pattern.compile("(\\w+)\\.(\\w+)"); //$NON-NLS-1$
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.join("|", Strings.findAll(input, names, //$NON-NLS-1$
                        "<$1-$2>")); //$NON-NLS-1$
            }
        }, //
                EQ("bill.gates", "<bill-gates>"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("a.b, c.d,...  e.f", "<a-b>|<c-d>|<e-f>"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testSplit_Comma() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String input) throws Throwable {
                String[] args = input.split("\\|", 3); //$NON-NLS-1$
                char[] delim = args[0].toCharArray();
                int limit = Integer.parseInt(args[1]);
                input = args[2];
                if (isBreakpoint())
                    System.out.println(input);
                String[] result = Strings.split((Object) input, delim, limit,
                        Strings.TRIM | Strings.QUOTE);
                return Strings.join("|", result); //$NON-NLS-1$
            }
        }, //
                EQ(",|0|a,b,c", "a|b|c"), // 1 //$NON-NLS-1$ //$NON-NLS-2$
                EQ(",|0|a,b,c,,,", "a|b|c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ(",|1|a,b,c", "a,b,c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ(",|2|a,b,c", "a|b,c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ(",|3|a,b,c", "a|b|c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ(",|4|a,b,c", "a|b|c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ(",|2| a , b , c", "a|b , c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ(",|2| a \t\n ,\nb,\n c\n\n", "a|b,\n c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ(",|2|,,", "|,"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ(",|2|,   ,\n\n", "|,"), // //$NON-NLS-1$ //$NON-NLS-2$

                EQ(",|0|a,\"b\",c", "a|b|c"), // 11 //$NON-NLS-1$ //$NON-NLS-2$
                EQ(",|0|a,\"b, \\\"j,k,l\\\", bb\",c,,,", //$NON-NLS-1$
                        "a|b, \"j,k,l\", bb|c"), // //$NON-NLS-1$
                END);
    }

    @Test
    public void testSplit_Space() {
        TestDefs.tests(new _TestEval<String>() {
            public Object eval(String input) throws Throwable {
                String[] args = input.split("\\|", 2); //$NON-NLS-1$
                int limit = Integer.parseInt(args[0]);
                input = args[1];
                if (isBreakpoint())
                    System.out.println(input);
                String[] result = Strings.split((Object) input, null, limit,
                        Strings.QUOTE);
                return Strings.join("|", result); //$NON-NLS-1$
            }
        }, //
                EQ("0|a b c", "a|b|c"), // 1 //$NON-NLS-1$ //$NON-NLS-2$
                EQ("0|a b c   ", "a|b|c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("1|a b c", "a b c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("2|a b c", "a|b c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("3|a b c", "a|b|c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("4|a b c", "a|b|c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("2| a   b   c", "a|b   c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("2| a \t\n  \nb \n c\n\n", "a|b \n c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("2|  ", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("2|  x  \n\n", "x"), // //$NON-NLS-1$ //$NON-NLS-2$

                EQ("0|a \"b\" c", "a|b|c"), // 11 //$NON-NLS-1$ //$NON-NLS-2$
                EQ("0|a \"b  \\\"j k l\\\"  bb\" c   ", //$NON-NLS-1$
                        "a|b  \"j k l\"  bb|c"), // //$NON-NLS-1$
                END);
    }

    @Test
    public void testSplitBySize() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                String[] args = input.split("\\|", 2); //$NON-NLS-1$
                int limit = Integer.parseInt(args[0]);
                String s = args[1];
                String[] v = Strings.splitBySize(s, 3, limit);
                return Strings.join("|", v); //$NON-NLS-1$
            }
        }, //
                EQ("1|aaabbbccc", "aaabbbccc"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("2|aaabbbccc", "aaa|bbbccc"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("3|aaabbbccc", "aaa|bbb|ccc"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("4|aaabbbccc", "aaa|bbb|ccc"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("3|aaabbbcc", "aaa|bbb|cc"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("3|aaabbbc", "aaa|bbb|c"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("3|aaa", "aaa"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("3|aa", "aa"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("3|a", "a"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("3|", ""), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testHyphen() {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.hyphenatize(input);
            }
        }, //
                EQ("hello", "hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("helloWorld", "hello-world"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("HelloWorld", "hello-world"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("ABC", "abc"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("UUEncode", "uuencode"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("FFTestAndRTest", "fftest-and-rtest"), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

    @Test
    public void testUcfirstWords() throws Exception {
        TestDefs.tests(new TestEval<String>() {
            public Object eval(String input) throws Throwable {
                return Strings.ucfirstWords(input);
            }
        }, //
                EQ("hello", "Hello"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("hello world", "Hello World"), // //$NON-NLS-1$ //$NON-NLS-2$
                EQ("   abc  def  ", "   Abc  Def  "), // //$NON-NLS-1$ //$NON-NLS-2$
                END);
    }

}
