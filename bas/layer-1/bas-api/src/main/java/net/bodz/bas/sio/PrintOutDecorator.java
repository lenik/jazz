package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Locale;

import net.bodz.bas.model.AbstractDecorator;

public class PrintOutDecorator
        extends AbstractDecorator<IPrintOut>
        implements IPrintOut {

    private static final long serialVersionUID = 1L;

    public PrintOutDecorator(IPrintOut _orig) {
        super(_orig);
    }

    @Override
    public void flush() {
        _orig.flush();
    }

    @Override
    public void flush(boolean strict) {
        _orig.flush(strict);
    }

    @Override
    public void close() {
        _orig.close();
    }

    @Override
    public void print(boolean b) {
        _orig.print(b);
    }

    @Override
    public void print(char c) {
        _orig.print(c);
    }

    @Override
    public void print(char[] s) {
        _orig.print(s);
    }

    @Override
    public void print(double d) {
        _orig.print(d);
    }

    @Override
    public void print(float f) {
        _orig.print(f);
    }

    @Override
    public void print(int i) {
        _orig.print(i);
    }

    @Override
    public void print(long l) {
        _orig.print(l);
    }

    @Override
    public void print(Object... args) {
        _orig.print(args);
    }

    @Override
    public void print(Object obj) {
        _orig.print(obj);
    }

    @Override
    public void print(String s) {
        _orig.print(s);
    }

    @Override
    public void printf(Locale l, String format, Object... args) {
        _orig.printf(l, format, args);
    }

    @Override
    public void printf(String format, Object... args) {
        _orig.printf(format, args);
    }

    @Override
    public void println() {
        _orig.println();
    }

    @Override
    public void println(boolean x) {
        _orig.println(x);
    }

    @Override
    public void println(char x) {
        _orig.println(x);
    }

    @Override
    public void println(char[] x) {
        _orig.println(x);
    }

    @Override
    public void println(double x) {
        _orig.println(x);
    }

    @Override
    public void println(float x) {
        _orig.println(x);
    }

    @Override
    public void println(int x) {
        _orig.println(x);
    }

    @Override
    public void println(long x) {
        _orig.println(x);
    }

    @Override
    public void println(Object... args) {
        _orig.println(args);
    }

    @Override
    public void println(Object x) {
        _orig.println(x);
    }

    @Override
    public void println(String x) {
        _orig.println(x);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        _orig.write(chars, off, len);
    }

    @Override
    public void write(char[] chars)
            throws IOException {
        _orig.write(chars);
    }

    @Override
    public void write(CharBuffer charBuffer)
            throws IOException {
        _orig.write(charBuffer);
    }

    @Override
    public void write(CharSequence chars, int off, int len)
            throws IOException {
        _orig.write(chars, off, len);
    }

    @Override
    public void write(int ch)
            throws IOException {
        _orig.write(ch);
    }

    @Override
    public void write(String s, int off, int len)
            throws IOException {
        _orig.write(s, off, len);
    }

    @Override
    public void write(String s)
            throws IOException {
        _orig.write(s);
    }

}
