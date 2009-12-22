package net.bodz.bas.io.term;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.WriterOutputStream;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.text.util.Strings;

public abstract class _Terminal implements Terminal {

    @Override
    public CharOut getCharOut() {
        return new TerminalCharOut(this);
    }

    @Override
    public Writer getWriter() {
        return new TerminalWriter(this);
    }

    @Override
    public PrintStream getPrintStream() {
        Charset charset = Charset.forName("utf-8"); //$NON-NLS-1$
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
        n(s);
        p();
    }

    @Override
    public void n(Object obj) {
        n(String.valueOf(obj));
    }

    @Override
    public void p(Object obj) {
        p(String.valueOf(obj));
    }

    @Override
    public void t(Object obj) {
        t(String.valueOf(obj));
    }

    @Override
    public void n(Object... args) {
        String s = Strings.join("", args); //$NON-NLS-1$
        n(s);
    }

    @Override
    public void p(Object... args) {
        String s = Strings.join("", args); //$NON-NLS-1$
        p(s);
    }

    @Override
    public void t(Object... args) {
        String s = Strings.join("", args); //$NON-NLS-1$
        t(s);
    }

    @Override
    public void f(String format, Object... args) {
        String s = String.format(format, args);
        n(s);
    }

    // new features

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void close() throws IOException {
    }

    @Override
    public void beep() {
    }

    @Override
    public void setBackColor(int index) {
    }

    @Override
    public void setTextColor(int index) {
    }

}
