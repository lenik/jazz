package net.bodz.bas.log.term;

import java.io.IOException;
import java.io.PrintStream;

import net.bodz.bas.sio.IPrintCharOut;
import net.bodz.bas.sio.PrintStreamPrintCharOut;

public class StreamTerminal
        extends AbstractTerminal {

    // private boolean dirty; ...
    private final PrintStream out;

    public StreamTerminal(PrintStream stream) {
        if (stream == null)
            throw new NullPointerException("stream");
        this.out = stream;
    }

    @Override
    public IPrintCharOut getCharOut() {
        return new PrintStreamPrintCharOut(out);
    }

    @Override
    public PrintStream getPrintStream() {
        return out;
    }

    @Override
    public void p() {
        out.println();
    }

    @Override
    public void p_(String s) {
        out.print(s);
    }

    @Override
    public void p(String s) {
        out.println(s);
    }

    @Override
    public void flush()
            throws IOException {
        out.flush();
    }

}
