package net.bodz.bas.io.term;

import java.io.IOException;
import java.io.PrintStream;

import net.bodz.bas.io.out.CharOut;
import net.bodz.bas.io.out.CharOuts;

public class StreamTerminal extends AbstractTerminal {

    // private boolean dirty; ...
    private final PrintStream out;

    public StreamTerminal(PrintStream stream) {
        if (stream == null)
            throw new NullPointerException("stream"); 
        this.out = stream;
    }

    @Override
    public CharOut getCharOut() {
        return CharOuts.get(out);
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
    public void t(String s) {
        out.print(s);
        out.print('\r');
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
