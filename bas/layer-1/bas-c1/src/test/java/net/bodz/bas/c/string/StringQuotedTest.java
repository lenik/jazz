package net.bodz.bas.c.string;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class StringQuotedTest
        extends Assert {

    @Test
    public void testSplit_Comma()
            throws IOException {
        class D {
            void o(String input, String expected)
                    throws IOException {
                String[] args = input.split("\\|", 3);
                char[] delim = args[0].toCharArray();
                int limit = Integer.parseInt(args[1]);
                input = args[2];
                String[] result = StringQuoted.split(input, delim, limit, //
                        StringQuoted.TRIM | StringQuoted.QUOTE | StringQuoted.DEQUOTE);
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
            void o(String input, String expected)
                    throws IOException {
                String[] args = input.split("\\|", 2);
                int limit = Integer.parseInt(args[0]);
                input = args[1];
                String[] result = StringQuoted.split(input, null, limit, //
                        StringQuoted.QUOTE | StringQuoted.DEQUOTE);
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

}
