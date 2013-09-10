package net.bodz.bas.c.string;

import java.io.ByteArrayOutputStream;

import net.bodz.bas.c.java.util.regex.PatternProcessor;
import net.bodz.bas.io.IPrintOut;

public class StringUtil {

    private static PatternProcessor UNESCAPE;
    static {
        UNESCAPE = new PatternProcessor("\\\\(.)") {
            @Override
            protected void matched(String part) {
                switch (part.charAt(1)) {
                case 'a':
                    part = "&";
                    break;
                case 'l':
                    part = "<";
                    break;
                case 'n':
                    part = "\n";
                    break;
                case 'g':
                    part = ">";
                    break;
                case 'p':
                    part = "'";
                    break;
                case 'q':
                    part = "\"";
                    break;
                case 't':
                    part = "\t";
                    break;
                }
                super.matched(part);
            }
        };
    }

    public static String unescape(boolean escaped, String s) {
        if (!escaped)
            return s;
        return unescape(s);
    }

    public static String unescape(String s) {
        return UNESCAPE.process(s);
    }

    public static void helpEscapes(IPrintOut out) {
        out.println("Escapes:");
        out.println("    \\a  &");
        out.println("    \\l  <");
        out.println("    \\n  RET");
        out.println("    \\g  >");
        out.println("    \\p  '");
        out.println("    \\q  \"");
        out.println("    \\t  TAB");
    }

    public static byte[] parseHex(String hex) {
        int len = hex.length();
        ByteArrayOutputStream buf = new ByteArrayOutputStream(len / 2);
        int by = 0;
        int digits = 0;
        for (int i = 0; i < len; i++) {
            char c = hex.charAt(i);
            if (Character.isWhitespace(c)) {
                if (digits != 0) {
                    buf.write(by);
                    digits = 0;
                    by = 0;
                }
                continue;
            }
            by <<= 4;
            if (c >= '0' && c <= '9')
                by |= c - '0';
            else if (c >= 'a' && c <= 'f')
                by |= c - 'a' + 10;
            else if (c >= 'A' && c <= 'F')
                by |= c - 'A' + 10;
            else
                throw new IllegalArgumentException("illegal char '" + c + "' in hex");
            if (++digits == 2) {
                buf.write(by);
                digits = 0;
                by = 0;
            }
        }
        if (digits != 0)
            buf.write(by);
        return buf.toByteArray();
    }

}