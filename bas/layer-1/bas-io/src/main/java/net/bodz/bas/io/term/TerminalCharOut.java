package net.bodz.bas.io.term;

import java.io.IOException;

import net.bodz.bas.sio.AbstractCharOut;

public class TerminalCharOut
        extends AbstractCharOut {

    final ITerminal terminal;

    public TerminalCharOut(ITerminal terminal) {
        if (terminal == null)
            throw new NullPointerException("terminal");
        this.terminal = terminal;
    }

    @Override
    public void flush(boolean strict)
            throws IOException {
        terminal.flush();
    }

    @Override
    public void write(int ch)
            throws IOException {
        char[] cv = new char[1];
        cv[0] = (char) ch;
        terminal.p_(new String(cv));
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        String s = new String(chars, off, len);
        terminal.p_(s);
    }

}
