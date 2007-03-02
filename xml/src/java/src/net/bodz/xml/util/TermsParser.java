package net.bodz.xml.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.freejava.err.IdentifiedException;
import net.sf.freejava.err.ParseException;
import net.sf.freejava.util.LAReader;

public class TermsParser {

    protected LAReader        lar;
    private StringBuffer      buf;
    private ArrayList<String> args;

    public TermsParser() {
        buf = new StringBuffer();
        args = new ArrayList<String>();
    }

    protected void setSrc(String src) {
        lar = new LAReader(src);
    }

    protected void align() {
        try {
            while (Character.isSpaceChar(lar.look()))
                lar.read();
        } catch (IOException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }

    }

    protected int nextToken(boolean preferChars) {
        try {
            int c;
            while (true) {
                c = lar.read();
                if (c == -1)
                    return EOF;
                if (Character.isSpaceChar((c)))
                    continue;
                if (isOperator(c))
                    return c;
                break;
            }

            buf.setLength(0);
            buf.append((char) c);
            if (preferChars) {
                while (true) {
                    c = lar.look();
                    if (c == -1)
                        break;
                    if (isOperator(c))
                        break;
                    buf.append((char) lar.read());
                }
                return CHARS;
            } else {
                while (true) {
                    c = lar.look();
                    if (c == -1)
                        break;
                    if (!Character.isLowerCase(c))
                        break;
                    buf.append((char) lar.read());
                }
                return NAME;
            }
        } catch (IOException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
    }

    protected Term nextTerm() throws ParseException {
        try {
            int t = nextToken(false);
            if (t == -1)
                return null;
            if (t != NAME)
                throw new ParseException("name is expected, while "
                        + getTokenName(t) + " is found");

            Term term = new Term();
            term.name = buf.toString();

            align();
            if (lar.look() == '(') {
                lar.read();
                term.setParams(nextArgs(')'));
                align();
            }
            if (lar.look() == '[') {
                lar.read();
                term.setDims(nextArgs(']'));
                align();
            }
            return term;
        } catch (IOException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
    }

    protected String nextArg(int terminator) throws ParseException {
        try {
            align();
            int c = lar.look();
            if (c == -1 || c == terminator)
                return null;
            if (isOperator(c))
                return "";
            int t = nextToken(true);
            if (t != CHARS)
                throw new ParseException("invalid argument");
            return buf.toString().trim();
        } catch (IOException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
    }

    protected String[] nextArgs(int terminator) throws ParseException {
        try {
            // first CHARS
            String arg = nextArg(terminator);
            if (arg == null)
                return null;
            args.clear();
            args.add(arg);

            // rest (',' CHARS)*
            while (true) {
                align();
                int c = lar.look();
                if (c == -1 || c == terminator)
                    break;
                if (c != ',')
                    throw new ParseException(
                            "argument-separator(,) expected, while "
                                    + getTokenName(c) + " is found");
                lar.read(); // eat ','

                arg = nextArg(terminator);
                if (arg == null) {
                    args.add("");
                    break;
                }
                args.add(arg);
            }
            if (lar.look() == terminator)
                lar.read();
            return args.toArray(new String[0]);
        } catch (IOException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
    }

    public Term[] nextTerms(String src) throws ParseException {
        setSrc(src);
        List<Term> terms = new ArrayList<Term>();
        Term term;
        while ((term = nextTerm()) != null) {
            terms.add(term);
        }
        return terms.toArray(new Term[0]);
    }

    private static TermsParser instance;

    public static Term[] parse(String src) throws ParseException {
        if (instance == null)
            instance = new TermsParser();
        return instance.nextTerms(src);
    }

    private static final int      EOF         = -1;
    // \w[a-z0-9_]*+
    private static final int      NAME        = 1000;
    // [^,]+
    private static final int      CHARS       = 1001;

    /**
     * <code>
     // TERM*
     private static final int      TERMS       = 1000;
     // NAME PARAMS? DIMS?
     private static final int      TERM        = 1001;
     // '(' ARGS ')'
     private static final int      PARAMS      = 1003;
     // '[' ARGS ']'
     private static final int      DIMS        = 1004;
     // CHARS? (',' CHARS?)*
     private static final int      ARGS        = 1005;
     */

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
