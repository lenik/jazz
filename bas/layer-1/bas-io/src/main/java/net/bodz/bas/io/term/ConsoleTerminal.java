package net.bodz.bas.io.term;

import java.io.IOException;

import net.bodz.bas.exceptions.OutOfDomainException;
import net.bodz.bas.io.CharOut;

public class ConsoleTerminal extends _Terminal {

    private CharOut out;
    private int width;

    /** just commited */
    static final int START = 0;

    /** begin of line */
    static final int BOL = 1;

    /** middle of line */
    static final int DIRTY = 2;

    /** line contains test/preview buffer, see {@link #blen}. */
    static final int TBUF = 3;

    private int state = START;
    private int blen;

    public ConsoleTerminal(CharOut out, int width) {
        if (out == null)
            throw new NullPointerException("out"); //$NON-NLS-1$
        if (width < 1)
            throw new OutOfDomainException("width", width, 1); //$NON-NLS-1$
        this.out = out;
        this.width = width;
    }

    @Override
    public CharOut getCharOut() {
        return out;
    }

    protected String getLead() {
        return ""; //$NON-NLS-1$
    }

    private final boolean isEOL(String s) {
        int len = s.length();
        if (len == 0)
            return false;
        char c = s.charAt(len - 1);
        return (c == '\r' || c == '\n');
    }

    @Override
    public void n(String s) {
        assert s != null : "null string"; //$NON-NLS-1$
        switch (state) {
        case START:
            out.print(getLead());
            break;
        case TBUF:
            killt();
        }
        state = DIRTY;
        if (isEOL(s))
            state = BOL;
        out.print(s);
    }

    @Override
    public void p() {
        p(""); //$NON-NLS-1$
    }

    @Override
    public void p(String s) {
        assert s != null : "null string"; //$NON-NLS-1$
        switch (state) {
        case START:
            out.print(getLead());
            break;
        case TBUF:
            killt();
        }
        out.println(s);
        state = BOL;
    }

    void killt() {
        String pad = Strings.repeat(blen, ' ');
        out.print(pad);
        out.print('\r');
        blen = 0;
    }

    @Override
    public void t(String s) {
        assert s != null : "null string"; //$NON-NLS-1$
        if (state == DIRTY)
            out.println();
        String t = getLead() + s;
        t = Strings.ellipse(t, width);
        out.print(t);
        int len = t.length();
        if (len < blen) {
            String pad = Strings.repeat(blen - len, ' ');
            out.print(pad);
        }
        blen = len;
        out.print('\r');
        state = TBUF;
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }

}
