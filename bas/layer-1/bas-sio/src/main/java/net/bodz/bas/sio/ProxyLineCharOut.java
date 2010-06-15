package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Locale;

import net.bodz.bas.proxy.AbstractProxy;

public class ProxyLineCharOut
        extends AbstractProxy<IPrintCharOut>
        implements IPrintCharOut {

    public ProxyLineCharOut(IPrintCharOut proxy) {
        super(proxy);
    }

    @Override
    public void checkError(boolean reset)
            throws IOException {
        proxy.checkError(reset);
    }

    public void flush() {
        proxy.flush();
    }

    public void flush(boolean strict) {
        proxy.flush(strict);
    }

    @Override
    public void close() {
        proxy.close();
    }

    public void print(boolean b) {
        proxy.print(b);
    }

    public void print(char c) {
        proxy.print(c);
    }

    public void print(char[] s) {
        proxy.print(s);
    }

    public void print(double d) {
        proxy.print(d);
    }

    public void print(float f) {
        proxy.print(f);
    }

    public void print(int i) {
        proxy.print(i);
    }

    public void print(long l) {
        proxy.print(l);
    }

    public void print(Object... args) {
        proxy.print(args);
    }

    public void print(Object obj) {
        proxy.print(obj);
    }

    public void print(String s) {
        proxy.print(s);
    }

    public void printf(Locale l, String format, Object... args) {
        proxy.printf(l, format, args);
    }

    public void printf(String format, Object... args) {
        proxy.printf(format, args);
    }

    public void println() {
        proxy.println();
    }

    public void println(boolean x) {
        proxy.println(x);
    }

    public void println(char x) {
        proxy.println(x);
    }

    public void println(char[] x) {
        proxy.println(x);
    }

    public void println(double x) {
        proxy.println(x);
    }

    public void println(float x) {
        proxy.println(x);
    }

    public void println(int x) {
        proxy.println(x);
    }

    public void println(long x) {
        proxy.println(x);
    }

    public void println(Object... args) {
        proxy.println(args);
    }

    public void println(Object x) {
        proxy.println(x);
    }

    public void println(String x) {
        proxy.println(x);
    }

    public void write(char[] chars, int off, int len)
            throws IOException {
        proxy.write(chars, off, len);
    }

    public void write(char[] chars)
            throws IOException {
        proxy.write(chars);
    }

    public void write(CharBuffer charBuffer)
            throws IOException {
        proxy.write(charBuffer);
    }

    public void write(CharSequence chars, int off, int len)
            throws IOException {
        proxy.write(chars, off, len);
    }

    public void write(int ch)
            throws IOException {
        proxy.write(ch);
    }

    public void write(String s, int off, int len)
            throws IOException {
        proxy.write(s, off, len);
    }

    public void write(String s)
            throws IOException {
        proxy.write(s);
    }

}
