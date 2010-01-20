package net.bodz.bas.io.term;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;

import net.bodz.bas.exceptions.UnexpectedException;
import net.bodz.bas.io.WriterOutputStream;
import net.bodz.bas.sio.ILineCharOut;
import net.bodz.bas.text.util.StringArray;

public abstract class AbstractTerminal
        implements ITerminal {

    @Override
    public ILineCharOut getCharOut() {
        return new TerminalCharOut(this);
    }

    @Override
    public Writer getWriter() {
        return new TerminalWriter(this);
    }

    @Override
    public PrintStream getPrintStream() {
        Charset charset = Charset.forName("utf-8");
        WriterOutputStream out = new WriterOutputStream(getWriter(), charset);
        try {
            PrintStream printStream = new PrintStream(out, true, charset.name());
            return printStream;
        } catch (UnsupportedEncodingException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public void p(String s) {
        p_(s);
        p();
    }

    @Override
    public void p(Object obj) {
        p(String.valueOf(obj));
    }

    @Override
    public void p_(Object obj) {
        p_(String.valueOf(obj));
    }

    @Override
    public void p(Object... args) {
        String s = StringArray.join("", args);
        p(s);
    }

    @Override
    public void p_(Object... args) {
        String s = StringArray.join("", args);
        p_(s);
    }

    @Override
    public void f(String format, Object... args) {
        String s = String.format(format, args);
        p_(s);
    }

    // new features

    @Override
    public void flush()
            throws IOException {
    }

}
