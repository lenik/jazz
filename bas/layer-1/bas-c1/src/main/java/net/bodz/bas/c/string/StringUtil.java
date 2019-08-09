package net.bodz.bas.c.string;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.c.java.util.regex.IPartProcessor;
import net.bodz.bas.c.java.util.regex.TextPrepByParts;
import net.bodz.bas.io.IPrintOut;

public class StringUtil {

    private static TextPrepByParts UNESCAPE;
    static {
        Pattern pattern = Pattern.compile("\\\\(.)");
        UNESCAPE = TextPrepByParts.match(pattern, new IPartProcessor() {
            @Override
            public String process(String part, Matcher matcher) {
                switch (part.charAt(1)) {
                case 'a':
                    return "&";
                case 'l':
                    return "<";
                case 'n':
                    return "\n";
                case 'g':
                    return ">";
                case 'p':
                    return "'";
                case 'q':
                    return "\"";
                case 't':
                    return "\t";
                default:
                    return part;
                }
            }
        });
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

    public static String enc1(String s) {
        if (s == null || s.isEmpty())
            return s;
        String s1 = s.substring(0, 1);
        String s2 = s.substring(1);
        Calendar calendar = Calendar.getInstance();
        int mo = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        StringBuffer sb = new StringBuffer();
        sb.append(mo);
        sb.append(day);
        sb.append(s1);
        sb.reverse();
        sb.append(s2);
        return sb.toString();
    }

}