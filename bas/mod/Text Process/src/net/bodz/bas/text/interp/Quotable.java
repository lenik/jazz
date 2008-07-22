package net.bodz.bas.text.interp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Quotable extends PatternProcessor {

    int leftTrim  = 0;
    int rightTrim = 0;

    public Quotable(Quotable o) {
        super(o.pattern);
        leftTrim = o.leftTrim;
        rightTrim = o.rightTrim;
    }

    public Quotable(char quoteChar) {
        this(quoteChar, quoteChar);
    }

    public Quotable(char quoteOpen, char quoteClose) {
        super(compile(quoteOpen, quoteClose));
        leftTrim = 1;
        rightTrim = 1;
    }

    public Quotable(char[] quoteChars) {
        super(compile(quoteChars));
        leftTrim = 1;
        rightTrim = 1;
    }

    public Quotable(Pattern quotePattern, int quoteCharLength) {
        super(quotePattern);
        leftTrim = quoteCharLength;
        rightTrim = quoteCharLength;
    }

    private static Pattern compile(char open, char close) {
        String _open = Pattern.quote(String.valueOf(open));
        String _close = Pattern.quote(String.valueOf(close));
        String regex = _open + "(\\\\.|.)*?" + _close;
        return Pattern.compile(regex);
    }

    private static Pattern compile(char[] chars) {
        StringBuffer regexBuf = null;
        for (int i = 0; i < chars.length; i++) {
            if (regexBuf == null)
                regexBuf = new StringBuffer(chars.length * 10);
            else
                regexBuf.append("|");
            char c = chars[i];
            String qc = Pattern.quote(String.valueOf(c));
            regexBuf.append(qc);
        }
        String regex = regexBuf.toString();
        return Pattern.compile(regex);
    }

    @Override
    protected final void matched(String part) {
        dequote(part);
    }

    @Override
    protected void unmatched(String part) {
        String s = interpUnquotedText(part);
        part(s, false);
    }

    protected void dequote(String s) {
        String quotedText = s.substring(leftTrim, s.length() - rightTrim);
        String dequoted = interpQuotedText(quotedText);
        part(dequoted, true);
    }

    protected String interpQuotedText(String quotedText) {
        return Unescape.interp(quotedText);
    }

    protected String interpUnquotedText(String s) {
        return s;
    }

    protected void part(String text, boolean quoted) {
        print(text);
    }

    public String[] split(final String delimiter, String s, int limit) {
        boolean trim = limit == 0;
        final List<String> parts;
        if (limit <= 0) {
            limit = Integer.MAX_VALUE;
            parts = new ArrayList<String>();
        } else
            parts = new ArrayList<String>(limit);
        final int _limit = limit;
        final StringBuffer buf = new StringBuffer();
        final boolean dequote = false;
        new Quotable(this) {
            private int rest = _limit; // assert limit >= 1

            @Override
            protected void dequote(String s) {
                if (dequote)
                    super.dequote(s);
                else
                    part(s, true);
            }

            @Override
            protected void part(String text, boolean quoted) {
                if (quoted) {
                    buf.append(text);
                    return;
                }
                assert rest >= 1;
                if (rest == 1) {
                    buf.append(text);
                    return;
                }
                String[] v = text.split(delimiter, rest);
                buf.append(v[0]);
                // if (v.length > 0)
                if (v.length > 1) {
                    parts.add(buf.toString());
                    buf.setLength(0);
                    for (int i = 1; i < v.length - 1; i++) {
                        parts.add(v[i]);
                    }
                    buf.append(v[v.length - 1]);
                    rest -= v.length - 1;
                }
            }
        }.process(s);
        parts.add(buf.toString());
        if (trim) {
            int n = parts.size() - 1;
            while (n >= 0) {
                String end = parts.get(n);
                if (!end.isEmpty())
                    break;
                parts.remove(n--);
            }
        }
        if (parts.isEmpty())
            return new String[] { "" };
        return parts.toArray(new String[0]);
    }

    public String[] split(String delimiter, String s) {
        return split(delimiter, s, 0);
    }
}
