package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.regex.Pattern;

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
    public void testCount_CharPattern() throws Exception {
        class D {
            void o(String input, int expected) {
                int actual = Strings.count(input, '.');
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("empty", 0); //$NON-NLS-1$
        d.o("hello.world", 1); //$NON-NLS-1$
        d.o("oh... my god! ", 3); //$NON-NLS-1$
        d.o(". dot .. everywhere .", 4); //$NON-NLS-1$
    }

    @Test
    public void testCount_StringPattern1() throws Exception {
        class D {
            void o(String input, int expected) {
                int actual = Strings.count(input, "."); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("empty", 0); //$NON-NLS-1$
        d.o("hello.world", 1); //$NON-NLS-1$
        d.o("oh... my god! ", 3); //$NON-NLS-1$
        d.o(". dot .. everywhere .", 4);//$NON-NLS-1$
    }

    @Test
    public void testCount_StringPattern2() {
        class D {
            void o(String input, int expected) {
                int actual = Strings.count(input, ".."); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("empty", 0); //$NON-NLS-1$
        d.o("hello.world", 0); //$NON-NLS-1$
        d.o("oh... my god! ", 1); //$NON-NLS-1$
        d.o(". dot .. everywhere .", 1); //$NON-NLS-1$
    }

    @Test
    public void testEscape() {
        class D {
            void o(String input, String expected) {
                String actual = Strings.escape(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("", ""); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello", "hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\"b\'c", "a\\\"b\\'c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\nb\rc", "a\\nb\\rc"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a\\b\\\\c\\\\\\d", "a\\\\b\\\\\\\\c\\\\\\\\\\\\d"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testFindAll() {
        final Pattern square = Pattern.compile("\\[(.*?)\\]"); //$NON-NLS-1$
        class D {
            void o(String input, String expected) {
                String actual = Strings.join("|", Strings.findAll(input, square, 1)); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("[hello]", "hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("[a] [b] [c]", "a|b|c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("[a [b [c]]] [[d] [[e] f]]", "a [b [c|[d|[e"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("none", ""); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("[][][]", "||"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testFindAll_2() {
        final Pattern names = Pattern.compile("(\\w+)\\.(\\w+)"); //$NON-NLS-1$
        class D {
            void o(String input, String expected) {
                String actual = Strings.join("|", Strings.findAll(input, names, //$NON-NLS-1$
                        "<$1-$2>")); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("bill.gates", "<bill-gates>"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("a.b, c.d,...  e.f", "<a-b>|<c-d>|<e-f>"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testSplit_Comma() throws IOException {
        class D {
            void o(String expected, String input) throws IOException {
                String[] args = input.split("\\|", 3); //$NON-NLS-1$
                char[] delim = args[0].toCharArray();
                int limit = Integer.parseInt(args[1]);
                input = args[2];
                String[] result = Strings.split((Object) input, delim, limit, Strings.TRIM
                        | Strings.QUOTE);
                String actual = Strings.join("|", result); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o(",|0|a,b,c", "a|b|c"); // 1 //$NON-NLS-1$ //$NON-NLS-2$
        d.o(",|0|a,b,c,,,", "a|b|c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o(",|1|a,b,c", "a,b,c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o(",|2|a,b,c", "a|b,c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o(",|3|a,b,c", "a|b|c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o(",|4|a,b,c", "a|b|c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o(",|2| a , b , c", "a|b , c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o(",|2| a \t\n ,\nb,\n c\n\n", "a|b,\n c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o(",|2|,,", "|,"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o(",|2|,   ,\n\n", "|,"); //$NON-NLS-1$ //$NON-NLS-2$

        d.o(",|0|a,\"b\",c", "a|b|c"); // 11 //$NON-NLS-1$ //$NON-NLS-2$
        d.o(",|0|a,\"b, \\\"j,k,l\\\", bb\",c,,,", "a|b, \"j,k,l\", bb|c"); //$NON-NLS-1$
    }

    @Test
    public void testSplitQuoted() throws IOException {
        class D {
            void o(String expected, String input) throws IOException {
                String[] args = input.split("\\|", 2); //$NON-NLS-1$
                int limit = Integer.parseInt(args[0]);
                input = args[1];
                String[] result = Strings.split((Object) input, null, limit, Strings.QUOTE);
                String actual = Strings.join("|", result); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("0|a b c", "a|b|c"); // 1 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("0|a b c   ", "a|b|c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("1|a b c", "a b c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("2|a b c", "a|b c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("3|a b c", "a|b|c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("4|a b c", "a|b|c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("2| a   b   c", "a|b   c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("2| a \t\n  \nb \n c\n\n", "a|b \n c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("2|  ", ""); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("2|  x  \n\n", "x"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("0|a \"b\" c", "a|b|c"); // 11 //$NON-NLS-1$ //$NON-NLS-2$
        d.o("0|a \"b  \\\"j k l\\\"  bb\" c   ", "a|b  \"j k l\"  bb|c");//$NON-NLS-1$
    }

    @Test
    public void testSplitBySize() {
        class D {
            void o(String input, String expected) {
                String[] args = input.split("\\|", 2); //$NON-NLS-1$
                int limit = Integer.parseInt(args[0]);
                String s = args[1];
                String[] v = Strings.splitBySize(s, 3, limit);
                String actual = Strings.join("|", v); //$NON-NLS-1$
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("1|aaabbbccc", "aaabbbccc"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("2|aaabbbccc", "aaa|bbbccc"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("3|aaabbbccc", "aaa|bbb|ccc"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("4|aaabbbccc", "aaa|bbb|ccc"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("3|aaabbbcc", "aaa|bbb|cc"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("3|aaabbbc", "aaa|bbb|c"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("3|aaa", "aaa"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("3|aa", "aa"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("3|a", "a"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("3|", ""); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testHyphen() {
        class D {
            void o(String input, String expected) {
                String actual = Strings.hyphenatize(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("hello", "hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("helloWorld", "hello-world"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("HelloWorld", "hello-world"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("ABC", "abc"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("UUEncode", "uuencode"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("FFTestAndRTest", "fftest-and-rtest"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Test
    public void testUcfirstWords() throws Exception {
        class D {
            void o(String input, String expected) {
                String actual = Strings.ucfirstWords(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("hello", "Hello"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("hello world", "Hello World"); //$NON-NLS-1$ //$NON-NLS-2$
        d.o("   abc  def  ", "   Abc  Def  "); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
