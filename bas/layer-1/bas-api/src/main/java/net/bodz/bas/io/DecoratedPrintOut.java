package net.bodz.bas.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import net.bodz.bas.meta.decl.NotNull;

public abstract class DecoratedPrintOut
        extends DecoratedCharOut
        implements IPrintOut {

    private static final long serialVersionUID = 1L;

    public DecoratedPrintOut(ICharOut _orig) {
        super(_orig);
    }

    @NotNull
    @Override
    public IPrintOut getWrapped() {
        return (IPrintOut) _orig;
    }

    @Override
    public void print(CharSequence s) {
        getWrapped().print(s);
    }

    @Override
    public void print(String s) {
        getWrapped().print(s);
    }

    @Override
    public void print(boolean b) {
        getWrapped().print(b);
    }

    @Override
    public void print(char c) {
        getWrapped().print(c);
    }

    @Override
    public void print(char[] s) {
        getWrapped().print(s);
    }

    @Override
    public void print(double d) {
        getWrapped().print(d);
    }

    @Override
    public void print(float f) {
        getWrapped().print(f);
    }

    @Override
    public void dump(@NotNull ICharIn charIn)
            throws IOException {
        getWrapped().dump(charIn);
    }

    @Override
    public void print(int i) {
        getWrapped().print(i);
    }

    @Override
    public void print(long l) {
        getWrapped().print(l);
    }

    @Override
    public void print(Object obj) {
        getWrapped().print(obj);
    }

    @Override
    public void print(Object... args) {
        getWrapped().print(args);
    }

    @Override
    public void println() {
        getWrapped().println();
    }

    @Override
    public void println(CharSequence x) {
        getWrapped().println(x);
    }

    @Override
    public void println(String x) {
        getWrapped().println(x);
    }

    @Override
    public void println(boolean x) {
        getWrapped().println(x);
    }

    @Override
    public void println(char x) {
        getWrapped().println(x);
    }

    @Override
    public void println(char[] x) {
        getWrapped().println(x);
    }

    @Override
    public void println(double x) {
        getWrapped().println(x);
    }

    @Override
    public void println(float x) {
        getWrapped().println(x);
    }

    @Override
    public void println(int x) {
        getWrapped().println(x);
    }

    @Override
    public void println(long x) {
        getWrapped().println(x);
    }

    @Override
    public void println(Object x) {
        getWrapped().println(x);
    }

    @Override
    public void println(Object... args) {
        getWrapped().println(args);
    }

    @Override
    public void printf(Locale l, String format, Object... args) {
        getWrapped().printf(l, format, args);
    }

    @Override
    public void printf(String format, Object... args) {
        getWrapped().printf(format, args);
    }

    @Override
    public void flush() {
        getWrapped().flush();
    }

    @Override
    public void close()
            throws IOException {
        getWrapped().close();
    }

    @NotNull
    @Override
    public ITreeOut indented() {
        return getWrapped().indented();
    }

    @NotNull
    @Override
    public PrintWriter toPrintWriter() {
        return getWrapped().toPrintWriter();
    }

}
