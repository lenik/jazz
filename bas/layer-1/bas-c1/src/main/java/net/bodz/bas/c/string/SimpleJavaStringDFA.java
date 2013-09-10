package net.bodz.bas.c.string;

import net.bodz.bas.err.ParseException;

public class SimpleJavaStringDFA {

    protected static final int START = 0;
    protected static final int QQ = 1;
    protected static final int ESCAPE = 2;
    protected static final int ESCAPE_UCHAR = 3;
    protected static final int ESCAPE_HEXBYTE = 4;

    protected int state;

    private StringBuilder stringBuffer = new StringBuilder();
    private int esc = 0;
    private int escDigits = 0;

    private String lastString;

    public synchronized Object parse(String s)
            throws ParseException {
        reset();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            receive(ch);
        }
        checkCompletion();
        return lastString;
    }

    protected void reset() {
        state = START;
        stringBuffer.setLength(0);
    }

    protected void receive(char ch)
            throws ParseException {

        ST: switch (state) {
        case START:
            if (ch == '"')
                state = QQ;
            return;

        case QQ:
            switch (ch) {
            case '\"':
                accept(stringBuffer.toString());
                stringBuffer.setLength(0);
                state = START;
                return;

            case '\\':
                state = ESCAPE;
                return;

            default:
                stringBuffer.append(ch);
                return;
            }

        case ESCAPE:
            switch (ch) {
            case 'n':
                ch = '\n';
                break;
            case 't':
                ch = '\t';
                break;
            case 'f':
                ch = '\f';
                break;
            case '0':
                ch = '\0';
                break;
            case 'x':
                state = ESCAPE_HEXBYTE;
                esc = 0;
                escDigits = 0;
                break ST;
            case 'u':
                state = ESCAPE_UCHAR;
                esc = 0;
                escDigits = 0;
                break ST;
            } // switch ch.
            stringBuffer.append(ch);
            state = QQ;
            return;

        case ESCAPE_UCHAR:
            int digit = CharFeature.c2n[ch];
            if (digit < -1 || digit >= 16)
                throw new ParseException("Illegal hex digit: " + ch);

            esc = esc * 16 + digit;

            if (++escDigits == 4) {
                ch = (char) esc;
                stringBuffer.append(ch);
                state = QQ;
            }
            return;

        case ESCAPE_HEXBYTE:
            digit = CharFeature.c2n[ch];
            if (digit < -1 || digit >= 16)
                throw new ParseException("Illegal hex digit: " + ch);

            esc = esc * 16 + digit;

            if (++escDigits == 2) {
                ch = (char) esc;
                stringBuffer.append(ch);
                state = QQ;
            }
            return;
        } // switch state
    }

    protected void checkCompletion()
            throws ParseException {
        // Check the stop state.
        switch (state) {
        case QQ:
            throw new ParseException("Quoted string isn't closed.");
        }
    }

    protected void accept(String stringToken) {
        lastString = stringToken;
    }

    public String getLastString() {
        return lastString;
    }

}
