package net.bodz.bas.text.util;

import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

public class StringArrayTest
        extends TestCase {

    @Test
    public void testSplit_Comma()
            throws IOException {
        class D {
            void o(String expected, String input)
                    throws IOException {
                String[] args = input.split("\\|", 3);
                char[] delim = args[0].toCharArray();
                int limit = Integer.parseInt(args[1]);
                input = args[2];
                String[] result = StringArray.split(input, delim, limit, StringArray.TRIM | StringArray.QUOTE);
                String actual = StringArray.join("|", result);
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
    public void testSplitQuoted()
            throws IOException {
        class D {
            void o(String expected, String input)
                    throws IOException {
                String[] args = input.split("\\|", 2);
                int limit = Integer.parseInt(args[0]);
                input = args[1];
                String[] result = StringArray.split(input, null, limit, StringArray.QUOTE);
                String actual = StringArray.join("|", result);
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
