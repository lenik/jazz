package net.bodz.bas.io.term;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;

import net.bodz.bas.io.CharOut;

public interface Terminal {

    CharOut getCharOut();

    Writer getWriter();

    PrintStream getPrintStream();

    void p();

    void n(String s);

    void p(String s);

    void t(String s);

    void n(Object obj);

    void p(Object obj);

    void t(Object obj);

    void n(Object... args);

    void p(Object... args);

    void t(Object... args);

    void f(String format, Object... args);

    void flush() throws IOException;

    void close() throws IOException;

    // extras

    void setTextColor(int index);

    void setBackColor(int index);

    void beep();

}
