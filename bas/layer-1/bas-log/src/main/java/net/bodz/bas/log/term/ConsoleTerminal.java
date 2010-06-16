package net.bodz.bas.log.term;

import java.io.IOException;

import net.bodz.bas.exceptions.OutOfDomainException;
import net.bodz.bas.sio.IPrintCharOut;
import net.bodz.bas.text.util.Strings;

public class ConsoleTerminal
        extends AbstractTerminal {

    private IPrintCharOut out;
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

    public ConsoleTerminal(IPrintCharOut out, int width) {
        if (out == null)
            throw new NullPointerException("out");
        if (width < 1)
            throw new OutOfDomainException("width", width, 1);
        this.out = out;
        this.width = width;
    }

    @Override
    public IPrintCharOut getCharOut() {
        return out;
    }

    protected String getLead() {
        return "";
    }

    private final boolean isEOL(String s) {
        int len = s.length();
        if (len == 0)
            return false;
        char c = s.charAt(len - 1);
        return (c == '\r' || c == '\n');
    }

    @Override
    public void p_(String s) {
        assert s != null : "null string";
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
        p("");
    }

    @Override
    public void p(String s) {
        assert s != null : "null string";
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

    // @Override
    // public void p_(String s) {
    // assert s != null : "null string";
    // if (state == DIRTY)
    // out.println();
    // String t = getLead() + s;
    // t = Strings.ellipse(t, width);
    // out.print(t);
    // int len = t.length();
    // if (len < blen) {
    // String pad = Strings.repeat(blen - len, ' ');
    // out.print(pad);
    // }
    // blen = len;
    // out.print('\r');
    // state = TBUF;
    // }

    @Override
    public void flush()
            throws IOException {
        out.flush();
    }

}
