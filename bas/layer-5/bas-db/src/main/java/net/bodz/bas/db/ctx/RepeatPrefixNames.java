package net.bodz.bas.db.ctx;

import java.util.StringTokenizer;

import net.bodz.bas.t.tuple.Split;

public class RepeatPrefixNames {

    public static final char DEFAULT_DELIM = '.';
    public static final char DEFAULT_SEPARATOR = '/';

    private final char delim;
    private final char sep;

    public RepeatPrefixNames() {
        this(DEFAULT_DELIM, DEFAULT_SEPARATOR);
    }

    public RepeatPrefixNames(char delim) {
        this(delim, DEFAULT_SEPARATOR);
    }

    public RepeatPrefixNames(char delim, char sep) {
        this.delim = delim;
        this.sep = sep;
    }

    public String compact(String s) {
        if (s == null)
            return null;

        while (true) {
            Split split = Split.shift(s, sep);
            if (split.b == null)
                return s;

            String head = split.a;
            String tail = split.b;
            if (!tail.startsWith(head))
                return s;

            int headLen = head.length();
            if (tail.length() == headLen)
                return head;

            char la = tail.charAt(headLen);
            if (la == delim || la == sep) {
                s = tail;
                continue;
            }
            return s;
        }
    }

    public String expand(String s) {
        if (s == null)
            return null;

        StringBuilder sb = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(s, "/");
        int count = 0;
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (count++ != 0)
                sb.append(sep);
            _repeat(sb, token);
        }
        return sb.toString();
    }

    public String repeat(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        _repeat(sb, s);
        return sb.toString();

    }

    void _repeat(StringBuilder sb, String s) {
        int length = s.length();
        int count = 0;
        int start = 0;
        while (start <= length) {
            int pos = s.indexOf(delim, start);
            int end = pos == -1 ? length : pos;

            if (count++ != 0)
                sb.append(sep);
            String part = s.substring(0, end);
            sb.append(part);
            start = end + 1;
        }
    }

    public static final RepeatPrefixNames DEFAULT = new RepeatPrefixNames();

}
