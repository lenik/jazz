package net.bodz.bas.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public abstract class DecoratedPrintOut
        extends DecoratedCharOut
        implements
            IPrintOut {

    private static final long serialVersionUID = 1L;

    public DecoratedPrintOut(ICharOut _orig) {
        super(_orig);
    }

    @Override
    public IPrintOut getWrapped() {
        return (IPrintOut) _orig;
    }

    public boolean isClosed() {
        return getWrapped().isClosed();
    }

    public void print(CharSequence s) {
        getWrapped().print(s);
    }

    public void print(String s) {
        getWrapped().print(s);
    }

    public void print(boolean b) {
        getWrapped().print(b);
    }

    public void print(char c) {
        getWrapped().print(c);
    }

    public void print(char[] s) {
        getWrapped().print(s);
    }

    public void print(double d) {
        getWrapped().print(d);
    }

    public void print(float f) {
        getWrapped().print(f);
    }

    public void dump(ICharIn charIn)
            throws IOException {
        getWrapped().dump(charIn);
    }

    public void print(int i) {
        getWrapped().print(i);
    }

    public void print(long l) {
        getWrapped().print(l);
    }

    public void print(Object obj) {
        getWrapped().print(obj);
    }

    public void print(Object... args) {
        getWrapped().print(args);
    }

    public void println() {
        getWrapped().println();
    }

    public void println(CharSequence x) {
        getWrapped().println(x);
    }

    public void println(String x) {
        getWrapped().println(x);
    }

    public void println(boolean x) {
        getWrapped().println(x);
    }

    public void println(char x) {
        getWrapped().println(x);
    }

    public void println(char[] x) {
        getWrapped().println(x);
    }

    public void println(double x) {
        getWrapped().println(x);
    }

    public void println(float x) {
        getWrapped().println(x);
    }

    public void println(int x) {
        getWrapped().println(x);
    }

    public void println(long x) {
        getWrapped().println(x);
    }

    public void println(Object x) {
        getWrapped().println(x);
    }

    public void println(Object... args) {
        getWrapped().println(args);
    }

    public void printf(Locale l, String format, Object... args) {
        getWrapped().printf(l, format, args);
    }

    public void printf(String format, Object... args) {
        getWrapped().printf(format, args);
    }

    @Override
    public void flush() {
        getWrapped().flush();
    }

    public void _flushX()
            throws IOException {
        getWrapped()._flushX();
    }

    @Override
    public void close() {
        getWrapped().close();
    }

    public void _closeX()
            throws IOException {
        getWrapped()._closeX();
    }

    public ITreeOut indented() {
        return getWrapped().indented();
    }

    public PrintWriter toPrintWriter() {
        return getWrapped().toPrintWriter();
    }

}
