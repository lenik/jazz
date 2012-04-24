package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Locale;

import net.bodz.bas.mode.AbstractDecorator;

public class PrintOutDecorator
        extends AbstractDecorator<IPrintOut>
        implements IPrintOut {

    public PrintOutDecorator(IPrintOut impl) {
        super(impl);
    }

    public void flush() {
        impl.flush();
    }

    public void flush(boolean strict) {
        impl.flush(strict);
    }

    @Override
    public void close() {
        impl.close();
    }

    public void print(boolean b) {
        impl.print(b);
    }

    public void print(char c) {
        impl.print(c);
    }

    public void print(char[] s) {
        impl.print(s);
    }

    public void print(double d) {
        impl.print(d);
    }

    public void print(float f) {
        impl.print(f);
    }

    public void print(int i) {
        impl.print(i);
    }

    public void print(long l) {
        impl.print(l);
    }

    public void print(Object... args) {
        impl.print(args);
    }

    public void print(Object obj) {
        impl.print(obj);
    }

    public void print(String s) {
        impl.print(s);
    }

    public void printf(Locale l, String format, Object... args) {
        impl.printf(l, format, args);
    }

    public void printf(String format, Object... args) {
        impl.printf(format, args);
    }

    public void println() {
        impl.println();
    }

    public void println(boolean x) {
        impl.println(x);
    }

    public void println(char x) {
        impl.println(x);
    }

    public void println(char[] x) {
        impl.println(x);
    }

    public void println(double x) {
        impl.println(x);
    }

    public void println(float x) {
        impl.println(x);
    }

    public void println(int x) {
        impl.println(x);
    }

    public void println(long x) {
        impl.println(x);
    }

    public void println(Object... args) {
        impl.println(args);
    }

    public void println(Object x) {
        impl.println(x);
    }

    public void println(String x) {
        impl.println(x);
    }

    public void write(char[] chars, int off, int len)
            throws IOException {
        impl.write(chars, off, len);
    }

    public void write(char[] chars)
            throws IOException {
        impl.write(chars);
    }

    public void write(CharBuffer charBuffer)
            throws IOException {
        impl.write(charBuffer);
    }

    public void write(CharSequence chars, int off, int len)
            throws IOException {
        impl.write(chars, off, len);
    }

    public void write(int ch)
            throws IOException {
        impl.write(ch);
    }

    public void write(String s, int off, int len)
            throws IOException {
        impl.write(s, off, len);
    }

    public void write(String s)
            throws IOException {
        impl.write(s);
    }

}
