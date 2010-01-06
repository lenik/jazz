package net.bodz.bas.types.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.regex.Pattern;

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
    public void testCount_CharPattern() throws Exception {
        class D {
            void o(String input, int expected) {
                int actual = Strings.count(input, '.');
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("empty", 0); 
        d.o("hello.world", 1); 
        d.o("oh... my god! ", 3); 
        d.o(". dot .. everywhere .", 4); 
    }

    @Test
    public void testCount_StringPattern1() throws Exception {
        class D {
            void o(String input, int expected) {
                int actual = Strings.count(input, "."); 
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("empty", 0); 
        d.o("hello.world", 1); 
        d.o("oh... my god! ", 3); 
        d.o(". dot .. everywhere .", 4);
    }

    @Test
    public void testCount_StringPattern2() {
        class D {
            void o(String input, int expected) {
                int actual = Strings.count(input, ".."); 
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("empty", 0); 
        d.o("hello.world", 0); 
        d.o("oh... my god! ", 1); 
        d.o(". dot .. everywhere .", 1); 
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
        d.o("", "");  
        d.o("hello", "hello");  
        d.o("a\"b\'c", "a\\\"b\\'c");  
        d.o("a\nb\rc", "a\\nb\\rc");  
        d.o("a\\b\\\\c\\\\\\d", "a\\\\b\\\\\\\\c\\\\\\\\\\\\d");  
    }

    @Test
    public void testFindAll() {
        final Pattern square = Pattern.compile("\\[(.*?)\\]"); 
        class D {
            void o(String input, String expected) {
                String actual = Strings.join("|", Strings.findAll(input, square, 1)); 
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
                String actual = Strings.join("|", Strings.findAll(input, names, 
                        "<$1-$2>")); 
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("bill.gates", "<bill-gates>");  
        d.o("a.b, c.d,...  e.f", "<a-b>|<c-d>|<e-f>");  
    }

    @Test
    public void testSplit_Comma() throws IOException {
        class D {
            void o(String expected, String input) throws IOException {
                String[] args = input.split("\\|", 3); 
                char[] delim = args[0].toCharArray();
                int limit = Integer.parseInt(args[1]);
                input = args[2];
                String[] result = Strings.split((Object) input, delim, limit, Strings.TRIM | Strings.QUOTE);
                String actual = Strings.join("|", result); 
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o(",|0|a,b,c", "a|b|c"); // 1  
        d.o(",|0|a,b,c,,,", "a|b|c");  
        d.o(",|1|a,b,c", "a,b,c");  
        d.o(",|2|a,b,c", "a|b,c");  
        d.o(",|3|a,b,c", "a|b|c");  
        d.o(",|4|a,b,c", "a|b|c");  
        d.o(",|2| a , b , c", "a|b , c");  
        d.o(",|2| a \t\n ,\nb,\n c\n\n", "a|b,\n c");  
        d.o(",|2|,,", "|,");  
        d.o(",|2|,   ,\n\n", "|,");  

        d.o(",|0|a,\"b\",c", "a|b|c"); // 11  
        d.o(",|0|a,\"b, \\\"j,k,l\\\", bb\",c,,,", "a|b, \"j,k,l\", bb|c"); 
    }

    @Test
    public void testSplitQuoted() throws IOException {
        class D {
            void o(String expected, String input) throws IOException {
                String[] args = input.split("\\|", 2); 
                int limit = Integer.parseInt(args[0]);
                input = args[1];
                String[] result = Strings.split((Object) input, null, limit, Strings.QUOTE);
                String actual = Strings.join("|", result); 
                assertEquals(expected, actual);
            }
        }
        D d = new D();
        d.o("0|a b c", "a|b|c"); // 1  
        d.o("0|a b c   ", "a|b|c");  
        d.o("1|a b c", "a b c");  
        d.o("2|a b c", "a|b c");  
        d.o("3|a b c", "a|b|c");  
        d.o("4|a b c", "a|b|c");  
        d.o("2| a   b   c", "a|b   c");  
        d.o("2| a \t\n  \nb \n c\n\n", "a|b \n c");  
        d.o("2|  ", "");  
        d.o("2|  x  \n\n", "x");  
        d.o("0|a \"b\" c", "a|b|c"); // 11  
        d.o("0|a \"b  \\\"j k l\\\"  bb\" c   ", "a|b  \"j k l\"  bb|c");
    }

    @Test
    public void testSplitBySize() {
        class D {
            void o(String input, String expected) {
                String[] args = input.split("\\|", 2); 
                int limit = Integer.parseInt(args[0]);
                String s = args[1];
                String[] v = Strings.splitBySize(s, 3, limit);
                String actual = Strings.join("|", v); 
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

    @Test
    public void testHyphen() {
        class D {
            void o(String input, String expected) {
                String actual = Strings.hyphenatize(input);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("hello", "hello");  
        d.o("helloWorld", "hello-world");  
        d.o("HelloWorld", "hello-world");  
        d.o("ABC", "abc");  
        d.o("UUEncode", "uuencode");  
        d.o("FFTestAndRTest", "fftest-and-rtest");  
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
        d.o("hello", "Hello");  
        d.o("hello world", "Hello World");  
        d.o("   abc  def  ", "   Abc  Def  ");  
    }

}
