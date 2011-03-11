package net.bodz.bas.regex;

import java.util.List;
import java.util.regex.Pattern;

import net.bodz.bas.string.Strings;

public class QuoteFormat {

    final Pattern quotePattern;
    final int quoteOpenLen;
    final int quoteCloseLen;

    QuoteFormat(Pattern quotePattern, int quoteOpenLen, int quoteCloseLen) {
        if (quotePattern == null)
            throw new NullPointerException("quotePattern");
        if (quoteOpenLen < 1)
            throw new IllegalArgumentException("quoteOpenLen must >= 1: " + quoteOpenLen);
        if (quoteCloseLen < 1)
            throw new IllegalArgumentException("quoteCloseLen must >= 1: " + quoteCloseLen);
        this.quotePattern = quotePattern;
        this.quoteOpenLen = quoteOpenLen;
        this.quoteCloseLen = quoteCloseLen;
    }

    public QuoteFormat(QuoteFormat o) {
        this(o.quotePattern, o.quoteOpenLen, o.quoteCloseLen);
    }

    public QuoteFormat(char quoteChar) {
        this(quoteChar, quoteChar);
    }

    public QuoteFormat(char quoteOpen, char quoteClose) {
        this(compileSimpleQuotePattern(quoteOpen, quoteClose), 1, 1);
    }

    public QuoteFormat(char... quoteChars) {
        this(compileQuotePatterbByCharSet(quoteChars), 1, 1);
    }

    private static Pattern compileSimpleQuotePattern(char open, char close) {
        String _open = Pattern.quote(String.valueOf(open));
        String _close = Pattern.quote(String.valueOf(close));
        String regex = _open + "((?:\\\\.|.)*?)" + _close;
        return Pattern.compile(regex);
    }

    private static Pattern compileQuotePatterbByCharSet(char[] chars) {
        StringBuilder regexBuf = null;
        for (int i = 0; i < chars.length; i++) {
            if (regexBuf == null)
                regexBuf = new StringBuilder(chars.length * 10);
            else
                regexBuf.append("|");
            char c = chars[i];
            String qc = Pattern.quote(String.valueOf(c));
            regexBuf.append(qc + "((?:\\\\.|.)*?)" + qc);
        }
        String regex = regexBuf.toString();
        return Pattern.compile(regex);
    }

    /**
     * @see Strings#split(String, char[], int)
     */
    public String[] _split(boolean dequote, String delimitor, String s, int limit) {
        QuotedSplitProcessor qsp = new QuotedSplitProcessor(this, dequote, delimitor, limit);
        List<String> parts = qsp.splitList(s);
        if (limit == 0) { // Remove empty suffix parts
            int n = parts.size();
            while (n >= 1) {
                String end = parts.get(n - 1);
                if (!end.isEmpty())
                    break;
                parts.remove(--n);
            }
        }
        if (parts.isEmpty())
            return new String[] { "" };
        return parts.toArray(new String[0]);
    }

    /**
     * @see Strings#split(String, char[], int)
     */
    public String[] split(String delim, String s, int limit) {
        return _split(false, delim, s, limit);
    }

    /**
     * @see Strings#split(String, char[], int)
     */
    public String[] split(String delim, String s) {
        return _split(false, delim, s, 0);
    }

    /**
     * @see Strings#split(String, char[], int)
     */
    public String[] splitDequoted(String delim, String s, int limit) {
        return _split(true, delim, s, limit);
    }

    /**
     * @see Strings#split(String, char[], int)
     */
    public String[] splitDequoted(String delim, String s) {
        return _split(true, delim, s, 0);
    }

    public static final QuoteFormat Q = new QuoteFormat('\'');
    public static final QuoteFormat QQ = new QuoteFormat('\"');
    public static final QuoteFormat Q_QQ = new QuoteFormat('\'', '\"');

}
