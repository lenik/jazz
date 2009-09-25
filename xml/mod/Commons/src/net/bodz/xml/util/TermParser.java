package net.bodz.xml.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;

import net.bodz.bas.io.LAReader;
import net.bodz.bas.lang.err.IdentifiedException;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.util.PrefetchedIterator;

/**
 * @test {@link TermParserTest}
 */
public class TermParser {

    private final TermDict dict;

    public TermParser() {
        this(null);
    }

    /**
     * @param dict
     *            used to convert name to id-integer.
     */
    public TermParser(TermDict dict) {
        this.dict = dict;
    }

    public Iterable<Term> parse(final String text) {
        if (text == null)
            throw new NullPointerException("text");
        return new Iterable<Term>() {
            @Override
            public Iterator<Term> iterator() {
                return new Iter(new StringReader(text));
            }
        };
    }

    public Iterator<Term> parse(Reader reader) {
        if (reader == null)
            throw new NullPointerException("reader");
        return new Iter(reader);
    }

    public static Iterable<Term> parse(TermDict dict, String text) {
        return new TermParser(dict).parse(text);
    }

    class Iter extends PrefetchedIterator<Term> {

        final LAReader    reader;
        StringBuffer      buffer;
        ArrayList<String> parambuf;

        public Iter(Reader reader) {
            this.reader = new LAReader(reader);
            buffer = new StringBuffer();
            parambuf = new ArrayList<String>();
        }

        @Override
        protected Term fetch() {
            try {
                Term term = nextTerm();
                return term == null ? end() : term;
            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        void skip() {
            try {
                while (Character.isSpaceChar(reader.look()))
                    reader.read();
            } catch (IOException e) {
                throw new IdentifiedException(e.getMessage(), e);
            }
        }

        int nextToken(boolean preferChars) {
            try {
                int c;
                while (true) {
                    c = reader.read();
                    if (c == -1)
                        return EOF;
                    if (Character.isSpaceChar((c)))
                        continue;
                    if (isOperator(c))
                        return c;
                    break;
                }

                buffer.setLength(0);
                buffer.append((char) c);
                if (preferChars) {
                    while (true) {
                        c = reader.look();
                        if (c == -1)
                            break;
                        if (isOperator(c))
                            break;
                        buffer.append((char) reader.read());
                    }
                    return CHARS;
                } else {
                    while (true) {
                        c = reader.look();
                        if (c == -1)
                            break;
                        if (!Character.isLowerCase(c))
                            break;
                        buffer.append((char) reader.read());
                    }
                    return NAME;
                }
            } catch (IOException e) {
                throw new IdentifiedException(e.getMessage(), e);
            }
        }

        Term nextTerm() throws ParseException {
            try {
                int t = nextToken(false);
                if (t == -1)
                    return null;
                if (t != NAME)
                    throw new ParseException("name is expected, while " + getTokenName(t)
                            + " is found");

                String name = buffer.toString();
                int id = 0;
                if (dict != null) {
                    Integer _id = dict.getId(name);
                    if (_id != null)
                        id = _id;
                }
                String[] parameters = null;
                String[] bounds = null;

                skip();
                if (reader.look() == '(') {
                    reader.read();
                    parameters = nextArgs(')');
                    skip();
                }
                if (reader.look() == '[') {
                    reader.read();
                    bounds = nextArgs(']');
                    skip();
                }
                return new Term(id, name, parameters, bounds);
            } catch (IOException e) {
                throw new IdentifiedException(e.getMessage(), e);
            }
        }

        String nextArg(int terminator) throws ParseException {
            try {
                skip();
                int c = reader.look();
                if (c == -1 || c == terminator)
                    return null;
                if (isOperator(c))
                    return "";
                int t = nextToken(true);
                if (t != CHARS)
                    throw new ParseException("invalid argument");
                return buffer.toString().trim();
            } catch (IOException e) {
                throw new IdentifiedException(e.getMessage(), e);
            }
        }

        String[] nextArgs(int terminator) throws ParseException {
            try {
                // first CHARS
                String arg = nextArg(terminator);
                if (arg == null)
                    return null;
                parambuf.clear();
                parambuf.add(arg);

                // rest (',' CHARS)*
                while (true) {
                    skip();
                    int c = reader.look();
                    if (c == -1 || c == terminator)
                        break;
                    if (c != ',')
                        throw new ParseException("argument-separator(,) expected, while "
                                + getTokenName(c) + " is found");
                    reader.read(); // eat ','

                    arg = nextArg(terminator);
                    if (arg == null) {
                        parambuf.add("");
                        break;
                    }
                    parambuf.add(arg);
                }
                if (reader.look() == terminator)
                    reader.read();
                return parambuf.toArray(new String[0]);
            } catch (IOException e) {
                throw new IdentifiedException(e.getMessage(), e);
            }
        }

    }

    private static final int      EOF         = -1;
    // \w[a-z0-9_]*+
    private static final int      NAME        = 1000;
    // [^,]+
    private static final int      CHARS       = 1001;

    private static final String[] _TokenNames = new String[] { "NAME", "CHARS", };

    private static String getTokenName(int token) {
        if (token >= 1000)
            return _TokenNames[token - 1000];
        return String.valueOf((char) token);
    }

    private static final int[] _Operators;
    static {
        _Operators = new int[] {
        /* 0 */
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        /* 1 */
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        /* 2 */
        0, 0, 0, 0, 0, 0, 0, 0, '(', ')', 0, 0, ',', 0, 0, 0,
        /* 3 */
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        /* 4 */
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        /* 5 */
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '[', 0, ']', 0, 0,
        /* 6 */
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        /* 7 */
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '{', 0, '}', 0, };
    }

    private static boolean isOperator(int c) {
        return c < _Operators.length && _Operators[c] > 0;
    }

}
