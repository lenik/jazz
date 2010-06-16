package net.bodz.bas.log.term;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

import net.bodz.bas.sio.IPrintCharOut;

public interface ITerminal {

    IPrintCharOut getCharOut();

    Writer getWriter();

    PrintStream getPrintStream();

    void p();

    void p_(String s);

    void p(String s);

    void p_(Object obj);

    void p(Object obj);

    void p_(Object... args);

    void p(Object... args);

    void f(String format, Object... args);

    /**
     * @param strict
     *            <code>true</code> means the call will return only after all buffered contents have
     *            been written, <code>false</code> means the caller has finished its output.
     */
    void flush(boolean strict)
            throws IOException;

    /**
     * The same to {@link #flush(boolean)} with <code>strict</code> set to <code>true</code>.
     */
    void flush()
            throws IOException;

}
