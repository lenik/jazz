package net.bodz.bas.c.string;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.UnexpectedException;

public class StringQuoted {

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
        StringBuilder buf = null;
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
                        buf = new StringBuilder(val.length());
                        got++;
                    }
                    // if (quote) buf.append((char) token);
                    buf.append(val);
                    // if (quote) buf.append((char) token);
                    break;
                default: // ordinary char
                    // assert new String(delims).contains(tokenizer.sval);
                    if (buf == null)
                        buf = new StringBuilder();
                    if (list.size() == limit - 1)
                        buf.append((char) token);
                    list.add(buf.toString());
                    buf.setLength(0);
                    got++;
                }
            }
            if (got == limit) {
                if (buf == null)
                    buf = new StringBuilder();

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

}
