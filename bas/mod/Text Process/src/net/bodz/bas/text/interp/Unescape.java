package net.bodz.bas.text.interp;

import java.util.regex.Pattern;

import net.bodz.bas.types.util.PatternProcessor;

public class Unescape extends PatternProcessor {

    private final int escapeCharLen;
    private int       eatAhead;

    public Unescape(String escapeChar) {
        super(Pattern.compile(Pattern.quote(escapeChar) + "."));
        escapeCharLen = escapeChar.length();
    }

    @Override
    protected void matched(int start, int end) {
        start += escapeCharLen;
        int eatenLen = end - start;
        eatAhead = recognizeEscapeCode(start) - eatenLen;
    }

    protected int recognizeEscapeCode(int start) {
        char c = source.charAt(start);
        int eat = 1;
        switch (c) {
        case 'b':
            c = '\b';
            break;
        case 'f':
            c = '\f';
            break;
        case 'n':
            c = '\n';
            break;
        case 'r':
            c = '\r';
            break;
        case 't':
            c = '\t';
            break;
        case 'x':
        case 'X':
            eat = lookHex(start + 1, 2);
            if (eat != 0)
                c = (char) parseInt(16, start + 1, eat);
            eat++;
            break;
        case 'u':
        case 'U':
            eat = lookHex(start + 1, 4);
            if (eat != 0)
                c = (char) parseInt(16, start + 1, eat);
            eat++;
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
            eat = lookOct(start, 3);
            if (eat != 0)
                c = (char) parseInt(8, start, eat);
            break;
        default:
        }
        buffer.append(c);
        return eat;
    }

    protected int lookHex(int off, int len) {
        len = Math.min(len, source.length() - off);
        for (int i = 0; i < len; i++) {
            char c = source.charAt(off + i);
            if (c >= '0' && c <= '9')
                c -= '0';
            else if (c >= 'a' && c <= 'f')
                c -= 'a' - 10;
            else if (c >= 'A' && c <= 'F')
                c -= 'A' - 10;
            else
                return i;
        }
        return len;
    }

    protected int lookOct(int off, int len) {
        len = Math.min(len, source.length() - off);
        for (int i = 0; i < len; i++) {
            char c = source.charAt(off + i);
            if (c >= '0' && c <= '7')
                c -= '0';
            else
                return i;
        }
        return len;
    }

    protected int parseInt(int radix, int off, int len) {
        String s = source.substring(off, off + len);
        return Integer.parseInt(s, radix);
    }

    @Override
    protected void unmatched(int start, int end) {
        start += eatAhead;
        eatAhead = 0;
        super.unmatched(start, end);
    }

    public static String interp(String s, String escapeChar) {
        Unescape unescape = new Unescape(escapeChar);
        return unescape.process(s);
    }

    private static final Unescape cUnescape;
    static {
        cUnescape = new Unescape("\\");
    }

    public static String interp(String s) {
        return cUnescape.process(s);
    }

}
