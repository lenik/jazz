package net.bodz.bas.io.term;

import java.io.IOException;

import net.bodz.bas.io.out.CharOut;

public class TerminalCharOut extends CharOut {

    final Terminal terminal;

    public TerminalCharOut(Terminal terminal) {
        if (terminal == null)
            throw new NullPointerException("terminal"); 
        this.terminal = terminal;
    }

    @Override
    public void _close() throws IOException {
        terminal.close();
    }

    @Override
    public void _flush() throws IOException {
        terminal.flush();
    }

    @Override
    public void write(char[] chars, int off, int len) throws IOException {
        String s = new String(chars, off, len);
        terminal.n(s);
    }

}
