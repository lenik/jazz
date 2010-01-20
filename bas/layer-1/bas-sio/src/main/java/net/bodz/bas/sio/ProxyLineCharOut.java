package net.bodz.bas.sio;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Locale;

import net.bodz.bas.proxy.AbstractProxy;

public class ProxyLineCharOut
        extends AbstractProxy<ILineCharOut>
        implements ILineCharOut {

    public ProxyLineCharOut(ILineCharOut proxy) {
        super(proxy);
    }

    public void flush()
            throws SIOException {
        proxy.flush();
    }

    public void flush(boolean strict)
            throws IOException {
        proxy.flush(strict);
    }

    public void print(boolean b)
            throws SIOException {
        proxy.print(b);
    }

    public void print(char c)
            throws SIOException {
        proxy.print(c);
    }

    public void print(char[] s)
            throws SIOException {
        proxy.print(s);
    }

    public void print(double d)
            throws SIOException {
        proxy.print(d);
    }

    public void print(float f)
            throws SIOException {
        proxy.print(f);
    }

    public void print(int i)
            throws SIOException {
        proxy.print(i);
    }

    public void print(long l)
            throws SIOException {
        proxy.print(l);
    }

    public void print(Object... args)
            throws SIOException {
        proxy.print(args);
    }

    public void print(Object obj)
            throws SIOException {
        proxy.print(obj);
    }

    public void print(String s)
            throws SIOException {
        proxy.print(s);
    }

    public void printf(Locale l, String format, Object... args)
            throws SIOException {
        proxy.printf(l, format, args);
    }

    public void printf(String format, Object... args)
            throws SIOException {
        proxy.printf(format, args);
    }

    public void println()
            throws SIOException {
        proxy.println();
    }

    public void println(boolean x)
            throws SIOException {
        proxy.println(x);
    }

    public void println(char x)
            throws SIOException {
        proxy.println(x);
    }

    public void println(char[] x)
            throws SIOException {
        proxy.println(x);
    }

    public void println(double x)
            throws SIOException {
        proxy.println(x);
    }

    public void println(float x)
            throws SIOException {
        proxy.println(x);
    }

    public void println(int x)
            throws SIOException {
        proxy.println(x);
    }

    public void println(long x)
            throws SIOException {
        proxy.println(x);
    }

    public void println(Object... args)
            throws SIOException {
        proxy.println(args);
    }

    public void println(Object x)
            throws SIOException {
        proxy.println(x);
    }

    public void println(String x)
            throws SIOException {
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
