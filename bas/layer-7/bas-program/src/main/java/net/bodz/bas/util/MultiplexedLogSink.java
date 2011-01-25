package net.bodz.bas.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.log.AbstractLogSink;
import net.bodz.bas.log.ILogSink;

public class MultiplexedLogSink
        extends AbstractLogSink {

    private List<ILogSink> terminals;

    public MultiplexedLogSink(ILogSink... terminals) {
        this.terminals = new ArrayList<ILogSink>(terminals.length);
        for (ILogSink t : terminals)
            this.terminals.add(t);
    }

    public boolean add(ILogSink terminal) {
        return terminals.add(terminal);
    }

    public boolean remove(ILogSink terminal) {
        return terminals.remove(terminal);
    }

    @Override
    public void f(String format, Object... args) {
        for (ILogSink t : terminals)
            t.f(format, args);
    }

    @Override
    public void p(String s) {
        for (ILogSink t : terminals)
            t.p(s);
    }

}
