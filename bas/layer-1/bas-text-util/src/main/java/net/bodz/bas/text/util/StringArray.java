package net.bodz.bas.text.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import net.bodz.bas.c1.util.Pair;
import net.bodz.bas.exceptions.NotImplementedException;
import net.bodz.bas.exceptions.UnexpectedException;

public class StringArray {

    public static String join(String separator, Object array) {
        if (array == null)
            throw new NullPointerException("array");
        if (!array.getClass().isArray())
            throw new IllegalArgumentException("Not an array: " + array.getClass());
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
        return buffer == null ? "" : buffer.toString();
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

    public static String join(String separator, Enumeration<?> enumr) {
        StringBuffer buffer = null;
        while (enumr.hasMoreElements()) {
            Object o = enumr.nextElement();
            if (buffer == null)
                buffer = new StringBuffer();
            else
                buffer.append(separator);
            buffer.append(String.valueOf(o));
        }
        return buffer == null ? "" : buffer.toString();

    }

    public static Pair<String, String> join(String separatorKey, String separatorValue, Map<?, ?> map) {
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
            return "";
        return buf.toString();
    }

    /** trim each token */
    public static final int TRIM = 1;

    /** EOL also used to delimit tokens */
    public static final int MULTILINES = 2;

    /** string quoted by " or ' are treated as single token */
    public static final int QUOTE = 4;

    /** remove quote chars, only affects with {@link #QUOTE} */
    public static final int DEQUOTE = 8;

    /** line comment (//) and block comment are removed */
    public static final int COMMENT = 16;

    /**
     * @param delims
     *            whitespace if <code>null</code>
     */
    public static String[] split(String src, char[] delims, int limit, int flags) {
        boolean trimList = limit == 0;
        boolean trim = 0 != (flags & TRIM);
        if (limit <= 0)
            limit = Integer.MAX_VALUE;

        Reader reader = new StringReader(src);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        tokenizer.resetSyntax();
        tokenizer.wordChars(Character.MIN_CODE_POINT, Character.MAX_CODE_POINT);
        if (delims == null)
            for (char sp : " \t\n\r".toCharArray())
                tokenizer.whitespaceChars(sp, sp);
        else
            for (char d : delims)
                tokenizer.ordinaryChar(d);
        if (0 != (flags & MULTILINES)) {
            tokenizer.whitespaceChars('\n', '\n');
            tokenizer.whitespaceChars('\r', '\r');
        }
        if (0 != (flags & QUOTE)) {
            // TODO - DEQUOTE or not.
            if (0 == (flags & DEQUOTE))
                throw new NotImplementedException("raw quote isn't impl.");
            tokenizer.quoteChar('"');
            tokenizer.quoteChar('\'');
        }
        if (0 != (flags & COMMENT)) {
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
                if (buf == null)
                    buf = new StringBuffer();

                // flush peek cache in StreamTokenizer
                tokenizer.resetSyntax(); // make all chars ordinary
                while ((token = tokenizer.nextToken()) != StreamTokenizer.TT_EOF)
                    // tailbuf.append("<" + (char) lastToken + ">");
                    buf.append((char) token);

                // rest=reader.readRest()
                // buf.append(rest);
                int c;
                while (true) {
                    c = reader.read();
                    if (c == -1)
                        break;
                    buf.append((char) c);
                }

                String tail = buf.toString();
                if (delims == null)
                    tail = StringPart.trimRight(tail);
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
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }

        int size = list.size();
        if (trim)
            for (int i = 0; i < size; i++)
                list.set(i, list.get(i).trim());
        if (trimList)
            while (size > 0 && list.get(size - 1).isEmpty())
                list.remove(--size);
        return list.toArray(new String[0]);
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
            throw new IllegalArgumentException("empty sizes");
        int len = s.length();
        int _sizesum = 0;
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            if (size <= 0)
                throw new IllegalArgumentException("illegal size [" + i + "]");
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
        return list.toArray(new String[0]);
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

}
