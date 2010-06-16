package net.bodz.bas.text.util;

import java.nio.CharBuffer;

import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.IPrintCharOut;

public class Unescape {

    private final char[] ESC;

    public Unescape(String escapeChar) {
        this.ESC = escapeChar.toCharArray();
    }

    private boolean getESC(CharBuffer in, int start) {
        if (ESC.length > in.remaining())
            return false;
        for (int i = 0; i < ESC.length; i++) {
            if (in.charAt(i) != ESC[i])
                return false;
        }
        in.position(in.position() + ESC.length);
        return true;
    }

    public void process(CharBuffer in, IPrintCharOut out) {
        int limit = in.limit();
        StringBuffer t = new StringBuffer();
        int i = in.position();
        while (i < limit) {
            if (getESC(in, i)) {
                if (t.length() != 0) {
                    String s = unmatched(t.toString());
                    out.print(s);
                    t.setLength(0);
                }
                String decoded = decode(in);
                out.print(decoded);
                i = in.position();
            } else {
                char c = in.get();
                t.append(c);
                i++;
            }
        }
        if (t.length() != 0) {
            String s = unmatched(t.toString());
            out.print(s);
        }
    }

    public String process(String s) {
        BCharOut buf = new BCharOut();
        process(CharBuffer.wrap(s), buf);
        return buf.toString();
    }

    protected String decode(CharBuffer in) {
        char c = in.get();
        int v;
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
            v = BufParsers.getInt(in, 16, 255);
            if (v != -1)
                c = (char) v;
            break;
        case 'u':
        case 'U':
            // Character.MAX_CODE_POINT;
            v = BufParsers.getInt(in, 16, Character.MAX_VALUE);
            if (v != -1)
                c = (char) v;
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
            in.position(in.position() - 1); // unget
            v = BufParsers.getInt(in, 8, 255);
            if (v != -1)
                c = (char) v;
            break;
        default:
        }
        return String.valueOf(c);
    }

    protected String unmatched(String s) {
        return s;
    }

    public static String unescape(String s, String escapeChar) {
        Unescape unescape = new Unescape(escapeChar);
        return unescape.process(s);
    }

    private static final Unescape cUnescape;
    static {
        cUnescape = new Unescape("\\"); 
    }

    public static String unescape(String s) {
        return cUnescape.process(s);
    }

}
