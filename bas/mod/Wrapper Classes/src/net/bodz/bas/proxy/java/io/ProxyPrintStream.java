package net.bodz.bas.proxy.java.io;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;

/**
 * NOT WELL IMPLEMENTED.
 */
public class ProxyPrintStream extends PrintStream {

    protected PrintStream proxy;

    public ProxyPrintStream(PrintStream proxy) {
        super(System.err);
        assert proxy != null;
        this.proxy = proxy;
    }

    @Override
    public PrintStream append(char c) {
        return proxy.append(c);
    }

    @Override
    public PrintStream append(CharSequence csq, int start, int end) {
        return proxy.append(csq, start, end);
    }

    @Override
    public PrintStream append(CharSequence csq) {
        return proxy.append(csq);
    }

    @Override
    public boolean checkError() {
        return proxy.checkError();
    }

    @Override
    public void close() {
        proxy.close();
    }

    @Override
    public boolean equals(Object obj) {
        return proxy.equals(obj);
    }

    @Override
    public void flush() {
        proxy.flush();
    }

    @Override
    public PrintStream format(Locale l, String format, Object... args) {
        return proxy.format(l, format, args);
    }

    @Override
    public PrintStream format(String format, Object... args) {
        return proxy.format(format, args);
    }

    @Override
    public int hashCode() {
        return proxy.hashCode();
    }

    @Override
    public void print(boolean b) {
        proxy.print(b);
    }

    @Override
    public void print(char c) {
        proxy.print(c);
    }

    @Override
    public void print(char[] s) {
        proxy.print(s);
    }

    @Override
    public void print(double d) {
        proxy.print(d);
    }

    @Override
    public void print(float f) {
        proxy.print(f);
    }

    @Override
    public void print(int i) {
        proxy.print(i);
    }

    @Override
    public void print(long l) {
        proxy.print(l);
    }

    @Override
    public void print(Object obj) {
        proxy.print(obj);
    }

    @Override
    public void print(String s) {
        proxy.print(s);
    }

    @Override
    public PrintStream printf(Locale l, String format, Object... args) {
        return proxy.printf(l, format, args);
    }

    @Override
    public PrintStream printf(String format, Object... args) {
        return proxy.printf(format, args);
    }

    @Override
    public void println() {
        proxy.println();
    }

    @Override
    public void println(boolean x) {
        proxy.println(x);
    }

    @Override
    public void println(char x) {
        proxy.println(x);
    }

    @Override
    public void println(char[] x) {
        proxy.println(x);
    }

    @Override
    public void println(double x) {
        proxy.println(x);
    }

    @Override
    public void println(float x) {
        proxy.println(x);
    }

    @Override
    public void println(int x) {
        proxy.println(x);
    }

    @Override
    public void println(long x) {
        proxy.println(x);
    }

    @Override
    public void println(Object x) {
        proxy.println(x);
    }

    @Override
    public void println(String x) {
        proxy.println(x);
    }

    @Override
    public String toString() {
        return proxy.toString();
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        proxy.write(buf, off, len);
    }

    @Override
    public void write(byte[] b) throws IOException {
        proxy.write(b);
    }

    @Override
    public void write(int b) {
        proxy.write(b);
    }

}
