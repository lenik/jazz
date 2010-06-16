package net.bodz.bas.log.term;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

import net.bodz.bas.io.NullPrintStream;
import net.bodz.bas.io.NullWriter;
import net.bodz.bas.sio.IPrintCharOut;
import net.bodz.bas.sio.NullCharOut;

public class NullTerminal
        implements ITerminal {

    @Override
    public IPrintCharOut getCharOut() {
        return NullCharOut.getInstance();
    }

    @Override
    public Writer getWriter() {
        return NullWriter.getInstance();
    }

    @Override
    public PrintStream getPrintStream() {
        return NullPrintStream.getInstance();
    }

    @Override
    public void p() {
    }

    @Override
    public void p_(String s) {
    }

    @Override
    public void p(String s) {
    }

    @Override
    public void p_(Object obj) {
    }

    @Override
    public void p(Object obj) {
    }

    @Override
    public void p_(Object... args) {
    }

    @Override
    public void p(Object... args) {
    }

    @Override
    public void f(String format, Object... args) {
    }

    @Override
    public void flush()
            throws IOException {
    }

}
