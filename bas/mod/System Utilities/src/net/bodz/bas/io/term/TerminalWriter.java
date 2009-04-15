package net.bodz.bas.io.term;

import java.io.IOException;
import java.io.Writer;

public class TerminalWriter extends Writer {

    private final Terminal terminal;

    public TerminalWriter(Terminal terminal) {
        if (terminal == null)
            throw new NullPointerException("terminal");
        this.terminal = terminal;
    }

    @Override
    public void close() throws IOException {
        terminal.close();
    }

    @Override
    public void flush() throws IOException {
        terminal.flush();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String s = new String(cbuf, off, len);
        terminal.n(s);
    }

}
