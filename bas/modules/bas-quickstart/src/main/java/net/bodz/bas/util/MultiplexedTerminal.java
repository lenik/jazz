package net.bodz.bas.util;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.commons.exceptions.NotImplementedException;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.term.Terminal;
import net.bodz.bas.io.term._Terminal;

public class MultiplexedTerminal extends _Terminal {

    private List<Terminal> terminals;

    public MultiplexedTerminal(Terminal... terminals) {
        this.terminals = new ArrayList<Terminal>(terminals.length);
        for (Terminal t : terminals)
            this.terminals.add(t);
    }

    public boolean add(Terminal terminal) {
        return terminals.add(terminal);
    }

    public boolean remove(Terminal terminal) {
        return terminals.remove(terminal);
    }

    @Override
    public void f(String format, Object... args) {
        for (Terminal t : terminals)
            t.f(format, args);
    }

    public void n(String s) {
        for (Terminal t : terminals)
            t.n(s);
    }

    public void p() {
        for (Terminal t : terminals)
            t.p();
    }

    @Override
    public void p(String s) {
        for (Terminal t : terminals)
            t.p(s);
    }

    public void t(String s) {
        for (Terminal t : terminals)
            t.t(s);
    }

    @Override
    public void beep() {
        for (Terminal t : terminals)
            t.beep();
    }

    @Override
    public void flush() throws IOException {
        for (Terminal t : terminals)
            t.flush();
    }

    @Override
    public void close() throws IOException {
        for (Terminal t : terminals)
            t.close();
    }

    @Override
    public CharOut getCharOut() {
        throw new NotImplementedException();
    }

    @Override
    public PrintStream getPrintStream() {
        throw new NotImplementedException();
    }

    @Override
    public Writer getWriter() {
        throw new NotImplementedException();
    }

}
