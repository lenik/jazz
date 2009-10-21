package net.bodz.bas.types.util;

import static net.bodz.bas.lang.BoolMath.test;
import static net.bodz.bas.types.util.ArrayOps.Chars;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.lang.err.NotImplementedException;
import net.bodz.bas.text.diff.DiffComparator;
import net.bodz.bas.text.diff.DiffComparators;
import net.bodz.bas.text.diff.DiffFormat;
import net.bodz.bas.text.diff.DiffFormats;
import net.bodz.bas.text.diff.DiffInfo;
import net.bodz.bas.text.util.Interps;
import net.bodz.bas.text.util.PatternProcessor;
import net.bodz.bas.types.Pair;

/**
 * @test {@link StringsTest}
 */
public class Strings {

    /**
     * @see Objects#equals(Object, Object)
     */
    @Deprecated
    public static boolean equals(String a, String b) {
        if (a == null || b == null)
            return a == b;
        return a.equals(b);
    }

    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null || b == null)
            return a == b;
        return a.equalsIgnoreCase(b);
    }

    public static int indexOfIgnoreCase(String s, String needle, Locale locale) {
        return s.toLowerCase(locale).indexOf(needle.toLowerCase(locale));
    }

    public static int indexOfIgnoreCase(String s, String needle) {
        return s.toLowerCase().indexOf(needle.toLowerCase());
    }

    public static int indexOfIgnoreCase(String s, char needle, Locale locale) {
        return s.toLowerCase(locale).indexOf(Character.toLowerCase(needle));
    }

    public static int indexOfIgnoreCase(String s, char needle) {
        return s.toLowerCase().indexOf(Character.toLowerCase(needle));
    }

    public static String chop(String s, int chopLen) {
        assert s != null;
        if (s.length() < chopLen)
            return ""; //$NON-NLS-1$
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
        return chomp(s, "\n"); //$NON-NLS-1$
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

    static final char PADCHAR = ' ';

    public static String padLeft(String s, int len, char padChar) {
        int padLen = len - s.length();
        if (padLen <= 0)
            return s;
        return Strings.repeat(padLen, padChar) + s;
    }

    public static String padLeft(String s, int len) {
        return padLeft(s, len, PADCHAR);
    }

    public static String padRight(String s, int len, char padChar) {
        int padLen = len - s.length();
        if (padLen <= 0)
            return s;
        return s + Strings.repeat(padLen, padChar);
    }

    public static String padRight(String s, int len) {
        return padRight(s, len, PADCHAR);
    }

    public static String padMiddle(String s, int len, char padChar) {
        int padLen = len - s.length();
        if (padLen <= 0)
            return s;
        int padLeft = padLen / 2; // slightly left-ward
        int padRight = padLen - padLeft;
        return Strings.repeat(padLeft, padChar) + s + Strings.repeat(padRight, padChar);
    }

    public static String padMiddle(String s, int len) {
        return padMiddle(s, len, PADCHAR);
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

    static Pattern words = Pattern.compile("\\w+"); //$NON-NLS-1$

    public static String ucfirstWords(String s) {
        return new PatternProcessor(words) {
            @Override
            protected void matched(String part) {
                print(ucfirst(part));
            }
        }.process(s);
    }

    /**
     * helloWorld => hello-world
     */
    public static String hyphenatize(String words) {
        while (words.startsWith("_")) //$NON-NLS-1$
            words = words.substring(1);
        BCharOut buf = new BCharOut(words.length() * 3 / 2);
        boolean breakNext = false;
        for (int wordStart = 0; wordStart < words.length();) {
            int wordEnd;
            wordEnd = indexOf(words, Character.UPPERCASE_LETTER, wordStart + 1);
            if (wordEnd == -1)
                wordEnd = words.length();
            String word = words.substring(wordStart, wordEnd);
            if (breakNext)
                buf.print('-');
            buf.print(word);
            breakNext = word.length() > 1;
            wordStart = wordEnd;
        }
        String s = buf.toString();
        if (s.startsWith("-")) //$NON-NLS-1$
            s = s.substring(1);
        return s.toLowerCase();
    }

    /**
     * hello-world => helloWorld
     */
    public static String dehyphenatize(String hstr) {
        String[] parts = hstr.split("-"); //$NON-NLS-1$
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

    public static String q(String s) {
        return "'" + s + "'"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static String qq(String s) {
        return '"' + s + '"';
    }

    public static String join(String separator, Object array) {
        StringBuffer buffer = null;
        int len = Array.getLength(array);
        for (int i = 0; i < len; i++) {
            if (buffer == null)
                buffer = new StringBuffer();
            else
                buffer.append(separator);
            Object e = Array.get(array, i);
            buffer.append(e);
        }
        return buffer == null ? "" : buffer.toString(); //$NON-NLS-1$
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
        return buffer == null ? "" : buffer.toString(); //$NON-NLS-1$
    }

    public static String join(String separator, Enumeration<?> enumr) {
        return join(separator, Iterators.iterator(enumr));
    }

    public static Pair<String, String> join(String separatorKey, String separatorValue,
            Map<?, ?> map) {
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
            return new Pair<String, String>("", ""); //$NON-NLS-1$ //$NON-NLS-2$
        else
            return new Pair<String, String>(bufferKey.toString(), bufferValue.toString());
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
        if (buf == null)
            return ""; //$NON-NLS-1$
        return buf.toString();
    }

    /** trim each token */
    public static final int TRIM       = 1;

    /** EOL also used to delimit tokens */
    public static final int MULTILINES = 2;

    /** string quoted by " or ' are treated as single token */
    public static final int QUOTE      = 4;

    /** remove quote chars, only affects with {@link #QUOTE} */
    public static final int DEQUOTE    = 8;

    /** line comment (//) and block comment are removed */
    public static final int COMMENT    = 16;

    /**
     * @param delims
     *            whitespace if <code>null</code>
     */
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
            for (char sp : " \t\n\r".toCharArray()) //$NON-NLS-1$
                tokenizer.whitespaceChars(sp, sp);
        else
            for (char d : delims)
                tokenizer.ordinaryChar(d);
        if (test(flags & MULTILINES)) {
            tokenizer.whitespaceChars('\n', '\n');
            tokenizer.whitespaceChars('\r', '\r');
        }
        if (test(flags & QUOTE)) {
            // TODO - DEQUOTE or not.
            if (!test(flags & DEQUOTE))
                throw new NotImplementedException("raw quote isn't impl."); //$NON-NLS-1$
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
            while (got < limit && (token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
                // boolean quote = false;
                switch (token) {
                case '"':
                case '\'':
                    // quote = true;
                case StreamTokenizer.TT_WORD:
                    String val = tokenizer.sval; // body of the string
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

    /**
     * @see #split(String, char[], int, int)
     * @see #TRIM
     * @see #QUOTE
     * @see #DEQUOTE
     */
    public static String[] split(String s, char[] delims, int limit) {
        return split(s, delims, limit, TRIM | QUOTE | DEQUOTE);
    }

    /**
     * @see #split(String, char[], int, int)
     * @see #TRIM
     * @see #QUOTE
     * @see #DEQUOTE
     */
    public static String[] split(String s, char... delims) {
        if (delims.length == 0)
            delims = null;
        return split(s, delims, 0);
    }

    public static String[] splitBySize(String s, int[] sizes, int limit) {
        if (limit == 0)
            limit = Integer.MAX_VALUE;
        if (sizes.length < 1)
            throw new IllegalArgumentException("empty sizes"); //$NON-NLS-1$
        int len = s.length();
        int _sizesum = 0;
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            if (size <= 0)
                throw new IllegalArgumentException("illegal size [" + i + "]"); //$NON-NLS-1$ //$NON-NLS-2$
            _sizesum += size;
        }
        List<String> list = new ArrayList<String>(len / _sizesum * sizes.length + 1);
        int start = 0;
        int index = 0;
        while (start < len) {
            if (index == limit - 1) {
                s = s.substring(start);
                assert !s.isEmpty();
                list.add(s);
                break;
            }
            int size = sizes[index++ % sizes.length];
            int subsize = Math.min(len - start, size);
            assert subsize != 0;
            list.add(s.substring(start, start + subsize));
            start += subsize;
        }
        return list.toArray(Empty.Strings);
    }

    public static String[] splitBySize(String s, int size, int limit) {
        int len = s.length();
        if (len <= size)
            return new String[] { s };
        return splitBySize(s, new int[] { size }, limit);
    }

    public static String[] splitBySize(String s, int size) {
        return splitBySize(s, size, 0);
    }

    public static int indexOf(String s, char[] charSet, int fromIndex) {
        int len = s.length();
        for (int i = fromIndex; i < len; i++) {
            char c = s.charAt(i);
            if (Chars.indexOf(charSet, c) != -1)
                return i;
        }
        return -1;
    }

    public static int indexOf(String s, char... charSet) {
        return indexOf(s, charSet, 0);
    }

    public static int indexOf(String s, int charType, int fromIndex) {
        int len = s.length();
        for (int i = fromIndex; i < len; i++) {
            char c = s.charAt(i);
            int type = Character.getType(c);
            if (type == charType)
                return i;
        }
        return -1;
    }

    public static int indexOf(String s, int charType) {
        return indexOf(s, charType, 0);
    }

    public static int lastIndexOf(String s, char[] charSet, int fromIndex) {
        int len = s.length();
        if (fromIndex >= len)
            fromIndex = len - 1;
        for (int i = fromIndex; i >= 0; i--) {
            char c = s.charAt(i);
            if (Chars.indexOf(charSet, c) != -1)
                return i;
        }
        return -1;
    }

    public static int lastIndexOf(String s, char[] charSet) {
        return lastIndexOf(s, charSet, s.length() - 1);
    }

    public static int lastIndexOf(String s, int charType, int fromIndex) {
        int len = s.length();
        if (fromIndex >= len)
            fromIndex = len - 1;
        for (int i = fromIndex; i >= 0; i--) {
            char c = s.charAt(i);
            int type = Character.getType(c);
            if (type == charType)
                return i;
        }
        return -1;
    }

    public static int lastIndexOf(String s, int charType) {
        return lastIndexOf(s, charType, s.length() - 1);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String before(String s, String literalPattern) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String before(String s, char literalPattern) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't contained
     */
    public static String before(String s, byte charCategory) {
        int pos = indexOf(s, charCategory);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String beforeLast(String s, String literalPattern) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String beforeLast(String s, char literalPattern) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't contained
     */
    public static String beforeLast(String s, byte charCategory) {
        int pos = lastIndexOf(s, charCategory);
        if (pos == -1)
            return null;
        return s.substring(0, pos);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String after(String s, String literalPattern) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(pos + literalPattern.length());
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String after(String s, char literalPattern) {
        int pos = s.indexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(pos + 1);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't contained
     */
    public static String after(String s, byte charCategory) {
        int pos = indexOf(s, charCategory);
        if (pos == -1)
            return null;
        return s.substring(pos + 1);
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String afterLast(String s, String literalPattern) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(pos + literalPattern.length());
    }

    /**
     * @return <code>null</code> if <code>pattern</code> isn't contained
     */
    public static String afterLast(String s, char literalPattern) {
        int pos = s.lastIndexOf(literalPattern);
        if (pos == -1)
            return null;
        return s.substring(pos + 1);
    }

    /**
     * @return <code>null</code> if <code>charCategory</code> isn't contained
     */
    public static String afterLast(String s, byte charCategory) {
        int pos = lastIndexOf(s, charCategory);
        if (pos == -1)
            return null;
        return s.substring(pos + 1);
    }

    public static int count(String s, char literalPattern) {
        int count = 0;
        int index = 0;
        while ((index = s.indexOf(literalPattern, index)) != -1) {
            index++;
            count++;
        }
        return count;
    }

    public static int count(String s, String literalPattern) {
        int count = 0;
        int index = 0;
        while ((index = s.indexOf(literalPattern, index)) != -1) {
            index += literalPattern.length();
            count++;
        }
        return count;
    }

    public static int count(String s, byte charCategory) {
        int count = 0;
        int index = 0;
        while ((index = indexOf(s, charCategory, index)) != -1) {
            index++;
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
        return ellipse(s, len, "..."); //$NON-NLS-1$
    }

    public static String ellipse(String s, int len, String ellipse, String epstart, String epend) {
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
            return prefix + ellipse(s.substring(estart, eend), len - trims, ellipse) + suffix;
        else
            return ellipse(s, len, ellipse);
    }

    private static PatternProcessor escapeProcessor;
    static {
        escapeProcessor = new PatternProcessor("[\\\\\"\'\r\n]") { //$NON-NLS-1$
            @Override
            protected void matched(String part) {
                assert part.length() == 1;
                char c = part.charAt(0);
                print(escape(c));
            }
        };
    }

    /**
     * Escape literal character in C/Java language.
     */
    public static String escape(int c) {
        switch (c) {
        case '\r':
            return "\\r"; //$NON-NLS-1$
        case '\n':
            return "\\n"; //$NON-NLS-1$
        case '\t':
            return "\\t"; //$NON-NLS-1$
        case '\0':
            return "\\0"; //$NON-NLS-1$

        case '\\': // followings: "\\"+c:
            return "\\\\"; //$NON-NLS-1$
        case '\"':
            return "\\\""; //$NON-NLS-1$
        case '\'':
            return "\\\'"; //$NON-NLS-1$
        }
        return String.valueOf((char) c);
    }

    /**
     * Escape literal string in C/Java language.
     */
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

    public static void diff(List<?> a, List<?> b, CharOut out, DiffFormat format) {
        DiffComparator alg = DiffComparators.gnudiff;
        List<DiffInfo> diffs = alg.diffCompare(a, b);
        format.format(a, b, diffs, out);
    }

    public static void diff(List<?> a, List<?> b, CharOut out) {
        diff(a, b, out, DiffFormats.Simdiff);
    }

    public static String diff(List<?> a, List<?> b) {
        BCharOut buffer = new BCharOut();
        diff(a, b, buffer);
        return buffer.toString();
    }

    public static void diff(String a, String b, CharOut out) {
        String[] av = a.split("\n"); //$NON-NLS-1$
        String[] bv = b.split("\n"); //$NON-NLS-1$
        diff(Arrays.asList(av), Arrays.asList(bv), out);
    }

    public static String diff(String a, String b) {
        BCharOut buffer = new BCharOut();
        diff(a, b, buffer);
        return buffer.toString();
    }

}
