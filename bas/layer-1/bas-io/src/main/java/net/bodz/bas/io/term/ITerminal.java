package net.bodz.bas.io.term;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

import net.bodz.bas.io.out.CharOut;

public interface ITerminal {

    CharOut getCharOut();

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

    void flush()
            throws IOException;

    void close()
            throws IOException;

}
