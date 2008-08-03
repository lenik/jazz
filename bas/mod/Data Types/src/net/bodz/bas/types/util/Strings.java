package net.bodz.bas.types.util;

import static net.bodz.bas.lang.BoolMath.test;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.text.interp.Interps;
import net.bodz.bas.text.interp.PatternProcessor;
import net.bodz.bas.types.Pair;

public class Strings {

    public static final boolean equals(String a, String b) {
        if (a == null || b == null)
            return a == b;
        return a.equals(b);
    }

    public static final boolean equalsIgnoreCase(String a, String b) {
        if (a == null || b == null)
            return a == b;
        return a.equalsIgnoreCase(b);
    }

    public static String chop(String s, int chopLen) {
        assert s != null;
        if (s.length() < chopLen)
            return "";
        return s.substring(0, s.length() - chopLen);
    }

    public static String chop(String s) {
        return chop(s, 1);
    }

    public static String chomp(String s, String pattern) {
        assert s != null;
        assert pattern != null;
        int chars = pattern.length();
        if (s.length() < chars)
            return s;
        int len = s.length();
        if (s.substring(len - chars).equals(pattern))
            return s.substring(0, len - chars);
        return s;
    }

    public static String chomp(String s) {
        return chomp(s, "\n");
    }

    public static String trimLeft(String s, int end) {
        if (end > s.length())
            end = s.length();
        int l = -1;
        while (++l < end)
            if (!Character.isWhitespace(s.charAt(l)))
                break;
        return s.substring(l, end);
    }

    public static String trimLeft(String s) {
        return trimLeft(s, s.length());
    }

    public static String trimRight(String s, int start) {
        if (start < 0)
            start = 0;
        int l = s.length();
        while (--l >= start)
            if (!Character.isWhitespace(s.charAt(l)))
                break;
        return s.substring(start, l + 1);
    }

    public static String trimRight(String s) {
        return trimRight(s, 0);
    }

    public static String ucfirst(String s) {
        if (s == null)
            return null;
        if (s.length() <= 1)
            return s.toUpperCase();
        char ucfirst = Character.toUpperCase(s.charAt(0));
        return ucfirst + s.substring(1);
    }

    public static String lcfirst(String s) {
        if (s == null)
            return null;
        if (s.length() <= 1)
            return s.toUpperCase();
        char lcfirst = Character.toLowerCase(s.charAt(0));
        return lcfirst + s.substring(1);
    }

    public static String hyphenatize(String words) {
        while (words.startsWith("_"))
            words = words.substring(1);
        PatternProcessor pp = new PatternProcessor("[A-Z]+") {
            @Override
            protected void matched(String part) {
                print('-');
                print(part.toLowerCase());
            }
        };
        String s = pp.process(words);
        if (s.startsWith("-"))
            s = s.substring(1);
        return s;
    }

    public static String dehyphenatize(String hstr) {
        String[] parts = hstr.split("-");
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.isEmpty())
                buf.append('_');
            else {
                if (i != 0)
                    part = Strings.ucfirst(part);
                buf.append(part);
            }
        }
        return buf.toString();
    }

    public static String join(String separator, Object... strings) {
        StringBuffer buffer = null;
        for (Object s : strings) {
            if (buffer == null)
                buffer = new StringBuffer();
            else
                buffer.append(separator);
            buffer.append(s);
        }
        return buffer == null ? "" : buffer.toString();
    }

    public static String join(String separator, String... strings) {
        return join(separator, (Object[]) strings);
    }

    public static String join(String separator, Iterable<?> iterable) {
        StringBuffer buffer = null;
        for (Object o : iterable) {
            if (buffer == null)
                buffer = new StringBuffer();
            else
                buffer.append(separator);
            buffer.append(String.valueOf(o));
        }
        return buffer == null ? "" : buffer.toString();
    }

    public static Pair<String, String> join(String separatorKey,
            String separatorValue, Map<?, ?> map) {
        StringBuffer bufferKey = null;
        StringBuffer bufferValue = null;
        for (Object o : map.entrySet()) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) o;
            if (bufferKey == null) {
                bufferKey = new StringBuffer();
                bufferValue = new StringBuffer();
            } else {
                bufferKey.append(separatorKey);
                bufferValue.append(separatorValue);
            }
            bufferKey.append(String.valueOf(entry.getKey()));
            bufferValue.append(String.valueOf(entry.getValue()));
        }
        if (bufferKey == null)
            return new Pair<String, String>("", "");
        else
            return new Pair<String, String>(bufferKey.toString(), bufferValue
                    .toString());
    }

    public static Pair<String, String> join(String separator, Map<?, ?> map) {
        return join(separator, separator, map);
    }

    public static String joinDot(int... values) {
        if (values == null)
            return null;
        StringBuffer buf = null;
        for (int rev : values) {
            if (buf == null)
                buf = new StringBuffer(values.length * 4);
            else
                buf.append('.');
            buf.append(rev);
        }
        return buf.toString();
    }

    /** trim each token */
    public static final int TRIM       = 1;

    /** EOL also used to delimit tokens */
    public static final int MULTILINES = 2;

    /** string quoted by " or ' are treated as single token */
    public static final int QUOTE      = 4;

    /** line comment (//) and block comment are removed */
    public static final int COMMENT    = 8;

    public static String[] split(Object src, char[] delims, int limit, int flags)
            throws IOException {
        boolean trimList = limit == 0;
        boolean trim = test(flags & TRIM);
        if (limit <= 0)
            limit = Integer.MAX_VALUE;
        Reader reader = Files.getReader(src);
        boolean shouldClose = Files.shouldClose(src);

        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        tokenizer.resetSyntax();
        tokenizer.wordChars(Character.MIN_CODE_POINT, Character.MAX_CODE_POINT);
        if (delims == null)
            for (char sp : " \t\n\r".toCharArray())
                tokenizer.whitespaceChars(sp, sp);
        else
            for (char d : delims)
                tokenizer.ordinaryChar(d);
        if (test(flags & MULTILINES)) {
            tokenizer.whitespaceChars('\n', '\n');
            tokenizer.whitespaceChars('\r', '\r');
        }
        if (test(flags & QUOTE)) {
            tokenizer.quoteChar('"');
            tokenizer.quoteChar('\'');
        }
        if (test(flags & COMMENT)) {
            tokenizer.slashSlashComments(true);
            tokenizer.slashStarComments(true);
        }

        List<String> list = new ArrayList<String>();
        int got = 0;
        int token;
        StringBuffer buf = null;
        try {
            while (got < limit
                    && (token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
                // boolean quote = false;
                switch (token) {
                case '"':
                case '\'':
                    // quote = true;
                case StreamTokenizer.TT_WORD:
                    String val = tokenizer.sval;
                    if (delims == null && buf != null) {
                        list.add(buf.toString());
                        buf.setLength(0);
                        got++;
                    }
                    if (buf == null) {
                        buf = new StringBuffer(val.length());
                        got++;
                    }
                    // if (quote) buf.append((char) token);
                    buf.append(val);
                    // if (quote) buf.append((char) token);
                    break;
                default: // ordinary char
                    // assert new String(delims).contains(tokenizer.sval);
                    if (buf == null)
                        buf = new StringBuffer();
                    if (list.size() == limit - 1)
                        buf.append((char) token);
                    list.add(buf.toString());
                    buf.setLength(0);
                    got++;
                }
            }
            if (got == limit) {
                String rest = Files.readAll(reader);
                if (buf == null)
                    buf = new StringBuffer(rest.length());

                // flush peek cache in StreamTokenizer
                tokenizer.resetSyntax(); // make all chars ordinary
                while ((token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF)
                    // tailbuf.append("<" + (char) lastToken + ">");
                    buf.append((char) token);

                buf.append(rest);
                String tail = buf.toString();
                if (delims == null)
                    tail = trimRight(tail);
                if (got > list.size())
                    list.add(tail);
                else {
                    int end = list.size() - 1;
                    list.set(end, list.get(end) + tail);
                }
                buf = null;
            }
            if (buf != null) {
                assert list.size() < limit;
                list.add(buf.toString());
            }
        } finally {
            if (shouldClose)
                reader.close();
        }

        int size = list.size();
        if (trim)
            for (int i = 0; i < size; i++)
                list.set(i, list.get(i).trim());
        if (trimList)
            while (size > 0 && list.get(size - 1).isEmpty())
                list.remove(--size);
        return list.toArray(Empty.Strings);
    }

    public static String[] split(String s, char[] delims, int limit, int flags) {
        try {
            return split(new StringReader(s), delims, limit, flags);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static String[] split(String s, char[] delims, int limit) {
        return split(s, delims, limit, TRIM | QUOTE);
    }

    public static String[] split(String s, char... delims) {
        if (delims.length == 0)
            delims = null;
        return split(s, delims, 0);
    }

    public static String before(String string, String pattern) {
        int pos = string.indexOf(pattern);
        if (pos == -1)
            throw new NotImplementedException(
                    "TODO: see substring-before for not-found");
        return string.substring(0, pos);
    }

    public static String before(String s, char pattern) {
        int pos = s.indexOf(pattern);
        if (pos == -1)
            throw new NotImplementedException(
                    "TODO: see substring-before for not-found");
        return s.substring(0, pos);
    }

    public static String after(String s, String pattern) {
        int pos = s.indexOf(pattern);
        if (pos == -1)
            throw new NotImplementedException(
                    "TODO: see substring-after for not-found");
        return s.substring(pos + 1);
    }

    public static String after(String s, char pattern) {
        int pos = s.indexOf(pattern);
        if (pos == -1)
            throw new NotImplementedException(
                    "TODO: see substring-after for not-found");
        return s.substring(pos + 1);
    }

    public static int count(String s, char pattern) {
        int count = 0;
        int index = 0;
        while ((index = s.indexOf(pattern, index)) != -1) {
            index++;
            count++;
        }
        return count;
    }

    public static int count(String s, String pattern) {
        int count = 0;
        int index = 0;
        while ((index = s.indexOf(pattern, index)) != -1) {
            index += pattern.length();
            count++;
        }
        return count;
    }

    public static String repeat(int count, String pattern) {
        StringBuffer buf = new StringBuffer(pattern.length() * count);
        while (--count >= 0)
            buf.append(pattern);
        return buf.toString();
    }

    public static String repeat(int count, char c) {
        char[] buf = new char[count];
        Arrays.fill(buf, c);
        return new String(buf);
    }

    public static String ellipse(String s, int len, String ellipse) {
        if (s.length() <= len)
            return s;
        int elen = ellipse.length();
        if (len > elen)
            return s.substring(0, len - elen) + ellipse;
        else
            return ellipse.substring(0, len);
    }

    public static String ellipse(String s, int len) {
        return ellipse(s, len, "...");
    }

    public static String ellipse(String s, int len, String ellipse,
            String epstart, String epend) {
        int estart = 0;
        if (epstart != null)
            if ((estart = s.indexOf(epstart)) == -1)
                estart = 0;
            else
                estart += epstart.length();
        int eend = s.length();
        if (epend != null)
            if ((eend = s.lastIndexOf(epend)) == -1)
                eend = s.length();
        int trims = estart + (s.length() - eend);
        String prefix = s.substring(0, estart);
        String suffix = s.substring(eend);
        if (len > trims + ellipse.length())
            return prefix
                    + ellipse(s.substring(estart, eend), len - trims, ellipse)
                    + suffix;
        else
            return ellipse(s, len, ellipse);
    }

    private static PatternProcessor escapeProcessor;
    static {
        escapeProcessor = new PatternProcessor("[\\\\\"\'\r\n]") {
            @Override
            protected void matched(String part) {
                assert part.length() == 1;
                char c = part.charAt(0);
                print(escape(c));
            }
        };
    }

    public static String escape(char c) {
        switch (c) {
        case '\r':
            return "\\r";
        case '\n':
            return "\\n";
        case '\t':
            return "\\t";
        case '\0':
            return "\\0";

        case '\\': // followings: "\\"+c:
            return "\\\\";
        case '\"':
            return "\\\"";
        case '\'':
            return "\\\'";
        }
        return String.valueOf(c);
    }

    public static String escape(String s) {
        if (s == null)
            return s;
        return escapeProcessor.process(s);
    }

    public static String[] findAll(String s, Pattern pattern, int group) {
        Matcher m = pattern.matcher(s);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group(group));
        }
        return list.toArray(Empty.Strings);
    }

    public static String[] findAll(String s, Pattern pattern) {
        return findAll(s, pattern, 0);
    }

    public static String[] findAll(String s, Pattern pattern, String replacement) {
        Matcher m = pattern.matcher(s);
        List<String> list = new ArrayList<String>();
        while (m.find()) {
            String deref = Interps.dereference(replacement, m);
            list.add(deref);
        }
        return list.toArray(Empty.Strings);
    }

}
